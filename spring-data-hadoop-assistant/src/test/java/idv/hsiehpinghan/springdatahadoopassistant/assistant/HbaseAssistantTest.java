package idv.hsiehpinghan.springdatahadoopassistant.assistant;

import idv.hsiehpinghan.springdatahadoopassistant.entity.Webpage;
import idv.hsiehpinghan.springdatahadoopassistant.filter.TestFilter;
import idv.hsiehpinghan.springdatahadoopassistant.suit.TestngSuitSetting;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueExcludeFilter;
import org.apache.hadoop.hbase.filter.SkipFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HbaseAssistantTest {
	private static final String TABLE_NAME = "webpage";
	private static final int PAGE_SIZE = 3;
	private HbaseAssistant assistant;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(HbaseAssistant.class);
	}

	@Test
	public void get() throws Exception {
		final int SIZE = 3;
		List<String> rowKeys = new ArrayList<String>(SIZE);
		rowKeys.add("tw.com.ipeen.www:http/comment/103620");
		rowKeys.add("tw.com.ipeen.www:http/comment/104119");
		rowKeys.add("tw.com.ipeen.www:http/comment/117595");
		List<Webpage> entities = assistant.get(TABLE_NAME, rowKeys, generateMultipleColumnPrefixFilter());
		entities.forEach((entity)->{
			System.err.println(entity);
		});
		Assert.assertEquals(entities.size(), SIZE);
	}
	
//	@Test
	public void scan() throws Exception {
//		 TestRowFilter();
		// TestPageFilter();
		// TestSingleColumnValueExcludeFilter();
		TestFilterList();
		// TestTestFilter();
	}

	private void TestTestFilter() throws Exception {
		Filter filter = new TestFilter();
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filter);
		for (Webpage entity : entities) {
			System.err.println(entity.getRowKey() + " / "
					+ entity.getF().getPrevFetchTime());
		}
		Assert.assertTrue(0 < entities.size());
	}

	private void TestFilterList() throws Exception {
//		Filter rowFilter_0 = generateRowFilter_0();
//		Filter rowFilter_1 = generateRowFilter_1();
		Filter rowFilter_2 = generateRowFilter_2();
//		Filter skipFilter = generateSkipFilter();
//		Filter multipleColumnPrefixFilter = generateMultipleColumnPrefixFilter();
		Filter pageFilter = generatePageFilter();
//		FilterList filterList = new FilterList(rowFilter, skipFilter,
//				multipleColumnPrefixFilter, pageFilter);
		FilterList filterList = new FilterList(rowFilter_2, pageFilter);
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filterList);
		for (Webpage entity : entities) {
			System.err.println(entity.getRowKey());
		}
		Assert.assertTrue(0 < entities.size());
	}

	private void TestSingleColumnValueExcludeFilter() throws Exception {
		Filter filter = generateSingleColumnValueExcludeFilter();
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filter);
		for (Webpage entity : entities) {
			System.err.println(entity.getRowKey() + " / "
					+ entity.getF().getPrevFetchTime());
		}
		Assert.assertTrue(0 < entities.size());
	}

	private void TestRowFilter() throws Exception {
		Filter filter = generateRowFilter_2();
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filter);
		for (Webpage entity : entities) {
			System.err.println(entity.getRowKey() + " / " + entity.getF().getPrevFetchTime());
		}
		Assert.assertEquals(1, entities.size());
	}

	private void TestPageFilter() throws Exception {
		Filter filter = generatePageFilter();
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filter);
		for (Webpage entity : entities) {
			System.err.println(entity);
		}
		Assert.assertTrue(PAGE_SIZE < entities.size());
	}

	private Filter generatePageFilter() {
		return new PageFilter(PAGE_SIZE);
	}

	private Filter generateMultipleColumnPrefixFilter() {
		byte[][] prefixes = new byte[][] { Bytes.toBytes("bas"),
				Bytes.toBytes("pts"), Bytes.toBytes("cnt"),
				Bytes.toBytes("sig") };
		return new MultipleColumnPrefixFilter(prefixes);
	}

	private Filter generateRowFilter_0() {
		 return new RowFilter(CompareFilter.CompareOp.GREATER,
		 new BinaryComparator(
		 Bytes.toBytes("tw.com.ipeen.www:http/comment/148690")));
			
	}

	private Filter generateRowFilter_1() {
		return new RowFilter(CompareFilter.CompareOp.LESS,
				new BinaryComparator(Bytes.toBytes("tw.com.ipeen.www:http/shop/82600")));
			
	}
	
	private Filter generateRowFilter_2() {
		return new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator("tw.com.ipeen.www:http/shop/[0-9]*$"));
			
	}
	
	private Filter generateSingleColumnValueExcludeFilter() {
		Date targetDate = Date.from(LocalDate.of(2016, 2, 11).atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
		return new SingleColumnValueExcludeFilter(Bytes.toBytes("f"),
				Bytes.toBytes("pts"), CompareFilter.CompareOp.GREATER,
				Bytes.toBytes(targetDate.getTime()));
	}

	private Filter generateSkipFilter() {
		Date targetDate_0 = Date.from(LocalDate.of(2016, 2, 11).atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
		Filter filter_0 = new SingleColumnValueExcludeFilter(
				Bytes.toBytes("f"), Bytes.toBytes("pts"),
				CompareFilter.CompareOp.GREATER, Bytes.toBytes(targetDate_0
						.getTime()));

		Date targetDate_1 = Date.from(LocalDate.of(2016, 2, 21).atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
		Filter filter_1 = new SingleColumnValueExcludeFilter(
				Bytes.toBytes("f"), Bytes.toBytes("pts"),
				CompareFilter.CompareOp.LESS_OR_EQUAL,
				Bytes.toBytes(targetDate_1.getTime()));

		FilterList filterList = new FilterList(filter_0, filter_1);
		return new SkipFilter(filterList);
	}

}
