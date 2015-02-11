package idv.hsiehpinghan.hbaseassistant.assistant;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.DailyFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.FinancialReportFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.InfoFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.MonthlyFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.RowKey;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.XbrlInstanceFamily;
import idv.hsiehpinghan.hbaseassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hbaseassistant.enumeration.TableOperation;
import idv.hsiehpinghan.hbaseassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Set;

import junit.framework.Assert;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class HbaseAssistantTest {
	private final Class<TestTable> tableClass = TestTable.class;
	private final String tableName = tableClass.getSimpleName();
	private HbaseAssistant hbaseAssistant;
	private int maxVersions = 10;
	private Date minDate;
	private Date maxDate;
	private Filter filter;
	private Date ver;
	private Enumeration enumeration = Enumeration.TYPE1;
	private String string = "string";
	private String elementId = "elementId";
	private Date instant;
	private BigDecimal value = new BigDecimal("1.1");
	private int year = 2015;
	private int month = 1;
	private String operatingIncomeOfComment = "operatingIncomeOfComment";
	private BigInteger operatingIncomeOfCurrentMonth = new BigInteger("2");
	private BigDecimal operatingIncomeOfDifferentPercent = new BigDecimal("3.3");
	private BigDecimal closingConditionOfOpeningPrice = new BigDecimal("4.4");
	private Date date;
	private BigInteger closingConditionOfStockAmount = new BigInteger("5");

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hbaseAssistant = applicationContext.getBean(HbaseAssistant.class);
		minDate = DateUtils.parseDate("2010/01/01", "yyyy/MM/dd");
		maxDate = DateUtils.parseDate("2020/01/01", "yyyy/MM/dd");
		ver = DateUtils.parseDate("2015/01/01", "yyyy/MM/dd");
	}

	@AfterClass
	public void afterClass() throws Exception {
		hbaseAssistant.dropTable(tableName);
	}

	@Test
	public void afterPropertiesSet() throws IOException {
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName));
	}

	@Test(dependsOnMethods = { "afterPropertiesSet" })
	public void createTable() throws IOException {
		if (hbaseAssistant.isTableExists(tableName)) {
			hbaseAssistant.dropTable(tableName);
		}
		// Test create table.
		Assert.assertFalse(hbaseAssistant.isTableExists(tableName));
		String[] colFamilies = hbaseAssistant.getColumnFamilyNames(tableClass);
		hbaseAssistant.createTable(tableName, colFamilies);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName));

		// Test create column families.
		HColumnDescriptor[] colDescs = hbaseAssistant
				.getColumnDescriptors(tableName);
		String[] acutualColFamilies = convert(colDescs);
		ArrayAsserts.assertArrayEquals(colFamilies, acutualColFamilies);
	}

	@Test(dependsOnMethods = { "createTable" })
	public void dropTable() throws IOException {
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName));
		hbaseAssistant.dropTable(tableName);
		Assert.assertFalse(hbaseAssistant.isTableExists(tableName));
	}

	@Test(dependsOnMethods = { "dropTable" })
	public void createTables() throws ClassNotFoundException, IOException {
		Class<?>[] classes = new Class<?>[1];
		classes[0] = TestTable.class;
		hbaseAssistant.createTables(classes, TableOperation.ADD_NONEXISTS);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName));
		hbaseAssistant.createTables(classes, TableOperation.DROP_CREATE);
		Assert.assertTrue(hbaseAssistant.isTableExists(tableName));
	}

	@Test(dependsOnMethods = { "createTables" })
	public void put() throws Exception {
		// Test put put.
		TestTable entity = createTestEntity(0);
		hbaseAssistant.put(entity);
		Assert.assertTrue(hbaseAssistant.isRowExists(entity.getRowKey()));

		// Test put puts.
		List<TestTable> entities = new ArrayList<TestTable>(2);
		TestTable entity1 = createTestEntity(1);
		entities.add(entity1);
		TestTable entity2 = createTestEntity(2);
		entities.add(entity2);
		hbaseAssistant.put(entities);
		Assert.assertTrue(hbaseAssistant.isRowExists(entity1.getRowKey()));
		Assert.assertTrue(hbaseAssistant.isRowExists(entity2.getRowKey()));
	}

	@Test(dependsOnMethods = { "put" })
	public void get() throws Exception {
		int id = 0;
		TestTable entity = new TestTable();
		generateRowKey(entity, id);
		hbaseAssistant.get(entity, maxVersions, minDate, maxDate, filter);
		testGetRowKey(entity, id);
		testGetInfoFamily(entity);
	}

	@Test(dependsOnMethods = { "put" })
	public void exist() throws Exception {
		TestTable entity = createTestEntity(0);
		Assert.assertTrue(hbaseAssistant.exist(entity.getRowKey()));
	}

	private void TestNoFilter() {
		List<HBaseTable> entities = hbaseAssistant.scan(TestTable.class);
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.get(0);
		Assert.assertEquals(2, entity.getInfoFamily()
				.getQualifierVersionValueSet().size());
	}

	private void TestKeyOnlyFilter() {
		List<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				new KeyOnlyFilter());
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.get(0);
		valueEmptyTest(entity.getInfoFamily());
	}

	private void TestMultipleColumnPrefixFilter() {
		byte[][] prefixes = new byte[][] { ByteConvertUtility
				.toBytes(InfoFamily.ENUMERATION) };
		List<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				new MultipleColumnPrefixFilter(prefixes));
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.get(0);
		Assert.assertEquals(1, entity.getInfoFamily()
				.getQualifierVersionValueSet().size());
	}

	private void TestFamilyFilter() {
		Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(ByteConvertUtility.toBytes("infoFamily")));
		List<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				famFilter);
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.get(0);
		Assert.assertEquals(2, entity.getInfoFamily()
				.getQualifierVersionValueSet().size());
		Assert.assertEquals(0, entity.getMonthlyFamily()
				.getQualifierVersionValueSet().size());
	}

	private void TestRowFilter() throws Exception {
		Filter rowFilter = new org.apache.hadoop.hbase.filter.RowFilter(
				CompareFilter.CompareOp.EQUAL, new BinaryComparator(
						createTestEntity(0).getRowKey().getBytes()));
		List<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				rowFilter);
		Assert.assertEquals(1, entities.size());
		TestTable entity = (TestTable) entities.get(0);
		Assert.assertEquals(2, entity.getInfoFamily()
				.getQualifierVersionValueSet().size());
	}

	@Test(dependsOnMethods = { "put" })
	public void scan() throws Exception {
		TestNoFilter();
		TestKeyOnlyFilter();
		TestMultipleColumnPrefixFilter();
		TestFamilyFilter();
		TestRowFilter();
	}

	@Test(dependsOnMethods = { "put" })
	public void getRowAmount() {
		int size = hbaseAssistant.getRowAmount(TestTable.class);
		Assert.assertEquals(3, size);
	}

	private void valueEmptyTest(HBaseColumnFamily family) {
		for (Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualEnt : family
				.getQualifierVersionValueSet()) {
			for (Entry<Date, HBaseValue> verEnt : qualEnt.getValue().entrySet()) {
				Assert.assertNull(verEnt.getValue());
			}
		}
	}

	private void testGetRowKey(TestTable entity, int id) throws Exception {
		RowKey rowKey = (RowKey) entity.getRowKey();
		Assert.assertEquals(generateStockCode(id), rowKey.getStockCode());
	}

	private String generateStockCode(int id) {
		return "2330_" + id;
	}

	private void testGetInfoFamily(TestTable entity) {
		InfoFamily fam = entity.getInfoFamily();
		Set<Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>> qualSet = fam
				.getQualifierVersionValueSet();
		Assert.assertEquals(2, qualSet.size());
		for (Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualEntry : qualSet) {
			NavigableMap<Date, HBaseValue> verMap = qualEntry.getValue();
			Assert.assertEquals(3, verMap.size());
			// Test verMap's descendingMap.
			Date beforeDate = null;
			for (Entry<Date, HBaseValue> verEntry : verMap.descendingMap()
					.entrySet()) {
				if (beforeDate == null) {
					beforeDate = verEntry.getKey();
				} else {
					Date currentDate = verEntry.getKey();
					Assert.assertTrue(currentDate.getTime() < beforeDate
							.getTime());
					beforeDate = currentDate;
				}
			}
		}
	}

	private void generateRowKey(TestTable entity, int id) throws ParseException {
		entity.new RowKey(generateStockCode(id), entity);
	}

	private void generateInfoFamily(TestTable entity) throws Exception {
		InfoFamily fam = entity.getInfoFamily();
		for (int i = 0; i < 3; ++i) {
			fam.setEnumeration(DateUtils.addDays(ver, i), enumeration);
			fam.setString(DateUtils.addDays(ver, -i), string);
		}
	}

	private void generateXbrlInstanceFamily(TestTable entity) throws Exception {
		XbrlInstanceFamily fam = entity.getXbrlInstanceFamily();
		fam.set(elementId, enumeration, instant, ver, value);
	}

	private void generateFinancialReportFamily(TestTable entity)
			throws Exception {
		FinancialReportFamily fam = entity.getFinancialReportFamily();
		fam.set(elementId, enumeration, instant, ver, string);
	}

	private void generateMonthlyFamily(TestTable entity) throws Exception {
		MonthlyFamily fam = entity.getMonthlyFamily();
		fam.setOperatingIncomeOfComment(year, month, ver,
				operatingIncomeOfComment);
		fam.setOperatingIncomeOfCurrentMonth(year, month, ver,
				operatingIncomeOfCurrentMonth);
		fam.setOperatingIncomeOfDifferentPercent(year, month, ver,
				operatingIncomeOfDifferentPercent);
	}

	private void generateDailyFamily(TestTable entity) throws Exception {
		DailyFamily fam = entity.getDailyFamily();
		fam.setClosingConditionOfOpeningPrice(date, ver,
				closingConditionOfOpeningPrice);
		fam.setClosingConditionOfStockAmount(date, ver,
				closingConditionOfStockAmount);
	}

	// private void generateFamily2(TestTable entity) throws Exception {
	// TestFamily2 family2 = entity.getFamily2();
	// for (int i = 1; i <= 3; ++i) {
	// String qual = "qual2_" + i;
	// Date date = DateUtils.parseDate("2015/02/0" + i, "yyyy/MM/dd");
	// Date valueDate2 = new Date();
	// String valueString2 = this.valueString2 + i;
	// int valueInt2 = 20 + i;
	// add(family2, qual, date, valueDate2, valueString2, valueInt2);
	// }
	// }

	// private void add(TestFamily2 family2, String qual, Date date,
	// Date valueDate2, String valueString2, int valueInt2) {
	// family2.add(qual, date, valueDate2, valueString2, valueInt2);
	// }

	private TestTable createTestEntity(int id) throws Exception {
		TestTable entity = new TestTable();
		generateRowKey(entity, id);
		generateInfoFamily(entity);
		generateXbrlInstanceFamily(entity);
		generateFinancialReportFamily(entity);
		generateMonthlyFamily(entity);
		generateDailyFamily(entity);
		return entity;
	}

	private String[] convert(HColumnDescriptor[] colDescs) {
		int size = colDescs.length;
		String[] strArr = new String[size];
		for (int i = 0; i < size; ++i) {
			strArr[i] = colDescs[i].getNameAsString();
		}
		return strArr;
	}
}
