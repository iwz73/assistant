package idv.hsiehpinghan.commonsdbcp2assistant.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BasicDataSourceTest {
	private final static String DRIVER = "org.h2.Driver";
	private final static String URL = "jdbc:h2:~/commons-dbcp2-assistant";
	private final static String SQL = "SELECT 1";

	@Test
	public void test() throws Exception {
		DataSource dataSource = generateBasicDataSource();
		try (Connection connection = dataSource.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL);) {
			int columnCount = resultSet.getMetaData().getColumnCount();
			while (resultSet.next()) {
				for (int i = 1; i <= columnCount; i++) {
					int result = resultSet.getInt(i);
					Assert.assertEquals(result, 1);
					System.err.println(result);
				}
			}
		}
	}

	public BasicDataSource generateBasicDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(DRIVER);
		ds.setUrl(URL);
		return ds;
	}

	public static void printDataSourceStats(DataSource ds) {
		BasicDataSource bds = (BasicDataSource) ds;
		System.out.println("NumActive: " + bds.getNumActive());
		System.out.println("NumIdle: " + bds.getNumIdle());
	}

	public static void shutdownDataSource(DataSource ds) throws SQLException {
		BasicDataSource bds = (BasicDataSource) ds;
		bds.close();
	}
}
