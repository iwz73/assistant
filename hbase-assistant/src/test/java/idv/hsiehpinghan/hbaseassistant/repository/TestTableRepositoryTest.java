package idv.hsiehpinghan.hbaseassistant.repository;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ColumnNameFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.QualifierColumnNameFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValueFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValuesFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValuesFamily.ValuesValue;
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
	private BigInteger operatingIncomeOfCurrentMonth = new BigInteger("16");
	private String unitType = "unitType";
	private Date date = DateUtility.getDate(2015, 2, 3);
	private BigDecimal operatingIncomeOfDifferentPercent = new BigDecimal(
			"19.19");
	private String operatingIncomeOfComment = "operatingIncomeOfComment";
	private String string = "string";
	private int month = 22;
	private BigDecimal value = new BigDecimal("23.23");
	private int year = 24;
	private Enumeration enumeration = Enumeration.TYPE2;
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
		TestTable entity = repository.generateEntity(stockCode, date);
		generateColumnNameFamilyContent(entity);
		generateValuesFamilyContent(entity);
		generateValueFamilyContent(entity);
		generateQualifierColumnNameFamilyContent(entity);
		repository.put(entity);
		Assert.assertTrue(repository.exists(entity.getRowKey()));
	}

	@Test(dependsOnMethods = { "put" })
	public void get() throws Exception {
		TestTable entity = repository.get(stockCode, date);
		assertColumnNameFamily(entity);
		assertValuesFamily(entity);
		assertValueFamily(entity);
		assertQualifierColumnNameFamily(entity);
	}

	private void generateColumnNameFamilyContent(TestTable entity) {
		ColumnNameFamily fam = entity.getColumnNameFamily();
		fam.setEnumeration(ver, enumeration);
		fam.setString(ver, string);
	}

	private void assertColumnNameFamily(TestTable entity) {
		ColumnNameFamily fam = entity.getColumnNameFamily();
		Assert.assertEquals(enumeration, fam.getEnumeration());
		Assert.assertEquals(string, fam.getString());
	}

	private void generateValuesFamilyContent(TestTable entity) {
		ValuesFamily fam = entity.getValuesFamily();
		fam.setValuesValue(elementId, enumeration, instant, ver, unitType,
				value);
	}

	private void assertValuesFamily(TestTable entity) {
		ValuesFamily fam = entity.getValuesFamily();
		ValuesValue val = fam.getValuesValue(elementId, enumeration, instant);
		Assert.assertEquals(val.getUnitType(), unitType);
		Assert.assertEquals(val.getValue(), value);
	}

	private void generateValueFamilyContent(TestTable entity) {
		ValueFamily fam = entity.getValueFamily();
		fam.set(elementId, enumeration, instant, ver, value);
	}

	private void assertValueFamily(TestTable entity) {
		ValueFamily fam = entity.getValueFamily();
		Assert.assertEquals(fam.get(elementId, enumeration, instant), value);
	}

	private void generateQualifierColumnNameFamilyContent(TestTable entity) {
		QualifierColumnNameFamily fam = entity.getQualifierColumnNameFamily();
		fam.setOperatingIncomeOfCurrentMonth(year, month, ver,
				operatingIncomeOfCurrentMonth);
		fam.setOperatingIncomeOfDifferentPercent(year, month, ver,
				operatingIncomeOfDifferentPercent);
		fam.setOperatingIncomeOfComment(year, month, ver,
				operatingIncomeOfComment);
	}

	private void assertQualifierColumnNameFamily(TestTable entity) {
		QualifierColumnNameFamily fam = entity.getQualifierColumnNameFamily();
		Assert.assertEquals(operatingIncomeOfCurrentMonth,
				fam.getOperatingIncomeOfCurrentMonth(year, month));
		Assert.assertEquals(operatingIncomeOfDifferentPercent,
				fam.getOperatingIncomeOfDifferentPercent(year, month));
		Assert.assertEquals(operatingIncomeOfComment,
				fam.getOperatingIncomeOfComment(year, month));
	}
}
