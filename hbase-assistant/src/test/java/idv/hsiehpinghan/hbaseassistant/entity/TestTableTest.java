package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ColumnNameFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.QualifierColumnNameFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.RowKey;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValueFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValuesFamily;
import idv.hsiehpinghan.hbaseassistant.entity.TestTable.ValuesFamily.ValuesValue;
import idv.hsiehpinghan.hbaseassistant.enumeration.Enumeration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTableTest {
	private Date ver = DateUtility.getDate(2015, 2, 3);
	private String elementId = "elementId";
	private String stockCode = "stockCode";
	private BigInteger operatingIncomeOfCurrentMonth = new BigInteger("3");
	private String unitType = "unitType";
	private Date date = DateUtility.getDate(2015, 2, 3);
	private BigDecimal operatingIncomeOfDifferentPercent = new BigDecimal("6.6");
	private String operatingIncomeOfComment = "operatingIncomeOfComment";
	private String string = "string";
	private int month = 9;
	private BigDecimal value = new BigDecimal("10.10");
	private int year = 11;
	private Enumeration enumeration = Enumeration.TYPE1;
	private Date instant = DateUtility.getDate(2015, 2, 3);

	@Test
	public void bytesConvert() {
		TestTable entity = new TestTable();
		testRowKey(entity);
		testColumnNameFamily(entity);
		testValuesFamily(entity);
		testValueFamily(entity);
		testQualifierColumnNameFamily(entity);
	}

	private void testRowKey(TestTable entity) {
		RowKey key = entity.new RowKey(stockCode, date, entity);
		Assert.assertEquals(stockCode, key.getStockCode());
		Assert.assertEquals(date, key.getDate());
	}

	private void testColumnNameFamily(TestTable entity) {
		generateColumnNameFamilyContent(entity);
		assertColumnNameFamily(entity);
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

	private void testValuesFamily(TestTable entity) {
		generateValuesFamilyContent(entity);
		assertValuesFamily(entity);
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

	private void testValueFamily(TestTable entity) {
		generateValueFamilyContent(entity);
		assertValueFamily(entity);
	}

	private void generateValueFamilyContent(TestTable entity) {
		ValueFamily fam = entity.getValueFamily();
		fam.set(elementId, enumeration, instant, ver, value);
	}

	private void assertValueFamily(TestTable entity) {
		ValueFamily fam = entity.getValueFamily();
		Assert.assertEquals(fam.get(elementId, enumeration, instant), value);
	}

	private void testQualifierColumnNameFamily(TestTable entity) {
		generateQualifierColumnNameFamilyContent(entity);
		assertQualifierColumnNameFamily(entity);
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
