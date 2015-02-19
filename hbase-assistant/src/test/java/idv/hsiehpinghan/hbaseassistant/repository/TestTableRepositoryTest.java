package idv.hsiehpinghan.hbaseassistant.repository;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.DailyFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.FinancialReportFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.InfoFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.MonthlyFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.XbrlInstanceFamily;
import idv.hsiehpinghan.hbaseassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hbaseassistant.suit.TestngSuitSetting;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTableRepositoryTest {
	private Date ver = DateUtility.getDate(2015, 2, 3);
	private String elementId = "elementId";
	private String stockCode = "stockCode";
	private BigInteger operatingIncomeOfCurrentMonth = new BigInteger("18");
	private String unitType = "unitType";
	private Date date = DateUtility.getDate(2015, 2, 3);
	private BigDecimal closingConditionOfOpeningPrice = new BigDecimal("21.21");
	private BigDecimal operatingIncomeOfDifferentPercent = new BigDecimal(
			"22.22");
	private BigInteger closingConditionOfStockAmount = new BigInteger("23");
	private String operatingIncomeOfComment = "operatingIncomeOfComment";
	private String string = "string";
	private BigDecimal value = new BigDecimal("26.26");
	private int month = 27;
	private int year = 28;
	private Enumeration enumeration = null;
	private Date instant = DateUtility.getDate(2015, 2, 3);
	private TestTableRepository repository;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		repository = applicationContext.getBean(TestTableRepository.class);
	}

	@Test
	public void put() throws Exception {
		TestTable entity = repository.generateEntity(stockCode);
		generateInfoFamilyContent(entity);
		generateXbrlInstanceFamilyContent(entity);
		generateFinancialReportFamilyContent(entity);
		generateMonthlyFamilyContent(entity);
		generateDailyFamilyContent(entity);
		repository.put(entity);
		Assert.assertTrue(repository.exists(entity.getRowKey()));
	}

	@Test(dependsOnMethods = { "put" })
	public void get() throws Exception {
		TestTable entity = repository.get(stockCode);
		assertInfoFamily(entity);
		assertXbrlInstanceFamily(entity);
		assertFinancialReportFamily(entity);
		assertMonthlyFamily(entity);
		assertDailyFamily(entity);
	}

	private void generateInfoFamilyContent(TestTable entity) {
		InfoFamily fam = entity.getInfoFamily();
		fam.setEnumeration(ver, enumeration);
		fam.setString(ver, string);
	}

	private void assertInfoFamily(TestTable entity) {
		InfoFamily fam = entity.getInfoFamily();
		Assert.assertEquals(enumeration, fam.getEnumeration());
		Assert.assertEquals(string, fam.getString());
	}

	private void generateXbrlInstanceFamilyContent(TestTable entity) {
		XbrlInstanceFamily fam = entity.getXbrlInstanceFamily();
	}

	private void assertXbrlInstanceFamily(TestTable entity) {
		XbrlInstanceFamily fam = entity.getXbrlInstanceFamily();
	}

	private void generateFinancialReportFamilyContent(TestTable entity) {
		FinancialReportFamily fam = entity.getFinancialReportFamily();
	}

	private void assertFinancialReportFamily(TestTable entity) {
		FinancialReportFamily fam = entity.getFinancialReportFamily();
	}

	private void generateMonthlyFamilyContent(TestTable entity) {
		MonthlyFamily fam = entity.getMonthlyFamily();
		fam.setOperatingIncomeOfCurrentMonth(year, month, ver,
				operatingIncomeOfCurrentMonth);
		fam.setOperatingIncomeOfDifferentPercent(year, month, ver,
				operatingIncomeOfDifferentPercent);
		fam.setOperatingIncomeOfComment(year, month, ver,
				operatingIncomeOfComment);
	}

	private void assertMonthlyFamily(TestTable entity) {
		MonthlyFamily fam = entity.getMonthlyFamily();
		Assert.assertEquals(operatingIncomeOfCurrentMonth,
				fam.getOperatingIncomeOfCurrentMonth(year, month));
		Assert.assertEquals(operatingIncomeOfDifferentPercent,
				fam.getOperatingIncomeOfDifferentPercent(year, month));
		Assert.assertEquals(operatingIncomeOfComment,
				fam.getOperatingIncomeOfComment(year, month));
	}

	private void generateDailyFamilyContent(TestTable entity) {
		DailyFamily fam = entity.getDailyFamily();
		fam.setClosingConditionOfOpeningPrice(date, ver,
				closingConditionOfOpeningPrice);
		fam.setClosingConditionOfStockAmount(date, ver,
				closingConditionOfStockAmount);
	}

	private void assertDailyFamily(TestTable entity) {
		DailyFamily fam = entity.getDailyFamily();
		Assert.assertEquals(closingConditionOfOpeningPrice,
				fam.getClosingConditionOfOpeningPrice(date));
		Assert.assertEquals(closingConditionOfStockAmount,
				fam.getClosingConditionOfStockAmount(date));
	}
}
