package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.ServiceInheritanceEntity;
import idv.hsiehpinghan.querydsljpaassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ServiceInheritanceServiceTest {
	private static final String STRING = "string";
	private ServiceInheritanceService service;
	private ServiceInheritanceEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(ServiceInheritanceService.class);
		entity = generateServiceInheritanceEntity();
		service.save(entity);
	}

	@Test
	public void findOne() {
		long id = entity.getId();
		Assert.assertEquals(service.findOne(id).getString(), STRING);
	}

	@Test(dependsOnMethods = { "findOne" })
	public void saveRollback() {
		ServiceInheritanceEntity entity = generateServiceInheritanceEntity();
		try {
			service.saveRollback(entity);
		} catch (RuntimeException e) {
			// do nothing.
		}
		Assert.assertNull(service.findOne(entity.getId()));
	}

	private ServiceInheritanceEntity generateServiceInheritanceEntity() {
		ServiceInheritanceEntity entity = new ServiceInheritanceEntity();
		entity.setId(System.nanoTime());
		entity.setString(STRING);
		return entity;
	}

}
