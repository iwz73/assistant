package idv.hsiehpinghan.hibernateassistant.service;

import idv.hsiehpinghan.hibernateassistant.entity.LifeCycleEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

@Service
@Transactional
public class LifeCycleService {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(LifeCycleEntity entity) {
		Statistics stat = sessionFactory.getStatistics();
		stat.clear();
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
		Assert.assertEquals(stat.getEntityInsertCount(), 0);
		Assert.assertEquals(stat.getFlushCount(), 0);
	}

	public void update(LifeCycleEntity entity) {
		Statistics stat = sessionFactory.getStatistics();
		stat.clear();
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
		Assert.assertEquals(stat.getEntityUpdateCount(), 0);
		Assert.assertEquals(stat.getFlushCount(), 0);
	}

	public void updateAndSelect(LifeCycleEntity entity) {
		Statistics stat = sessionFactory.getStatistics();
		stat.clear();
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
		Assert.assertEquals(stat.getEntityUpdateCount(), 0);
		Assert.assertEquals(stat.getFlushCount(), 0);
		session.createQuery("from LifeCycleEntity").list();
		Assert.assertEquals(stat.getEntityUpdateCount(), 1);
		Assert.assertEquals(stat.getQueryExecutionCount(), 1);
		Assert.assertEquals(stat.getFlushCount(), 1);
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public void getTwice(long id) {
		Statistics stat = sessionFactory.getStatistics();
		stat.clear();
		Session session = sessionFactory.getCurrentSession();
		LifeCycleEntity entity_0 = (LifeCycleEntity) session.get(
				LifeCycleEntity.class, id);
		LifeCycleEntity entity_1 = (LifeCycleEntity) session.get(
				LifeCycleEntity.class, id);
		Assert.assertTrue(entity_0 == entity_1);
		Assert.assertEquals(stat.getEntityLoadCount(), 1);
	}
}
