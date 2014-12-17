package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.classutility.utility.ClassUtility;
import idv.hsiehpinghan.hbaseassistant.annotation.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.enumeration.TableOperation;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseQualifier;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.interfaces.HBaseValue;
import idv.hsiehpinghan.objectutility.utility.ObjectUtility;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Component;

@Component
public class HbaseAssistant {
	@Autowired
	@Qualifier("hbaseConfiguration")
	private Configuration config;
	@Autowired
	private HBaseAdmin admin;
	@Autowired
	private HbaseTemplate hbaseTemplate;

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
			HBaseTable table = cls.getAnnotation(HBaseTable.class);
			if (table == null) {
				continue;
			}
			String tableNm = table.value();
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
	 * Add records.
	 * 
	 * @param entity
	 * @throws IllegalAccessException
	 */
	public void put(Object entity) throws IllegalAccessException {
		Class<?> cls = entity.getClass();
		String tableName = getTableName(cls);
		// Get row key
		Object rowKyeObj = ObjectUtility.readField(entity, "rowKey");
		byte[] rowKey = ((HBaseRowKey) rowKyeObj).toBytes();
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
				Map<HBaseQualifier, HBaseValue> qvMap = (Map<HBaseQualifier, HBaseValue>) ObjectUtility
						.readField(colFamObj, qvField.getName());
				for (Map.Entry<HBaseQualifier, HBaseValue> entry : qvMap
						.entrySet()) {
					byte[] qualifier = entry.getKey().toBytes();
					byte[] value = entry.getValue().toBytes();
					put.add(columnFamily, qualifier, value);
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

	public Object get(HBaseRowKey rowKey) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		Object tableObj = ObjectUtility.getOuterObject(rowKey);
		Class<?> tableCls = tableObj.getClass();
		String tableName = getTableName(tableCls);
		String rowName = new String(rowKey.toBytes());
		final String[] colFamArr = getColumnFamilyNames(tableCls);
		return hbaseTemplate.get(tableName, rowName, new RowMapper<Object>() {
			@Override
			public Object mapRow(Result result, int rowNum) throws Exception {
				for (String colFamNm : colFamArr) {
					NavigableMap<byte[], byte[]> map = result
							.getFamilyMap(Bytes.toBytes(colFamNm));
					for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
						
						
						
						System.out.println(new String(entry.getKey()) + "/"
								+ new String(entry.getValue()));
						
						
					}
					
					System.err.println("finish");
					
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
		List<String> colFamNms = convertToFiledNames(colFamFields);
		return colFamNms.toArray(new String[colFamNms.size()]);
	}

	private String getTableName(Class<?> cls) {
		HBaseTable table = cls.getAnnotation(HBaseTable.class);
		if (table == null) {
			throw new RuntimeException("Not a table entity !!!");
		}
		return table.value();
	}
}
