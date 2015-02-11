package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.hbaseassistant.entity.TestTable.DailyFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.FinancialReportFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.InfoFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.MonthlyFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.RowKey;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.XbrlInstanceFamily;
import idv.hsiehpinghan.hbaseassistant.enumeration.Enumeration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import junit.framework.Assert;

import org.apache.commons.lang3.time.DateUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTableTest {
	private String stockCode = "2330";
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
		ver = DateUtils.parseDate("2015/01/01", "yyyy/MM/dd");
		instant = DateUtils.parseDate("2015/03/03", "yyyy/MM/dd");
		date = DateUtils.parseDate("2015/05/05", "yyyy/MM/dd");
	}

	@Test
	public void bytesConvert() {
		TestTable entity = new TestTable();
		testRowKey(entity);
		testInfoFamily(entity);
		testXbrlInstanceFamily(entity);
		testFinancialReportFamily(entity);
		testMonthlyFamily(entity);
		testDailyFamily(entity);
	}

	private void testDailyFamily(TestTable entity) {
		DailyFamily fam = entity.getDailyFamily();
		fam.setClosingConditionOfOpeningPrice(date, ver, closingConditionOfOpeningPrice);
		Assert.assertEquals(closingConditionOfOpeningPrice, fam.getClosingConditionOfOpeningPrice(date));
		fam.setClosingConditionOfStockAmount(date, ver, closingConditionOfStockAmount);
		Assert.assertEquals(closingConditionOfStockAmount, fam.getClosingConditionOfStockAmount(date));
	}
	
	private void testMonthlyFamily(TestTable entity) {
		MonthlyFamily fam = entity.getMonthlyFamily();
		fam.setOperatingIncomeOfComment(year, month, ver, operatingIncomeOfComment);
		Assert.assertEquals(operatingIncomeOfComment, fam.getOperatingIncomeOfComment(year, month));
		fam.setOperatingIncomeOfCurrentMonth(year, month, ver, operatingIncomeOfCurrentMonth);
		Assert.assertEquals(operatingIncomeOfCurrentMonth, fam.getOperatingIncomeOfCurrentMonth(year, month));
		fam.setOperatingIncomeOfDifferentPercent(year, month, ver, operatingIncomeOfDifferentPercent);
		Assert.assertEquals(operatingIncomeOfDifferentPercent, fam.getOperatingIncomeOfDifferentPercent(year, month));
	}
	
	private void testFinancialReportFamily(TestTable entity) {
		FinancialReportFamily fam = entity.getFinancialReportFamily();
		fam.set(elementId, enumeration, instant, ver, string);
		Assert.assertEquals(string, fam.getAsString(elementId, enumeration, instant));
	}
	
	private void testXbrlInstanceFamily(TestTable entity) {
		XbrlInstanceFamily fam = entity.getXbrlInstanceFamily();
		fam.set(elementId, enumeration, instant, ver, value);
		Assert.assertEquals(value, fam.getAsBigDecimal(elementId, enumeration, instant));
	}
	
	private void testInfoFamily(TestTable entity) {
		InfoFamily fam = entity.getInfoFamily();
		fam.setEnumeration(ver, enumeration);
		Assert.assertEquals(enumeration, fam.getEnumeration());
		fam.setString(ver, string);
		Assert.assertEquals(string, fam.getString());
	}
	
	private void testRowKey(TestTable entity) {
		RowKey key = entity.new RowKey(stockCode, entity);
		Assert.assertEquals(stockCode, key.getStockCode());
	}

}
