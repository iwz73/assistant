package idv.hsiehpinghan.hbaseassistant.repository;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.assistant.HbaseAssistant;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public abstract class RepositoryBase {
	/**
	 * Create table.
	 * 
	 * @param clazz
	 * @throws IOException
	 */
	public void createTable(Class<? extends HBaseTable> clazz)
			throws IOException {
		getHbaseAssistant().createTable(clazz);
	}

	/**
	 * Check if table exists.
	 * 
	 * @param tableName
	 * @return
	 * @throws IOException
	 */
	public boolean isTableExists(String tableName) throws IOException {
		return getHbaseAssistant().isTableExists(tableName);
	}

	/**
	 * Drop table.
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	public void dropTable(String tableName) throws IOException {
		getHbaseAssistant().dropTable(tableName);
	}

	/**
	 * Check if entity exists.
	 * 
	 * @param rowKey
	 * @return
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws IOException
	 */
	public boolean exists(HBaseRowKey rowKey) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException, IOException {
		return getHbaseAssistant().exist(rowKey);
	}

	/**
	 * Get this repository's target table name.
	 * 
	 * @return
	 */
	public String getTargetTableName() {
		return getTargetTableClass().getSimpleName();
	}

	/**
	 * Put entities.
	 * 
	 * @param entities
	 * @throws IllegalAccessException
	 */
	public void put(List<? extends HBaseTable> entities)
			throws IllegalAccessException {
		getHbaseAssistant().put(entities);
	}

	/**
	 * Put entity.
	 * 
	 * @param entity
	 * @throws IllegalAccessException
	 */
	public void put(HBaseTable entity) throws IllegalAccessException {
		getHbaseAssistant().put(entity);
	}

	/**
	 * Get row key.
	 * 
	 * @param rowKey
	 * @return
	 * @throws IOException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 */
	public HBaseTable get(HBaseRowKey rowKey) throws IllegalAccessException,
			NoSuchMethodException, SecurityException, InstantiationException,
			IllegalArgumentException, InvocationTargetException, IOException {
		return getHbaseAssistant().get(rowKey);
	}

	/**
	 * Get row Amount.
	 * 
	 * @return
	 */
	public int getRowAmount() {
		return getHbaseAssistant().getRowAmount(getTargetTableClass());
	}

	/**
	 * Get this repository's target table class.
	 * 
	 * @return
	 */
	public abstract Class<? extends HBaseTable> getTargetTableClass();

	/**
	 * Get HBase assistant.
	 * 
	 * @return
	 */
	protected abstract HbaseAssistant getHbaseAssistant();
}
