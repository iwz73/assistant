package idv.hsiehpinghan.hbaseassistant.repository;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.assistant.HbaseAssistant;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IRepositoryBase {
	/**
	 * Create table.
	 * 
	 * @param clazz
	 * @throws IOException
	 */
	void createTable(Class<? extends HBaseTable> clazz) throws IOException;

	/**
	 * Check if table exists.
	 * 
	 * @param tableName
	 * @return
	 * @throws IOException
	 */
	boolean isTableExists(String tableName) throws IOException;

	/**
	 * Drop table.
	 * 
	 * @param tableName
	 * @throws IOException
	 */
	void dropTable(String tableName) throws IOException;

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
	boolean exists(HBaseRowKey rowKey) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException, InstantiationException, IOException;

	/**
	 * Get HBase assistant.
	 * 
	 * @return
	 */
	HbaseAssistant getHbaseAssistant();

	/**
	 * Get this repository's target table name.
	 * 
	 * @return
	 */
	String getTargetTableName();

	/**
	 * Get this repository's target table class.
	 * 
	 * @return
	 */
	Class<? extends HBaseTable> getTargetTableClass();
}
