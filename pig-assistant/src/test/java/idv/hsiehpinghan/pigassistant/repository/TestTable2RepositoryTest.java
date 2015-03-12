package idv.hsiehpinghan.pigassistant.repository;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.pigassistant.entity.TestTable2;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.AFamily;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.BFamily;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.BFamily.BValue;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.CFamily;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.DFamily;
import idv.hsiehpinghan.pigassistant.suit.TestngSuitSetting;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTable2RepositoryTest {
	private Date ver = DateUtility.getDate(2015, 2, 3);
	private String elementId = "elementId";
	private String stockCode = "stockCode";
	private BigInteger operatingIncomeOfCurrentMonth = new BigInteger("15");
	private String unitType = "unitType";
	private String operatingIncomeOfComment = "operatingIncomeOfComment";
	private String string = "string";
	private int month = 19;
	private BigDecimal value = new BigDecimal("20.20");
	private int year = 21;
	private Date date = DateUtility.getDate(2015, 2, 3);
	private BigDecimal operatingIncomeOfDifferentPercent = new BigDecimal(
			"23.23");
	private Date instant = DateUtility.getDate(2015, 2, 3);
	private TestTable2Repository repository;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		repository = applicationContext.getBean(TestTable2Repository.class);
	}

	@Test
	public void put() throws Exception {
		TestTable2 entity = repository.generateEntity(stockCode, date);
		generateAFamilyContent(entity);
		generateBFamilyContent(entity);
		generateCFamilyContent(entity);
		generateDFamilyContent(entity);
		repository.put(entity);
		Assert.assertTrue(repository.exists(entity.getRowKey()));

		putAdditionalEntity(1);
		putAdditionalEntity(2);
		putAdditionalEntity(3);
	}

	@Test(dependsOnMethods = { "put" })
	public void get() throws Exception {
		TestTable2 entity = repository.get(stockCode, date);
		assertAFamily(entity);
		assertBFamily(entity);
		assertCFamily(entity);
		assertDFamily(entity);
	}

	private void generateAFamilyContent(TestTable2 entity) {
		AFamily fam = entity.getAFamily();
		fam.setString(ver, string);
	}

	private void assertAFamily(TestTable2 entity) {
		AFamily fam = entity.getAFamily();
		Assert.assertEquals(string, fam.getString());
	}

	private void generateBFamilyContent(TestTable2 entity) {
		BFamily fam = entity.getBFamily();
		fam.setBValue(elementId, instant, ver, unitType, value);
	}

	private void assertBFamily(TestTable2 entity) {
		BFamily fam = entity.getBFamily();
		BValue val = fam.getBValue(elementId, instant);
		Assert.assertEquals(val.getUnitType(), unitType);
		Assert.assertEquals(val.getValue(), value);
	}

	private void generateCFamilyContent(TestTable2 entity) {
		CFamily fam = entity.getCFamily();
		fam.set(elementId, instant, ver, value);
	}

	private void assertCFamily(TestTable2 entity) {
		CFamily fam = entity.getCFamily();
		Assert.assertEquals(fam.get(elementId, instant), value);
	}

	private void generateDFamilyContent(TestTable2 entity) {
		DFamily fam = entity.getDFamily();
		fam.setOperatingIncomeOfCurrentMonth(year, month, ver,
				operatingIncomeOfCurrentMonth);
		fam.setOperatingIncomeOfDifferentPercent(year, month, ver,
				operatingIncomeOfDifferentPercent);
		fam.setOperatingIncomeOfComment(year, month, ver,
				operatingIncomeOfComment);
	}

	private void assertDFamily(TestTable2 entity) {
		DFamily fam = entity.getDFamily();
		Assert.assertEquals(operatingIncomeOfCurrentMonth,
				fam.getOperatingIncomeOfCurrentMonth(year, month));
		Assert.assertEquals(operatingIncomeOfDifferentPercent,
				fam.getOperatingIncomeOfDifferentPercent(year, month));
		Assert.assertEquals(operatingIncomeOfComment,
				fam.getOperatingIncomeOfComment(year, month));
	}

	private void putAdditionalEntity(int i) throws IllegalAccessException {
		TestTable2 entity = repository.generateEntity(stockCode + i, date);
		generateAFamilyContent(entity);
		generateBFamilyContent(entity);
		generateCFamilyContent(entity);
		generateDFamilyContent(entity);
		repository.put(entity);
	}
}
