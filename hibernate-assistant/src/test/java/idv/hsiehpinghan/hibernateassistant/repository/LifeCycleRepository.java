package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.LifeCycleEntity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LifeCycleRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(LifeCycleEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public void update(LifeCycleEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}

	public LifeCycleEntity merge(LifeCycleEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (LifeCycleEntity) session.merge(entity);
	}

	public LifeCycleEntity get(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (LifeCycleEntity) session.get(LifeCycleEntity.class, id);
	}

	public LifeCycleEntity load(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (LifeCycleEntity) session.load(LifeCycleEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<LifeCycleEntity> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from LifeCycleEntity").list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
