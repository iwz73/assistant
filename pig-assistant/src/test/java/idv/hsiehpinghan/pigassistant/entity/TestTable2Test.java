package idv.hsiehpinghan.pigassistant.entity;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.AFamily;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.BFamily;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.BFamily.BValue;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.CFamily;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.DFamily;
import idv.hsiehpinghan.pigassistant.entity.TestTable2.RowKey;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestTable2Test {
	private Date ver = DateUtility.getDate(2015, 2, 3);
	private String elementId = "elementId";
	private String stockCode = "stockCode";
	private BigInteger operatingIncomeOfCurrentMonth = new BigInteger("3");
	private String unitType = "unitType";
	private String operatingIncomeOfComment = "operatingIncomeOfComment";
	private String string = "string";
	private int month = 7;
	private BigDecimal value = new BigDecimal("8.8");
	private int year = 2015;
	private Date date = DateUtility.getDate(2015, 2, 3);
	private BigDecimal operatingIncomeOfDifferentPercent = new BigDecimal(
			"11.11");
	private Date instant = DateUtility.getDate(2015, 2, 3);

	@Test
	public void bytesConvert() {
		TestTable2 entity = new TestTable2();
		testRowKey(entity);
		testAFamily(entity);
		testBFamily(entity);
		testCFamily(entity);
		testDFamily(entity);
	}

	private void testRowKey(TestTable2 entity) {
		RowKey key = entity.new RowKey(stockCode, date, entity);
		Assert.assertEquals(stockCode, key.getStockCode());
		Assert.assertEquals(date, key.getDate());
	}

	private void testAFamily(TestTable2 entity) {
		generateAFamilyContent(entity);
		assertAFamily(entity);
	}

	private void generateAFamilyContent(TestTable2 entity) {
		AFamily fam = entity.getAFamily();
		fam.setString(ver, string);
	}

	private void assertAFamily(TestTable2 entity) {
		AFamily fam = entity.getAFamily();
		Assert.assertEquals(string, fam.getString());
	}

	private void testBFamily(TestTable2 entity) {
		generateBFamilyContent(entity);
		assertBFamily(entity);
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

	private void testCFamily(TestTable2 entity) {
		generateCFamilyContent(entity);
		assertCFamily(entity);
	}

	private void generateCFamilyContent(TestTable2 entity) {
		CFamily fam = entity.getCFamily();
		fam.set(elementId, instant, ver, value);
	}

	private void assertCFamily(TestTable2 entity) {
		CFamily fam = entity.getCFamily();
		Assert.assertEquals(fam.get(elementId, instant), value);
	}

	private void testDFamily(TestTable2 entity) {
		generateDFamilyContent(entity);
		assertDFamily(entity);
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
}
