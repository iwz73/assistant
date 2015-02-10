package idv.hsiehpinghan.hbaseassistant.assistant;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily1;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily1.TestValue1;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily2;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.TestFamily2.TestValue2;
import idv.hsiehpinghan.hbaseassistant.enumeration.TableOperation;
import idv.hsiehpinghan.hbaseassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.hbaseassistant.utility.ByteConvertUtility;

import java.io.IOException;
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
	private final String valueString1 = "valueString1_";
	private final String valueString2 = "valueString2_";
	private int maxVersions;
	private Date minDate;
	private Date maxDate;
	private Filter filter;
	private HbaseAssistant hbaseAssistant;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hbaseAssistant = applicationContext.getBean(HbaseAssistant.class);
	}

	@AfterClass
	public void afterClass() throws Exception {
		// hbaseAssistant.dropTable(tableName);
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
		maxVersions = 10;
		minDate = DateUtils.parseDate("2010/01/01", "yyyy/MM/dd");
		maxDate = DateUtils.parseDate("2020/01/01", "yyyy/MM/dd");
		filter = null;
		testGetRowKey();
		testGetColumnFamily1();
		testGetColumnFamily2();
	}

	@Test(dependsOnMethods = { "put" })
	public void exist() throws Exception {
		TestTable entity = createTestEntity(0);
		Assert.assertTrue(hbaseAssistant.exist(entity.getRowKey()));
	}

	@Test(dependsOnMethods = { "put" })
	public void scan() throws Exception {
		TestTable entity;
		// Test no filter.
		entity = (TestTable) hbaseAssistant.scan(TestTable.class).get(0);
		Assert.assertEquals(1, entity.getFamily1()
				.getQualifierVersionValueSet().size());
		Assert.assertEquals(3, entity.getFamily2()
				.getQualifierVersionValueSet().size());
		// Test keyOnlyFilter.
		entity = (TestTable) hbaseAssistant.scan(TestTable.class,
				new KeyOnlyFilter()).get(0);
		valueEmptyTest(entity.getFamily1());
		valueEmptyTest(entity.getFamily2());
		// Test multipleColumnPrefixFilter.
		byte[][] prefixes = new byte[][] { ByteConvertUtility.toBytes("qual1") };
		entity = (TestTable) hbaseAssistant.scan(TestTable.class,
				new MultipleColumnPrefixFilter(prefixes)).get(0);
		Assert.assertEquals(1, entity.getFamily1()
				.getQualifierVersionValueSet().size());
		Assert.assertEquals(0, entity.getFamily2()
				.getQualifierVersionValueSet().size());
		// Test familyFilter.
		Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(ByteConvertUtility.toBytes("family2")));
		entity = (TestTable) hbaseAssistant.scan(TestTable.class, famFilter)
				.get(0);
		Assert.assertEquals(0, entity.getFamily1()
				.getQualifierVersionValueSet().size());
		Assert.assertEquals(3, entity.getFamily2()
				.getQualifierVersionValueSet().size());
		// Test rowFilter
		Filter rowFilter = new org.apache.hadoop.hbase.filter.RowFilter(
				CompareFilter.CompareOp.EQUAL, new BinaryComparator(
						createTestEntity(0).getRowKey().getBytes()));
		entity = (TestTable) hbaseAssistant.scan(TestTable.class, rowFilter)
				.get(0);
		Assert.assertEquals(1, entity.getFamily1()
				.getQualifierVersionValueSet().size());
		Assert.assertEquals(3, entity.getFamily2()
				.getQualifierVersionValueSet().size());
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

	private void testGetRowKey() throws Exception {
		TestTable entity = new TestTable();
		generateRowKey(entity, 0);
		hbaseAssistant.get(entity, maxVersions, minDate, maxDate, filter);
	}

	private void testGetColumnFamily1() throws Exception {
		TestTable entity = new TestTable();
		generateRowKey(entity, 0);
		entity.getFamily1();
		hbaseAssistant.get(entity, maxVersions, minDate, maxDate, filter);
		testColumnFamily1(entity.getFamily1());
	}

	private void testColumnFamily1(TestFamily1 family1) {
		Set<Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>> qualSet = family1
				.getQualifierVersionValueSet();
		Assert.assertEquals(1, qualSet.size());
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
				}
				TestValue1 value = (TestValue1) verEntry.getValue();
				Assert.assertTrue(value.getValueString1().startsWith(
						valueString1));
			}
		}
	}

	private void testGetColumnFamily2() throws Exception {
		TestTable entity = new TestTable();
		generateRowKey(entity, 0);
		entity.getFamily2();
		hbaseAssistant.get(entity, maxVersions, minDate, maxDate, filter);
		testColumnFamily2(entity.getFamily2());
	}

	private void testColumnFamily2(TestFamily2 family2) {
		Set<Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>>> qualSet = family2
				.getQualifierVersionValueSet();
		Assert.assertEquals(3, qualSet.size());
		for (Entry<HBaseColumnQualifier, NavigableMap<Date, HBaseValue>> qualEntry : qualSet) {
			NavigableMap<Date, HBaseValue> verMap = qualEntry.getValue();
			Assert.assertEquals(1, verMap.size());

			for (Entry<Date, HBaseValue> verEntry : verMap.descendingMap()
					.entrySet()) {
				TestValue2 value = (TestValue2) verEntry.getValue();
				Assert.assertTrue(value.getValueString2().startsWith(
						valueString2));
			}
		}
	}

	private void generateRowKey(TestTable entity, int plusDayAmt) throws ParseException {
		Date keyDate1 = DateUtils.addDays(DateUtils.parseDate("1978/12/24", "yyyy/MM/dd"), plusDayAmt);
		String keyString1 = "keyString1";
		int keyInt1 = 888;
		entity.new TestRowKey(keyDate1, keyString1, keyInt1, entity);
	}

	private void generateFamily1(TestTable entity) throws Exception {
		TestFamily1 family1 = entity.getFamily1();
		for (int i = 1; i <= 3; ++i) {
			String qual = "qual1";
			Date date = DateUtils.parseDate("2015/01/0" + i, "yyyy/MM/dd");
			Date valueDate1 = DateUtils.addDays(new Date(), i);
			String valueString1 = this.valueString1 + i;
			int valueInt1 = 10 + i;
			add(family1, qual, date, valueDate1, valueString1, valueInt1);
		}
	}

	private void generateFamily2(TestTable entity) throws Exception {
		TestFamily2 family2 = entity.getFamily2();
		for (int i = 1; i <= 3; ++i) {
			String qual = "qual2_" + i;
			Date date = DateUtils.parseDate("2015/02/0" + i, "yyyy/MM/dd");
			Date valueDate2 = new Date();
			String valueString2 = this.valueString2 + i;
			int valueInt2 = 20 + i;
			add(family2, qual, date, valueDate2, valueString2, valueInt2);
		}
	}

	private void add(TestFamily1 family1, String qual, Date date,
			Date valueDate1, String valueString1, int valueInt1) {
		family1.add(qual, date, valueDate1, valueString1, valueInt1);
	}

	private void add(TestFamily2 family2, String qual, Date date,
			Date valueDate2, String valueString2, int valueInt2) {
		family2.add(qual, date, valueDate2, valueString2, valueInt2);
	}

	private TestTable createTestEntity(int plusDayAmt) throws Exception {
		TestTable entity = new TestTable();
		generateRowKey(entity, plusDayAmt);
		generateFamily1(entity);
		generateFamily2(entity);
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
