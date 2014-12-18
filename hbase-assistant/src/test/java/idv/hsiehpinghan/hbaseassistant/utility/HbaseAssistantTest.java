package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.hbaseassistant.enumeration.TableOperation;
import idv.hsiehpinghan.hbaseassistant.model.TestTable;
import idv.hsiehpinghan.hbaseassistant.model.TestTable.ColFam;
import idv.hsiehpinghan.hbaseassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HbaseAssistantTest {
	private final String tableName = "TEST_TABLE";
	private final String[] colFamilies = { "COLFAM_1", "COLFAM_2" };
	private final String packageName = "idv.hsiehpinghan.hbaseassistant.model";
	private final String tableName2 = "TestTable";
	private HbaseAssistant hbaseAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		setObjects();
//		dropTables();
	}

//	@Test
	public void createTable() throws IOException {
		hbaseAssistant.createTable(tableName, colFamilies);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName));
	}

//	@Test(dependsOnMethods = { "createTable" })
	public void dropTable() throws IOException {
		hbaseAssistant.dropTable(tableName);
		Assert.assertFalse(hbaseAssistant.isTableExists(tableName));
	}

//	@Test
	public void scanAndCreateTable() throws ClassNotFoundException, IOException {
		Assert.assertFalse(hbaseAssistant.isTableExists(tableName2));
		hbaseAssistant.scanAndCreateTable(packageName, TableOperation.ADD_NONEXISTS);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName2));
		hbaseAssistant.scanAndCreateTable(packageName, TableOperation.DROP_CREATE);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName2));
	}
	
//	@Test(dependsOnMethods = { "scanAndCreateTable" })
//	@Test
	public void put() throws IllegalAccessException, IOException {
		TestTable table = new TestTable();
		TestTable.Key rowKey = createRowKey(table);
		ColFam colFam = createColFam(table);
		TestTable entity = new TestTable(rowKey, colFam);
		hbaseAssistant.put(entity);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName2));
	}
	
//	@Test(dependsOnMethods = { "put" })
	@Test
	public void get() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		TestTable table = new TestTable();
		TestTable.Key rowKey = createRowKey(table);
		hbaseAssistant.get(rowKey);
	}
	
	private TestTable.Key createRowKey(TestTable table) {
		TestTable.Key rowKey = table.new Key("TestId", 3);
		return rowKey;
	}
	
	private ColFam createColFam(TestTable table) {
		ColFam colFam = table.new ColFam();
		Date dt = Calendar.getInstance().getTime();
		colFam.add("qual_1", dt, new BigDecimal("111"));
		colFam.add("qual_2", dt, new BigDecimal("222"));
		return colFam;
	}
	
	private void setObjects() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hbaseAssistant = applicationContext.getBean(HbaseAssistant.class);
	}

	private void dropTables() throws IOException {
		if (hbaseAssistant.isTableExists(tableName)) {
			hbaseAssistant.dropTable(tableName);
		}
		if (hbaseAssistant.isTableExists(tableName2)) {
			hbaseAssistant.dropTable(tableName2);
		}		
	}
}
