package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.CacheModeManyEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.CacheModeOneEntity;
import idv.hsiehpinghan.querydsljpaassistant.suit.TestngSuitSetting;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CacheModeServiceTest {
	private final long idBase = System.currentTimeMillis();
	private final int SIZE = 3;
	private final String NAME = "name";
	private CacheModeService service;
	private CacheModeOneEntity one;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(CacheModeService.class);
		one = generateCacheModeOneEntity();
		service.save(one);
	}

	@Test
	public void cacheModePut() {
		service.cacheModePut(one.getId());
	}

	@Test
	public void cacheModeNormal() {
		service.cacheModeNormal(one.getId());
	}

	@Test
	public void cacheModeIgnore() {
		service.cacheModeIgnore(one.getId());
	}

	@Test
	public void cacheModeGet() {
		service.cacheModeGet(one.getId());
	}

	@Test
	public void cacheModeRefresh() {
		service.cacheModeRefresh(one.getId());
	}

	@Test
	public void timeToLiveSeconds_0() {
		service.timeToLiveSeconds_0(one.getId());
	}

	@Test(dependsOnMethods={"timeToLiveSeconds_0"})
	public void timeToLiveSeconds_1() {
		service.timeToLiveSeconds_1(one.getId());
	}
	
	private void sleep(int seconeds) {
		try {
			Thread.sleep(seconeds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private CacheModeOneEntity generateCacheModeOneEntity() {
		CacheModeOneEntity entity = new CacheModeOneEntity();
		entity.setName(NAME);
		entity.setMany(generateCacheModeManyEntities(one));
		return entity;
	}

	private Set<CacheModeManyEntity> generateCacheModeManyEntities(
			CacheModeOneEntity one) {
		Set<CacheModeManyEntity> many = new HashSet<CacheModeManyEntity>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			many.add(generateCacheModeManyEntity(one, i));
		}
		return many;
	}

	private CacheModeManyEntity generateCacheModeManyEntity(
			CacheModeOneEntity one, int i) {
		CacheModeManyEntity entity = new CacheModeManyEntity();
		entity.setId(idBase + i);
		entity.setName(NAME + "_" + i);
		entity.setOne(one);
		return entity;
	}
}
