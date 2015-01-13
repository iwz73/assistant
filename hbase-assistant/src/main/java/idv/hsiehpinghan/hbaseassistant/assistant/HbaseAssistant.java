package idv.hsiehpinghan.hbaseassistant.assistant;

import idv.hsiehpinghan.classutility.utility.ClassUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.enumeration.TableOperation;
import idv.hsiehpinghan.hbaseassistant.extension.HbaseTemplateExtension;
import idv.hsiehpinghan.hbaseassistant.property.HbaseAssistantProperty;
import idv.hsiehpinghan.objectutility.utility.ObjectUtility;
import idv.hsiehpinghan.packageutility.utility.PackageUtility;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Component;

@Component
public class HbaseAssistant implements InitializingBean {
	private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	@Qualifier("hbaseConfiguration")
	private Configuration config;
	@Autowired
	private HBaseAdmin admin;
	@Autowired
	private HbaseTemplateExtension hbaseTemplate;
	@Autowired
	private HbaseAssistantProperty hbaseAssistantProperty;

	@Override
	public void afterPropertiesSet() throws Exception {
		TableOperation operation = TableOperation
				.valueOf(hbaseAssistantProperty.getTableOperation());
		if (TableOperation.NONE.equals(operation)) {
			return;
		}
		String[] pkgs = PackageUtility.getHbaseEntityPackages();
		logger.info("Hbase scan entity package : " + ArrayUtils.toString(pkgs));
		scanAndCreateTable(pkgs, operation);
	}

	/**
	 * Scan packages and create table.
	 * 
	 * @param packageNames
	 * @param operation
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void scanAndCreateTable(String[] packageNames,
			TableOperation operation) throws ClassNotFoundException,
			IOException {
		for (String packageName : packageNames) {
			scanAndCreateTable(packageName, operation);
		}
	}

	/**
	 * Scan package and create table.
	 * 
	 * @param packageName
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void scanAndCreateTable(String packageName, TableOperation operation)
			throws ClassNotFoundException, IOException {
		if (TableOperation.NONE.equals(operation)) {
			return;
		}
		List<Class<?>> classes = ClassUtility.getClasses(packageName);
		for (Class<?> cls : classes) {
			if (HBaseTable.class.isAssignableFrom(cls) == false) {
				continue;
			}
			String tableNm = cls.getSimpleName();
			if (isTableExists(tableNm)) {
				switch (operation) {
				case ADD_NONEXISTS:
					continue;
				case DROP_CREATE:
					dropTable(tableNm);
					break;
				case NONE:
					continue;
				default:
					throw new RuntimeException("Not implements !!!");
				}
			}
			createTable(cls);
		}
	}

	/**
	 * Create table.
	 * 
	 * @param clazz
	 * @throws IOException
	 */
	public void createTable(Class<?> clazz) throws IOException {
		String tableNm = clazz.getSimpleName();
		String[] colFamArr = getColumnFamilyNames(clazz);
		createTable(tableNm, colFamArr);
	}

	/**
	 * Add a row.
	 * 
	 * @param entity
	 * @throws IllegalAccessException
	 */
	public void put(HBaseTable entity) throws IllegalAccessException {
		Class<?> cls = entity.getClass();
		String tableName = cls.getSimpleName();
		// Get row key
		byte[] rowKey = getRowKey(entity).toBytes();
		final Put put = new Put(rowKey);
		// Get column families
		List<Field> colFamFlds = getColumnFamilyFields(cls);
		for (Field famFld : colFamFlds) {
			String colFamNm = famFld.getName();
			Object colFamObj = ObjectUtility.readField(entity, colFamNm);
			List<Field> qualMapFields = ObjectUtility
					.getFieldsByAssignableType(colFamObj.getClass(), Map.class);
			byte[] columnFamily = Bytes.toBytes(colFamNm);
			// Get qualifier and value
			for (Field qualMapField : qualMapFields) {
				@SuppressWarnings("unchecked")
				Map<HBaseColumnQualifier, Map<Date, HBaseValue>> qualMap = (Map<HBaseColumnQualifier, Map<Date, HBaseValue>>) ObjectUtility
						.readField(colFamObj, qualMapField.getName());
				for (Map.Entry<HBaseColumnQualifier, Map<Date, HBaseValue>> qualEntry : qualMap
						.entrySet()) {
					byte[] qualifier = qualEntry.getKey().toBytes();
					Map<Date, HBaseValue> verMap = qualEntry.getValue();
					for (Map.Entry<Date, HBaseValue> verEntry : verMap
							.entrySet()) {
						long version = verEntry.getKey().getTime();
						byte[] value = verEntry.getValue().toBytes();
						put.add(columnFamily, qualifier, version, value);
					}
				}
			}
		}
		hbaseTemplate.execute(tableName, new TableCallback<Void>() {
			@Override
			public Void doInTable(HTableInterface tableItf) throws Throwable {
				tableItf.put(put);
				return null;
			}
		});
	}

	/**
	 * Get Hbase entity.
	 * 
	 * @param rowKey
	 * @return
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public HBaseTable get(HBaseRowKey rowKey) throws IllegalAccessException,
			IOException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalArgumentException,
			InvocationTargetException {
		HBaseTable entity = rowKey.getTable();
		get(entity, 1, null, null, null);
		return entity;
	}

	/**
	 * Get Hbase entity.
	 * 
	 * @param entity
	 * @param maxVersions
	 * @param minDate
	 * @param maxDate
	 * @param filter
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public void get(final HBaseTable entity, int maxVersions, Date minDate,
			Date maxDate, Filter filter) throws IllegalAccessException,
			IOException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalArgumentException,
			InvocationTargetException {
		final Get get = generateGet(entity, maxVersions, minDate, maxDate,
				filter);
		hbaseTemplate.execute(entity.getTableName(), new TableCallback<Void>() {
			@Override
			public Void doInTable(HTableInterface tableItf) throws Throwable {
				NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = tableItf
						.get(get).getMap();
				if (map == null) {
					return null;
				}
				// Set column family.
				for (Map.Entry<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> entry : map
						.entrySet()) {
					String colFamNm = Bytes.toString(entry.getKey());
					HBaseColumnFamily colFam = (HBaseColumnFamily) ObjectUtility
							.readField(entity, colFamNm);
					colFam.fromMap(entry.getValue());
				}
				return null;
			}
		});
	}

	/**
	 * Drop table.
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	public void dropTable(String tableName) throws IOException {
		if (admin.isTableEnabled(tableName)) {
			admin.disableTable(tableName);
		}
		admin.deleteTable(tableName);
	}

	/**
	 * Check if table exists.
	 * 
	 * @param tableName
	 * @return
	 * @throws IOException
	 */
	public boolean isTableExists(String tableName) throws IOException {
		return admin.tableExists(tableName);
	}

	/**
	 * Check if row exists.
	 * 
	 * @param rowKey
	 * @return
	 */
	public boolean exist(HBaseRowKey rowKey) {
		String tableName = rowKey.getTableName();
		final Get get = new Get(rowKey.toBytes());
		return hbaseTemplate.execute(tableName, new TableCallback<Boolean>() {
			@Override
			public Boolean doInTable(HTableInterface tableItf) throws Throwable {
				return tableItf.exists(get);
			}
		});
	}

	void createTable(String tableName, String[] columnFamilies)
			throws IOException {
		HTableDescriptor tDesc = new HTableDescriptor(
				TableName.valueOf(tableName));
		for (int i = 0; i < columnFamilies.length; i++) {
			HColumnDescriptor cDesc = new HColumnDescriptor(columnFamilies[i]);
			tDesc.addFamily(cDesc);
		}
		admin.createTable(tDesc);
		logger.info(tableName + " created.");
	}

	Get generateGet(HBaseTable entity, int maxVersions, Date minDate,
			Date maxDate, Filter filter) throws IllegalAccessException,
			IOException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalArgumentException,
			InvocationTargetException {
		Get get = new Get(entity.getRowKey().toBytes());
		List<Field> colFamFields = ObjectUtility.getFieldsByAssignableType(
				entity.getClass(), HBaseColumnFamily.class);
		boolean isAllFamNull = isAllColumnFamilyFieldNull(entity, colFamFields);
		for (Field famField : colFamFields) {
			String famName = famField.getName();
			Object famObj = ObjectUtility.readField(entity, famName);
			if (famObj == null) {
				if (isAllFamNull == false) {
					continue;
				}
				famObj = ObjectUtility.createInnerClassInstance(entity,
						famField.getType(), entity);
				ObjectUtility.setField(entity, famField, famObj);
			}
			HBaseColumnFamily colFam = (HBaseColumnFamily) famObj;
			String famNm = convertToFiledName(famField);
			NavigableMap<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualMap = colFam
					.getQualifierVersionValueMap();
			if (qualMap.size() == 0) {
				get.addFamily(Bytes.toBytes(famNm));
				continue;
			}
			for (Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> entry : qualMap
					.entrySet()) {
				get.addColumn(Bytes.toBytes(famNm), entry.getKey().toBytes());
			}
		}
		long minTimestamp = minDate == null ? 0 : minDate.getTime();
		long maxTimestamp = maxDate == null ? Long.MAX_VALUE : maxDate
				.getTime();
		get.setTimeRange(minTimestamp, maxTimestamp);
		if (filter != null) {
			get.setFilter(filter);
		}
		if (maxVersions == 0) {
			get.setMaxVersions();
		} else {
			get.setMaxVersions(maxVersions);
		}
		return get;
	}

	String[] getColumnFamilyNames(Class<?> cls) {
		List<Field> colFamFields = getColumnFamilyFields(cls);
		List<String> colFamNms = convertToFiledNames(colFamFields);
		return colFamNms.toArray(new String[colFamNms.size()]);
	}

	HColumnDescriptor[] getColumnDescriptors(String tableName) {
		return hbaseTemplate.execute(tableName,
				new TableCallback<HColumnDescriptor[]>() {
					@Override
					public HColumnDescriptor[] doInTable(
							HTableInterface tableItf) throws Throwable {
						HColumnDescriptor[] colDescs = tableItf
								.getTableDescriptor().getColumnFamilies();
						return colDescs;
					}
				});
	}

	boolean isRowExists(final HBaseRowKey rowKey) {
		return hbaseTemplate.execute(rowKey.getTableName(),
				new TableCallback<Boolean>() {
					@Override
					public Boolean doInTable(HTableInterface tableItf)
							throws Throwable {
						Get get = new Get(rowKey.toBytes());
						return tableItf.exists(get);
					}
				});
	}

	private boolean isAllColumnFamilyFieldNull(HBaseTable entity,
			List<Field> colFamFields) throws IllegalAccessException {
		for (Field famField : colFamFields) {
			Object famObj = ObjectUtility.readField(entity, famField.getName());
			if (famObj != null) {
				return false;
			}
		}
		return true;
	}

	private List<String> convertToFiledNames(List<Field> colFamFields) {
		List<String> fNms = new ArrayList<String>(colFamFields.size());
		for (Field f : colFamFields) {
			fNms.add(convertToFiledName(f));
		}
		return fNms;
	}

	private String convertToFiledName(Field colFamField) {
		return colFamField.getName();
	}

	private List<Field> getColumnFamilyFields(Class<?> cls) {
		return ObjectUtility.getFieldsByAssignableType(cls,
				HBaseColumnFamily.class);
	}

	private HBaseRowKey getRowKey(HBaseTable entity)
			throws IllegalAccessException {
		List<Field> rowKeyFileds = ObjectUtility.getFieldsByAssignableType(
				entity.getClass(), HBaseRowKey.class);
		int size = rowKeyFileds.size();
		if (size <= 0) {
			throw new RuntimeException(entity.getTableName()
					+ "'s row key not set !!!");
		} else if (size > 1) {
			throw new RuntimeException(entity.getTableName() + " set " + size
					+ " row keys !!!");
		}
		Object rowKeyObj = ObjectUtility.readField(entity, rowKeyFileds.get(0)
				.getName());
		return (HBaseRowKey) rowKeyObj;
	}
}
