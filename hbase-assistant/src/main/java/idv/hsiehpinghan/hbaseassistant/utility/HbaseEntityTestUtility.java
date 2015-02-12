package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.repository.RepositoryBase;

import java.io.IOException;

public class HbaseEntityTestUtility {
	public static void dropAndCreateTargetTable(RepositoryBase repo)
			throws IOException {
		String tableName = repo.getTargetTableName();
		if (repo.isTableExists(tableName)) {
			repo.dropTable(tableName);
			Class<? extends HBaseTable> clazz = repo.getTargetTableClass();
			repo.createTable(clazz);
		}
	}
}
