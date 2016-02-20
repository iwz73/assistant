package idv.hsiehpinghan.springdatahadoopassistant.assistant;

import idv.hsiehpinghan.springdatahadoopassistant.entity.Webpage;
import idv.hsiehpinghan.springdatahadoopassistant.filter.TestFilter;
import idv.hsiehpinghan.springdatahadoopassistant.suit.TestngSuitSetting;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter;
import org.apache.hadoop.hbase.filter.PageFilter;
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
	public void scan() throws Exception {
		// TestRowFilter();
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
		Filter rowFilter = generateRowFilter();
		Filter skipFilter = generateSkipFilter();
		Filter multipleColumnPrefixFilter = multipleColumnPrefixFilter();
		FilterList filterList = new FilterList(rowFilter, skipFilter,
				multipleColumnPrefixFilter);
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filterList);
		for (Webpage entity : entities) {
			System.err.println(entity);
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
		Filter filter = generateRowFilter();
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filter);
		for (Webpage entity : entities) {
			System.err.println(entity);
		}
		Assert.assertEquals(1, entities.size());
	}

	private void TestPageFilter() throws Exception {
		PageFilter filter = new org.apache.hadoop.hbase.filter.PageFilter(
				PAGE_SIZE);
		Collection<Webpage> entities = assistant.scan(TABLE_NAME, filter);
		for (Webpage entity : entities) {
			System.err.println(entity);
		}
		Assert.assertTrue(PAGE_SIZE < entities.size());
	}

	private Filter multipleColumnPrefixFilter() {
		byte[][] prefixes = new byte[][] { Bytes.toBytes("bas"),
				Bytes.toBytes("pts"), Bytes.toBytes("cnt"),
				Bytes.toBytes("sig") };
		return new MultipleColumnPrefixFilter(prefixes);
	}

	private Filter generateRowFilter() {
		return new RowFilter(CompareFilter.CompareOp.EQUAL,
				new BinaryComparator(
						Bytes.toBytes("tw.com.ipeen.www:http/comment/148690")));
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
