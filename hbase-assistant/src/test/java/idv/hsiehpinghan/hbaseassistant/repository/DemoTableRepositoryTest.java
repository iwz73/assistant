package idv.hsiehpinghan.hbaseassistant.repository;

import idv.hsiehpinghan.datetimeutility.utility.DateUtility;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.CombinedFamily;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.ComplexFamily;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.ComplexFamily.ComplexValue;
import idv.hsiehpinghan.hbaseassistant.entity.DemoTable.SimpleFamily;
import idv.hsiehpinghan.hbaseassistant.suit.TestngSuitSetting;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoTableRepositoryTest {
	private Date ver = DateUtility.getDate(2015, 2, 3);
	private String elementId = "elementId";
	private String stockCode = "stockCode";
	private String unitType = "unitType";
	private Integer age = 12;
	private BigDecimal value = new BigDecimal("13.13");
	private String userName = "userName";
	private Date date = DateUtility.getDate(2015, 2, 3);
	private Date instant = DateUtility.getDate(2015, 2, 3);
	private DemoTableRepository repository;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		repository = applicationContext.getBean(DemoTableRepository.class);
	}

	@Test
	public void put() throws Exception {
		DemoTable entity = repository.generateEntity(stockCode, date);
		generateSimpleFamilyContent(entity);
		generateCombinedFamilyContent(entity);
		generateComplexFamilyContent(entity);
		repository.put(entity);
		Assert.assertTrue(repository.exists(entity.getRowKey()));
	}

	@Test(dependsOnMethods = { "get" })
	public void getWithSimpleFamilyOnly() throws Exception {
		DemoTable entity = repository.getWithSimpleFamilyOnly(stockCode, date);
		assertSimpleFamily(entity);
		assertEmptyCombinedFamily(entity);
		assertEmptyComplexFamily(entity);
	}

	@Test(dependsOnMethods = { "get" })
	public void getWithCombinedFamilyOnly() throws Exception {
		DemoTable entity = repository
				.getWithCombinedFamilyOnly(stockCode, date);
		assertEmptySimpleFamily(entity);
		assertCombinedFamily(entity);
		assertEmptyComplexFamily(entity);
	}

	@Test(dependsOnMethods = { "get" })
	public void getWithComplexFamilyOnly() throws Exception {
		DemoTable entity = repository.getWithComplexFamilyOnly(stockCode, date);
		assertEmptySimpleFamily(entity);
		assertEmptyCombinedFamily(entity);
		assertComplexFamily(entity);
	}

	@Test(dependsOnMethods = { "put" })
	public void get() throws Exception {
		DemoTable entity = repository.get(stockCode, date);
		assertSimpleFamily(entity);
		assertCombinedFamily(entity);
		assertComplexFamily(entity);
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

	private void assertEmptySimpleFamily(DemoTable entity) {
		SimpleFamily fam = entity.getSimpleFamily();
		Assert.assertEquals(fam.getLatestQualifierAndValueAsMap().size(), 0);
	}

	private void generateCombinedFamilyContent(DemoTable entity) {
		CombinedFamily fam = entity.getCombinedFamily();
		fam.set(elementId, instant, ver, value);
	}

	private void assertCombinedFamily(DemoTable entity) {
		CombinedFamily fam = entity.getCombinedFamily();
		Assert.assertEquals(fam.get(elementId, instant), value);
	}

	private void assertEmptyCombinedFamily(DemoTable entity) {
		CombinedFamily fam = entity.getCombinedFamily();
		Assert.assertEquals(fam.getLatestQualifierAndValueAsMap().size(), 0);
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

	private void assertEmptyComplexFamily(DemoTable entity) {
		ComplexFamily fam = entity.getComplexFamily();
		Assert.assertEquals(fam.getLatestQualifierAndValueAsMap().size(), 0);
	}
}
