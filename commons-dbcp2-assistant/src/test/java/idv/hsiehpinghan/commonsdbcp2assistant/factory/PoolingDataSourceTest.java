package idv.hsiehpinghan.commonsdbcp2assistant.factory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PoolingDataSourceTest {
	private final static String DRIVER = "org.h2.Driver";
	private final static String URL = "jdbc:h2:~/commons-dbcp2-assistant";
	private final static String SQL = "SELECT 1";

	@Test
	public void test() throws Exception {
		DataSource dataSource = generatePoolingDataSource();
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

	public PoolingDataSource<PoolableConnection> generatePoolingDataSource() {
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(URL, null);
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);
		poolableConnectionFactory.setPool(connectionPool);
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);
		return dataSource;
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
