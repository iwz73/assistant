package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.EvictEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EvictRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(EvictEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public EvictEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (EvictEntity) session.get(EvictEntity.class, id);
	}

	public void evict(EvictEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.evict(entity);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
