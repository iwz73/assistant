package idv.hsiehpinghan.springdatahadoopassistant.assistant;

import idv.hsiehpinghan.springdatahadoopassistant.entity.Webpage;
import idv.hsiehpinghan.springdatahadoopassistant.suit.TestngSuitSetting;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Collection;

import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueExcludeFilter;
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
//		TestRowFilter();
		// TestPageFilter();
//		 TestSingleColumnValueExcludeFilter();
		TestFilterList();
	}

	
	private void TestFilterList() throws Exception {
		Filter rowFilter = generateRowFilter();
		Filter singleColumnValueExcludeFilter = generateSingleColumnValueExcludeFilter();
		FilterList filterList = new FilterList(rowFilter);
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
			System.err.println(entity.getRowKey() + " / " + entity.getF().getPrevFetchTime());
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
}
