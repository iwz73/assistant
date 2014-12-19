package idv.hsiehpinghan.hbaseassistant.utility;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.enumeration.TableOperation;
import idv.hsiehpinghan.hbaseassistant.model.TestTable;
import idv.hsiehpinghan.hbaseassistant.model.TestTable.ColFam1;
import idv.hsiehpinghan.hbaseassistant.model.TestTable.ColFam2;
import idv.hsiehpinghan.hbaseassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HbaseAssistantTest {
	private final String tableName = "TEST_TABLE";
	private final String[] colFamilies = { "COLFAM_1", "COLFAM_2" };
	private final String packageName = "idv.hsiehpinghan.hbaseassistant.model";
	private final String tableName2 = TestTable.class.getSimpleName();
	private HbaseAssistant hbaseAssistant;
	private TestTable testTable;

	@BeforeClass
	public void beforeClass() throws IOException {
		setObjects();
		dropTables();
	}

	@Test
	public void createTable() throws IOException {
		hbaseAssistant.createTable(tableName, colFamilies);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName));
	}

	@Test(dependsOnMethods = { "createTable" })
	public void dropTable() throws IOException {
		hbaseAssistant.dropTable(tableName);
		Assert.assertFalse(hbaseAssistant.isTableExists(tableName));
	}

	@Test
	public void scanAndCreateTable() throws ClassNotFoundException, IOException {
		Assert.assertFalse(hbaseAssistant.isTableExists(tableName2));
		hbaseAssistant.scanAndCreateTable(packageName, TableOperation.ADD_NONEXISTS);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName2));
		hbaseAssistant.scanAndCreateTable(packageName, TableOperation.DROP_CREATE);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName2));
	}
	
	@Test(dependsOnMethods = { "scanAndCreateTable" })
	public void put() throws IllegalAccessException, IOException {
		testTable = createTestTable();
		hbaseAssistant.put(testTable);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName2));
	}
	
	@Test(dependsOnMethods = { "put" })
	public void get() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
		TestTable table = new TestTable();
		TestTable.Key rowKey = createRowKey(table);
		HBaseTable hBaseTable = hbaseAssistant.get(rowKey);
		
		System.err.println(hBaseTable);
		System.err.println(testTable);
		
		Assert.assertTrue(EqualsBuilder.reflectionEquals(hBaseTable, testTable));
	}
	
	private TestTable.Key createRowKey(TestTable table) {
		TestTable.Key rowKey = table.new Key("TestId", 3);
		return rowKey;
	}
	
	private ColFam1 createColFam1(TestTable table) {
		ColFam1 colFam = table.new ColFam1();
		colFam.add("qual_1a", DateUtils.addSeconds(Calendar.getInstance().getTime(), 10), new BigDecimal("11"));
		colFam.add("qual_1a", DateUtils.addSeconds(Calendar.getInstance().getTime(), 20), new BigDecimal("12"));
		colFam.add("qual_1b", DateUtils.addSeconds(Calendar.getInstance().getTime(), 30), new BigDecimal("13"));
		colFam.add("qual_1b", DateUtils.addSeconds(Calendar.getInstance().getTime(), 40), new BigDecimal("14"));
		return colFam;
	}
	
	private ColFam2 createColFam2(TestTable table) {
		ColFam2 colFam = table.new ColFam2();
		colFam.add("qual_2a", DateUtils.addSeconds(Calendar.getInstance().getTime(), 10), new BigDecimal("21"));
		colFam.add("qual_2a", DateUtils.addSeconds(Calendar.getInstance().getTime(), 20), new BigDecimal("22"));
		colFam.add("qual_2b", DateUtils.addSeconds(Calendar.getInstance().getTime(), 30), new BigDecimal("23"));
		colFam.add("qual_2b", DateUtils.addSeconds(Calendar.getInstance().getTime(), 40), new BigDecimal("24"));
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
	
	private TestTable createTestTable() {
		TestTable table = new TestTable();
		TestTable.Key rowKey = createRowKey(table);
		ColFam1 colFam1 = createColFam1(table);
		ColFam2 colFam2 = createColFam2(table);
		table.setRowKey(rowKey);
		table.setCf1(colFam1);
		table.setCf2(colFam2);
		return table;
	}
}
