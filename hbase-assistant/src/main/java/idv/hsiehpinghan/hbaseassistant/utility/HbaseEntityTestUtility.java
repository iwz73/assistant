package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseRowKey;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.repository.RepositoryBase;

import java.io.IOException;

import org.testng.internal.junit.ArrayAsserts;

public class HbaseEntityTestUtility {
//	public static void toBytesFromBytes(HBaseRowKey rowKey) {
//		byte[] expecteds = rowKey.getBytes();
//		rowKey.setBytes(expecteds);
//		byte[] actuals = rowKey.getBytes();
//		ArrayAsserts.assertArrayEquals(expecteds, actuals);
//	}
//
//	public static void toBytesFromBytes(HBaseColumnQualifier qualifier) {
//		byte[] expecteds = qualifier.getBytes();
//		qualifier.setBytes(expecteds);
//		byte[] actuals = qualifier.getBytes();
//		ArrayAsserts.assertArrayEquals(expecteds, actuals);
//	}
//
//	public static void toBytesFromBytes(HBaseValue value) {
//		byte[] expecteds = value.toBytes();
//		value.fromBytes(expecteds);
//		byte[] actuals = value.toBytes();
//		ArrayAsserts.assertArrayEquals(expecteds, actuals);
//	}

	public static void dropAndCreateTargetTable(RepositoryBase repo) throws IOException {
		String tableName = repo.getTargetTableName();
		if (repo.isTableExists(tableName)) {
			repo.dropTable(tableName);
			Class<? extends HBaseTable> clazz = repo.getTargetTableClass();
			repo.createTable(clazz);
		}
	}
}
