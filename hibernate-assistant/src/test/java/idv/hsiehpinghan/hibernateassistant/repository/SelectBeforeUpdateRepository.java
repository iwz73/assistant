package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.SelectBeforeUpdateEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SelectBeforeUpdateRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(SelectBeforeUpdateEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public void update(SelectBeforeUpdateEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.update(entity);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
