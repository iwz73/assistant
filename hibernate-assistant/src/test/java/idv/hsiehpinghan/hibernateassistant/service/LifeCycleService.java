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
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
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
