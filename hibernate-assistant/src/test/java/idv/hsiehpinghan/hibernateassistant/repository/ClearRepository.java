package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ClearEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClearRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(ClearEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public ClearEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (ClearEntity) session.get(ClearEntity.class, id);
	}

	public void clear() {
		Session session = sessionFactory.getCurrentSession();
		session.clear();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
