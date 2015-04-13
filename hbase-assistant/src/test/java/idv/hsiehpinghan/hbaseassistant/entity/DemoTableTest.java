package idv.hsiehpinghan.hbaseassistant.entity;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.CombinedFamily;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.ComplexFamily;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.ComplexFamily.ComplexValue;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.RowKey;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.SimpleFamily;

import java.math.BigDecimal;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTableTest {
	private Date ver = DateUtility.getDate(2015, 2, 3);
	private String elementId = "elementId";
	private String stockCode = "stockCode";
	private String unitType = "unitType";
	private Integer age = 4;
	private BigDecimal value = new BigDecimal("5.5");
	private String userName = "userName";
	private Date date = DateUtility.getDate(2015, 2, 3);
	private Date instant = DateUtility.getDate(2015, 2, 3);

	@Test
	public void bytesConvert() {
		DemoTable entity = new DemoTable();
		testRowKey(entity);
		testSimpleFamily(entity);
		testCombinedFamily(entity);
		testComplexFamily(entity);
	}

	private void testRowKey(DemoTable entity) {
		RowKey key = entity.new RowKey(stockCode, date, entity);
		Assert.assertEquals(key.getStockCode(), stockCode);
		Assert.assertEquals(key.getDate(), date);
	}

	private void testSimpleFamily(DemoTable entity) {
		generateSimpleFamilyContent(entity);
		assertSimpleFamily(entity);
	}

	private void generateSimpleFamilyContent(DemoTable entity) {
		SimpleFamily fam = entity.getSimpleFamily();
		fam.setUserName(ver, userName);
		fam.setAge(ver, age);
	}

	private void assertSimpleFamily(DemoTable entity) {
		SimpleFamily fam = entity.getSimpleFamily();
		Assert.assertEquals(fam.getUserName(), userName);
		Assert.assertEquals(fam.getAge(), age);
	}

	private void testCombinedFamily(DemoTable entity) {
		generateCombinedFamilyContent(entity);
		assertCombinedFamily(entity);
	}

	private void generateCombinedFamilyContent(DemoTable entity) {
		CombinedFamily fam = entity.getCombinedFamily();
		fam.set(elementId, instant, ver, value);
	}

	private void assertCombinedFamily(DemoTable entity) {
		CombinedFamily fam = entity.getCombinedFamily();
		Assert.assertEquals(fam.get(elementId, instant), value);
	}

	private void testComplexFamily(DemoTable entity) {
		generateComplexFamilyContent(entity);
		assertComplexFamily(entity);
	}

	private void generateComplexFamilyContent(DemoTable entity) {
		ComplexFamily fam = entity.getComplexFamily();
		fam.setComplexValue(elementId, instant, ver, unitType, value);
	}

	private void assertComplexFamily(DemoTable entity) {
		ComplexFamily fam = entity.getComplexFamily();
		ComplexValue val = fam.getComplexValue(elementId, instant);
		Assert.assertEquals(val.getUnitType(), unitType);
		Assert.assertEquals(val.getValue(), value);
	}
}
