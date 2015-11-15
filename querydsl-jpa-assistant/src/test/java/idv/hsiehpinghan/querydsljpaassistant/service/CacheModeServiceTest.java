package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.CacheModeEntity;
import idv.hsiehpinghan.querydsljpaassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CacheModeServiceTest {
	// private final String FROM_NAME = "from_name";
	private final String NAME = "name";
	// private Integer id;
	private CacheModeService service;
	private CacheModeEntity entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(CacheModeService.class);
		entity = generateCacheModeEntity();
		service.save(entity);
	}

	@Test
	public void cacheModePut() {
		service.cacheModePut(entity.getId());
	}

	@Test
	public void cacheModeNormal() {
		service.cacheModeNormal(entity.getId());
	}

	@Test
	public void cacheModeIgnore() {
		service.cacheModeIgnore(entity.getId());
	}

	private CacheModeEntity generateCacheModeEntity() {
		CacheModeEntity entity = new CacheModeEntity();
		entity.setName(NAME);
		return entity;
	}

}
