package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.classutility.utility.ClassUtility;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.enumeration.TableOperation;
import idv.hsiehpinghan.hbaseassistant.extension.HbaseTemplateExtension;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseValue;
import idv.hsiehpinghan.objectutility.utility.ObjectUtility;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import org.apache.commons.lang3.reflect.ConstructorUtils;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Component;

@Component
public class HbaseAssistant {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	@Qualifier("hbaseConfiguration")
	private Configuration config;
	@Autowired
	private HBaseAdmin admin;
	@Autowired
	private HbaseTemplateExtension hbaseTemplate;

	/**
	 * Scan package and create table.
	 * 
	 * @param packageName
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void scanAndCreateTable(String packageName, TableOperation operation)
			throws ClassNotFoundException, IOException {
		List<Class<?>> classes = ClassUtility.getClasses(packageName);
		for (Class<?> cls : classes) {
			if(HBaseTable.class.isAssignableFrom(cls) == false) {
				continue;
			}
			String tableNm = cls.getSimpleName();
			String[] colFamArr = getColumnFamilyNames(cls);

			if (isTableExists(tableNm)) {
				switch (operation) {
				case ADD_NONEXISTS:
					continue;
				case DROP_CREATE:
					dropTable(tableNm);
					break;
				default:
					throw new RuntimeException("Not implements !!!");
				}
			}
			createTable(tableNm, colFamArr);
		}
	}

	/**
	 * Add a row.
	 * 
	 * @param entity
	 * @throws IllegalAccessException
	 */
	public void put(Object entity) throws IllegalAccessException {
		Class<?> cls = entity.getClass();
		String tableName = cls.getSimpleName();
		// Get row key
		Object rowKyeObj = ObjectUtility.readField(entity, "rowKey");
		byte[] rowKey = ((HBaseRowKey) rowKyeObj).toBytes();
		final Put put = new Put(rowKey);
		// Get column families
		String[] colFamArr = getColumnFamilyNames(cls);

		logger.debug("colFamArr size : " + colFamArr.length);
		
		for (String colFamNm : colFamArr) {
			Object colFamObj = ObjectUtility.readField(entity, colFamNm);
			List<Field> qualAndValFields = ObjectUtility
					.getFieldsByAssignableType(colFamObj.getClass(), Map.class);
			byte[] columnFamily = Bytes.toBytes(colFamNm);
			
			logger.debug("qualAndValFields size : " + qualAndValFields.size());
			
			// Get qualifier and value
			for (Field qvField : qualAndValFields) {
				@SuppressWarnings("unchecked")
				Map<HBaseColumnQualifier, Map<Date, HBaseValue>> qvMap = (Map<HBaseColumnQualifier, Map<Date, HBaseValue>>) ObjectUtility
						.readField(colFamObj, qvField.getName());
				
				logger.debug("qvMap size : " + qvMap.size());
				
				for (Map.Entry<HBaseColumnQualifier, Map<Date, HBaseValue>> entry : qvMap
						.entrySet()) {
					byte[] qualifier = entry.getKey().toBytes();
					Map<Date, HBaseValue> tsVal = entry.getValue();
					
					logger.debug("qvMap size : " + qvMap.size());
					
					for (Map.Entry<Date, HBaseValue> ent : tsVal.entrySet()) {
						long version = ent.getKey().getTime();
						byte[] value = ent.getValue().toBytes();
						
						logger.debug(columnFamily + " / " + qualifier + " / " + version + " / " + value);
						
						put.add(columnFamily, qualifier, version, value);
					}
				}
			}
		}
		hbaseTemplate.execute(tableName, new TableCallback<Boolean>() {
			@Override
			public Boolean doInTable(HTableInterface tableItf) throws Throwable {
				tableItf.put(put);
				return true;
			}
		});
	}

	/**
	 * Get a row.
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
	public Object get(HBaseRowKey rowKey) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
		Object tableObj = ObjectUtility.getOuterObject(rowKey);
		final Class<?> tableCls = tableObj.getClass();
		String tableName = tableCls.getSimpleName();
		String rowKy = new String(rowKey.toBytes());

		// Get Table.
		Object table = ObjectUtility.getOuterObject(rowKey);
		// Get Column families.
		List<Field> colFamFields = ObjectUtility.getFieldsByAssignableType(table.getClass(),
				HBaseColumnFamily.class);
		
		logger.error("colFamFields.size : " + colFamFields.size());
		
		for(Field fld : colFamFields) {

			Object columnFamily = ObjectUtility.createInnerClassInstance(table, fld.getType());
			
			logger.error("o1 : " + table);
			FieldUtils.writeField(table, fld.getName(), columnFamily, true);
			logger.error("o2 : " + table);
		}
		
//		String[] colFamNms = getColumnFamilyNames(o.getClass());
//		for(String colFamN : colFamNms)
//		logger.error("o : " + colFamNms);
		
		return hbaseTemplate.getAllVersion(tableName, rowKy, new RowMapper<Object>() {
			@Override
			public Object mapRow(Result result, int rowNum) throws Exception {
				NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result.getMap();
				for (Map.Entry<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> entry : map.entrySet()) {
					byte[] familyArr = entry.getKey();
					NavigableMap<byte[], NavigableMap<Long, byte[]>> qualTsValMap = entry.getValue();
					for (Map.Entry<byte[], NavigableMap<Long, byte[]>> entry2 : qualTsValMap.entrySet()) {
						byte[] qualArr = entry2.getKey();
						NavigableMap<Long, byte[]> tsValMap = entry2.getValue();
						for (Map.Entry<Long, byte[]> entry3 : tsValMap.entrySet()) {
							Long version = entry3.getKey();
							byte[] value = entry3.getValue();
						}
					}
				}
				return null;
			}

		});
	}

	// public List<User> findAll() {
	// return hbaseTemplate.find(tableName, "cfInfo", new RowMapper<User>() {
	// @Override
	// public User mapRow(Result result, int rowNum) throws Exception {
	// return new User(Bytes.toString(result.getValue(CF_INFO, qUser)),
	// Bytes.toString(result.getValue(CF_INFO, qEmail)),
	// Bytes.toString(result.getValue(CF_INFO, qPassword)));
	// }
	// });
	//
	// }

	void createTable(String tableName, String[] columnFamilies)
			throws IOException {
		HTableDescriptor tDesc = new HTableDescriptor(
				TableName.valueOf(tableName));
		for (int i = 0; i < columnFamilies.length; i++) {
			HColumnDescriptor cDesc = new HColumnDescriptor(columnFamilies[i]);
			tDesc.addFamily(cDesc);
		}
		admin.createTable(tDesc);
	}

	void dropTable(String tableName) throws IOException {
		if (admin.isTableEnabled(tableName)) {
			admin.disableTable(tableName);
		}
		admin.deleteTable(tableName);
	}

	boolean isTableExists(String tableName) throws IOException {
		return admin.tableExists(tableName);
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
		
		logger.debug("colFamFields size : " + colFamFields.size());
		
		List<String> colFamNms = convertToFiledNames(colFamFields);
		return colFamNms.toArray(new String[colFamNms.size()]);
	}

//	private String getTableName(Class<?> cls) {
//		HBaseTable table = cls.getAnnotation(HBaseTable.class);
//		if (table == null) {
//			throw new RuntimeException("Not a table entity !!!");
//		}
//		return table.value();
//	}
}
