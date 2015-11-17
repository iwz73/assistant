package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.CacheModeOneEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

@Service
@Transactional
public class CacheModeService {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(CacheModeOneEntity entity) {
		entityManager.persist(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModePut(Long id) {
		CacheMode cacheMode = CacheMode.PUT;
		long entityLoadCount = 1;
		long secondLevelCacheMissCount = 0;
		long secondLevelCacheHitCount = 0;
		long secondLevelCachePutCount = 1;
		assertCacheMode(id, cacheMode, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModeNormal(Long id) {
		CacheMode cacheMode = CacheMode.NORMAL;
		long entityLoadCount = 1;
		long secondLevelCacheMissCount = 1;
		long secondLevelCacheHitCount = 0;
		long secondLevelCachePutCount = 1;
		assertCacheMode(id, cacheMode, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModeIgnore(Long id) {
		CacheMode cacheMode = CacheMode.IGNORE;
		long entityLoadCount = 1;
		long secondLevelCacheMissCount = 0;
		long secondLevelCacheHitCount = 0;
		long secondLevelCachePutCount = 0;
		assertCacheMode(id, cacheMode, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModeGet(Long id) {
		CacheMode cacheMode = CacheMode.GET;
		long entityLoadCount = 1;
		long secondLevelCacheMissCount = 1;
		long secondLevelCacheHitCount = 0;
		long secondLevelCachePutCount = 0;
		assertCacheMode(id, cacheMode, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModeRefresh(Long id) {
		CacheMode cacheMode = CacheMode.REFRESH;
		long entityLoadCount = 1;
		long secondLevelCacheMissCount = 0;
		long secondLevelCacheHitCount = 0;
		long secondLevelCachePutCount = 1;
		assertCacheMode(id, cacheMode, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void timeToLiveSeconds_0(Long id) {
		SessionFactory sessionFactory = getSessionFactory();
		sessionFactory.getCache().evictEntityRegion(CacheModeOneEntity.class);
		long entityLoadCount = 1;
		long secondLevelCacheMissCount = 1;
		long secondLevelCacheHitCount = 0;
		long secondLevelCachePutCount = 1;
		assertTimeToLiveSeconds(sessionFactory, id, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void timeToLiveSeconds_1(Long id) {
		SessionFactory sessionFactory = getSessionFactory();
		long entityLoadCount = 0;
		long secondLevelCacheMissCount = 0;
		long secondLevelCacheHitCount = 1;
		long secondLevelCachePutCount = 0;
		assertTimeToLiveSeconds(sessionFactory, id, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void timeToLiveSeconds_2(Long id) {
		SessionFactory sessionFactory = getSessionFactory();
		long entityLoadCount = 1;
		long secondLevelCacheMissCount = 1;
		long secondLevelCacheHitCount = 0;
		long secondLevelCachePutCount = 1;
		assertTimeToLiveSeconds(sessionFactory, id, entityLoadCount,
				secondLevelCacheMissCount, secondLevelCacheHitCount,
				secondLevelCachePutCount);
	}

	private void assertTimeToLiveSeconds(SessionFactory sessionFactory,
			Long id, long entityLoadCount, long secondLevelCacheMissCount,
			long secondLevelCacheHitCount, long secondLevelCachePutCount) {
		Statistics statistics = clearAndGetStatistics(sessionFactory);
		entityManager.find(CacheModeOneEntity.class, id);
		Assert.assertEquals(statistics.getEntityLoadCount(), entityLoadCount);
		Assert.assertEquals(statistics.getSecondLevelCacheMissCount(),
				secondLevelCacheMissCount);
		Assert.assertEquals(statistics.getSecondLevelCacheHitCount(),
				secondLevelCacheHitCount);
		Assert.assertEquals(statistics.getSecondLevelCachePutCount(),
				secondLevelCachePutCount);
	}

	private void assertCacheMode(Long id, CacheMode cacheMode,
			long entityLoadCount, long secondLevelCacheMissCount,
			long secondLevelCacheHitCount, long secondLevelCachePutCount) {
		SessionFactory sessionFactory = getSessionFactory();
		Statistics statistics = clearAndGetStatistics(sessionFactory);
		Session session = evictAndGetSession(sessionFactory);
		session.setCacheMode(cacheMode);
		CacheModeOneEntity entity = (CacheModeOneEntity) session.load(
				CacheModeOneEntity.class, id);
		entity.getName();
		Assert.assertEquals(statistics.getEntityLoadCount(), entityLoadCount);
		Assert.assertEquals(statistics.getSecondLevelCacheMissCount(),
				secondLevelCacheMissCount);
		Assert.assertEquals(statistics.getSecondLevelCacheHitCount(),
				secondLevelCacheHitCount);
		Assert.assertEquals(statistics.getSecondLevelCachePutCount(),
				secondLevelCachePutCount);
	}

	private Session evictAndGetSession(SessionFactory sessionFactory) {
		sessionFactory.getCache().evictEntityRegion(CacheModeOneEntity.class);
		return sessionFactory.openSession();
	}

	private Statistics clearAndGetStatistics(SessionFactory sessionFactory) {
		Statistics statistics = sessionFactory.getStatistics();
		statistics.clear();
		return statistics;
	}

	private SessionFactory getSessionFactory() {
		return entityManager.getEntityManagerFactory().unwrap(
				SessionFactory.class);
	}

}
