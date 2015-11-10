package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.CascadeEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CascadeRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void persist(CascadeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(entity);
	}

	public void merge(CascadeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(entity);
	}

	public CascadeEntity get(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (CascadeEntity) session.get(CascadeEntity.class, id);
	}

}
