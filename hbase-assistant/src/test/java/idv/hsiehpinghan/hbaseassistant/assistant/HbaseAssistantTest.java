package idv.hsiehpinghan.hbaseassistant.assistant;

import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnFamily;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseColumnQualifier;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseTable;
import idv.hsiehpinghan.hbaseassistant.abstractclass.HBaseValue;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ColumnNameFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.QualifierColumnNameFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.RowKey;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValueFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValuesFamily;
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
import java.util.TreeSet;

import junit.framework.Assert;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.FamilyFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FuzzyRowFilter;
import org.apache.hadoop.hbase.filter.KeyOnlyFilter;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Pair;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

public class HbaseAssistantTest {
	private final Class<TestTable> tableClass = TestTable.class;
	private final String tableName = tableClass.getSimpleName();
	private HbaseAssistant hbaseAssistant;
	private int maxVersions = 10;
	private Date date;
	private Date minDate;
	private Date maxDate;
	private Filter filter;
	private Date ver;
	private Enumeration enumeration = Enumeration.TYPE1;
	private String string = "string";
	private String elementId = "elementId";
	private Date instant;
	private String unitType = "unitType";
	private BigDecimal value = new BigDecimal("1.1");
	private int year = 2015;
	private int month = 1;
	private int pageSize = 1;
	private String operatingIncomeOfComment = "operatingIncomeOfComment";
	private BigInteger operatingIncomeOfCurrentMonth = new BigInteger("2");
	private BigDecimal operatingIncomeOfDifferentPercent = new BigDecimal("3.3");

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		hbaseAssistant = applicationContext.getBean(HbaseAssistant.class);
		date = DateUtils.parseDate("2012/03/11", "yyyy/MM/dd");
		minDate = DateUtils.parseDate("2010/01/01", "yyyy/MM/dd");
		maxDate = DateUtils.parseDate("2020/01/01", "yyyy/MM/dd");
		ver = DateUtils.parseDate("2015/01/01", "yyyy/MM/dd");
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
		testGetColumnNameFamily(entity);
	}

	@Test(dependsOnMethods = { "put" })
	public void exist() throws Exception {
		TestTable entity = createTestEntity(0);
		Assert.assertTrue(hbaseAssistant.exist(entity.getRowKey()));
	}

	@Test(dependsOnMethods = { "put" })
	public void scan() throws Exception {
		TestNoFilter();
		TestKeyOnlyFilter();
		TestMultipleColumnPrefixFilter();
		TestFamilyFilter();
		TestRowFilter();
		TestPageFilter();
		TestFuzzyRowFilter();
	}

	@Test(dependsOnMethods = { "put" })
	public void getRowAmount() {
		int size = hbaseAssistant.getRowAmount(TestTable.class);
		Assert.assertEquals(3, size);
	}
	
	private void TestNoFilter() {
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(TestTable.class);
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.first();
		Assert.assertEquals(2, entity.getColumnNameFamily()
				.getQualifierVersionValueSet().size());
	}

	private void TestKeyOnlyFilter() {
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				new KeyOnlyFilter());
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.first();
		valueEmptyTest(entity.getColumnNameFamily());
	}

	private void TestMultipleColumnPrefixFilter() {
		byte[][] prefixes = new byte[][] { ByteConvertUtility
				.toBytes(ColumnNameFamily.ENUMERATION) };
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				new MultipleColumnPrefixFilter(prefixes));
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.first();
		Assert.assertEquals(1, entity.getColumnNameFamily()
				.getQualifierVersionValueSet().size());
	}

	private void TestFamilyFilter() {
		Filter famFilter = new FamilyFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(
						ByteConvertUtility.toBytes("columnNameFamily")));
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				famFilter);
		Assert.assertEquals(3, entities.size());
		TestTable entity = (TestTable) entities.first();
		Assert.assertEquals(2, entity.getColumnNameFamily()
				.getQualifierVersionValueSet().size());
		Assert.assertEquals(0, entity.getQualifierColumnNameFamily()
				.getQualifierVersionValueSet().size());
	}

	private void TestRowFilter() throws Exception {
		Filter rowFilter = new org.apache.hadoop.hbase.filter.RowFilter(
				CompareFilter.CompareOp.EQUAL, new BinaryComparator(
						createTestEntity(0).getRowKey().getBytes()));
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				rowFilter);
		Assert.assertEquals(1, entities.size());
		TestTable entity = (TestTable) entities.first();
		Assert.assertEquals(2, entity.getColumnNameFamily()
				.getQualifierVersionValueSet().size());
	}

	private void TestPageFilter() throws Exception {
		PageFilter pageFilter = new org.apache.hadoop.hbase.filter.PageFilter(
				pageSize);
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				pageFilter);
		Assert.assertEquals(pageSize, entities.size());
	}

	private void TestFuzzyRowFilter() throws Exception {
		String stockCode = generateStockCode(0);
		TestTable entity = new TestTable();
		TestTable.RowKey rowKey = entity.new RowKey(stockCode, date, entity);
		List<Pair<byte[], byte[]>> fuzzyKeysData = new ArrayList<Pair<byte[], byte[]>>();
		Pair<byte[], byte[]> pair = new Pair<byte[], byte[]>(rowKey.getBytes(),
				rowKey.getFuzzyBytes(stockCode, null));
		fuzzyKeysData.add(pair);
		FuzzyRowFilter fuzzyRowFilter = new org.apache.hadoop.hbase.filter.FuzzyRowFilter(
				fuzzyKeysData);
		TreeSet<HBaseTable> entities = hbaseAssistant.scan(TestTable.class,
				fuzzyRowFilter);
		Assert.assertEquals(1, entities.size());
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

	private void testGetColumnNameFamily(TestTable entity) {
		ColumnNameFamily fam = entity.getColumnNameFamily();
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
		entity.new RowKey(generateStockCode(id), date, entity);
	}

	private void generateColumnNameFamily(TestTable entity) throws Exception {
		ColumnNameFamily fam = entity.getColumnNameFamily();
		for (int i = 0; i < 3; ++i) {
			fam.setEnumeration(DateUtils.addDays(ver, i), enumeration);
			fam.setString(DateUtils.addDays(ver, -i), string);
		}
	}

	private void generateValuesFamily(TestTable entity) throws Exception {
		ValuesFamily fam = entity.getValuesFamily();
		fam.setValuesValue(elementId, enumeration, instant, ver, unitType,
				value);
	}

	private void generateValueFamily(TestTable entity) throws Exception {
		ValueFamily fam = entity.getValueFamily();
		fam.set(elementId, enumeration, instant, ver, value);
	}

	private void generateQualifierColumnNameFamily(TestTable entity)
			throws Exception {
		QualifierColumnNameFamily fam = entity.getQualifierColumnNameFamily();
		fam.setOperatingIncomeOfComment(year, month, ver,
				operatingIncomeOfComment);
		fam.setOperatingIncomeOfCurrentMonth(year, month, ver,
				operatingIncomeOfCurrentMonth);
		fam.setOperatingIncomeOfDifferentPercent(year, month, ver,
				operatingIncomeOfDifferentPercent);
	}

	private TestTable createTestEntity(int id) throws Exception {
		TestTable entity = new TestTable();
		generateRowKey(entity, id);
		generateColumnNameFamily(entity);
		generateValuesFamily(entity);
		generateValueFamily(entity);
		generateQualifierColumnNameFamily(entity);
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
