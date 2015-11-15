package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.CacheModeEntity;

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

	public void save(CacheModeEntity entity) {
		entityManager.persist(entity);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModePut(Integer id) {
		assertCacheMode(id, CacheMode.PUT, 1, 0, 0, 1);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModeNormal(Integer id) {
		assertCacheMode(id, CacheMode.NORMAL, 1, 1, 0, 1);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void cacheModeIgnore(Integer id) {
		assertCacheMode(id, CacheMode.IGNORE, 1, 0, 0, 0);
	}
	
	public void assertCacheMode(Integer id, CacheMode cacheMode,
			long entityLoadCount, long secondLevelCacheMissCount,
			long secondLevelCacheHitCount, long secondLevelCachePutCount) {
		SessionFactory sessionFactory = getSessionFactory();
		Statistics statistics = clearAndGetStatistics(sessionFactory);
		Session session = evictAndGetSession(sessionFactory);
		session.setCacheMode(cacheMode);
		CacheModeEntity entity = (CacheModeEntity) session.load(
				CacheModeEntity.class, id);
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
		sessionFactory.getCache().evictEntityRegion(CacheModeEntity.class);
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
