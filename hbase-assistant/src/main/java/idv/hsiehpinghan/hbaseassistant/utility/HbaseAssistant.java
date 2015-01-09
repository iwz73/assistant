package idv.hsiehpinghan.hbaseassistant.utility;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.hbase.RowMapper;
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
		Object rowKeyObj = ObjectUtility.readField(entity, "rowKey");
		byte[] rowKey = ((HBaseRowKey) rowKeyObj).toBytes();
		final Put put = new Put(rowKey);
		// Get column families
		String[] colFamArr = getColumnFamilyNames(cls);
		for (String colFamNm : colFamArr) {
			Object colFamObj = ObjectUtility.readField(entity, colFamNm);
			List<Field> qualAndValFields = ObjectUtility
					.getFieldsByAssignableType(colFamObj.getClass(), Map.class);
			byte[] columnFamily = Bytes.toBytes(colFamNm);
			// Get qualifier and value
			for (Field qvField : qualAndValFields) {
				@SuppressWarnings("unchecked")
				Map<HBaseColumnQualifier, Map<Date, HBaseValue>> qvMap = (Map<HBaseColumnQualifier, Map<Date, HBaseValue>>) ObjectUtility
						.readField(colFamObj, qvField.getName());
				for (Map.Entry<HBaseColumnQualifier, Map<Date, HBaseValue>> entry : qvMap
						.entrySet()) {
					byte[] qualifier = entry.getKey().toBytes();
					Map<Date, HBaseValue> tsVal = entry.getValue();
					for (Map.Entry<Date, HBaseValue> ent : tsVal.entrySet()) {
						long version = ent.getKey().getTime();
						byte[] value = ent.getValue().toBytes();
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
	 * Get a row.
	 * 
	 * @param rowKey
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public HBaseTable get(HBaseRowKey rowKey) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException {
		// Get Table.
		final HBaseTable tableObj = rowKey.getTable();
		final Class<?> tableCls = tableObj.getClass();
		String tableName = tableCls.getSimpleName();
		String rowKy = new String(rowKey.toBytes());

		// Get Column families.
		List<Field> colFamFields = ObjectUtility.getFieldsByAssignableType(
				tableCls, HBaseColumnFamily.class);
		final Map<String, HBaseColumnFamily> famMap = new HashMap<String, HBaseColumnFamily>(
				colFamFields.size());
		for (Field fld : colFamFields) {
			HBaseColumnFamily columnFamily = (HBaseColumnFamily) ObjectUtility
					.createInnerClassInstance(tableObj, fld.getType());
			famMap.put(fld.getName(), columnFamily);
			FieldUtils.writeField(tableObj, fld.getName(), columnFamily, true);
		}
		return hbaseTemplate.getAllVersion(tableName, rowKy,
				new RowMapper<HBaseTable>() {
					@Override
					public HBaseTable mapRow(Result result, int rowNum)
							throws Exception {
						NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result
								.getMap();
						if (map == null) {
							return null;
						}
						// Set column family.
						for (Map.Entry<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> entry : map
								.entrySet()) {
							String famNm = new String(entry.getKey());
							HBaseColumnFamily colFam = famMap.get(famNm);
							colFam.fromMap(entry.getValue());
						}
						return tableObj;
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

	private List<String> convertToFiledNames(List<Field> colFamFields) {
		List<String> fNms = new ArrayList<String>(colFamFields.size());
		for (Field f : colFamFields) {
			fNms.add(f.getName());
		}
		return fNms;
	}

	private String[] getColumnFamilyNames(Class<?> cls) {
		List<Field> colFamFields = ObjectUtility.getFieldsByAssignableType(cls,
				HBaseColumnFamily.class);
		List<String> colFamNms = convertToFiledNames(colFamFields);
		return colFamNms.toArray(new String[colFamNms.size()]);
	}
}
