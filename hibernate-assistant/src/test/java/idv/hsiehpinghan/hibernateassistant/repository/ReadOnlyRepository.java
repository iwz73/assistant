package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ReadOnlyEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReadOnlyRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(ReadOnlyEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public ReadOnlyEntity findOne(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (ReadOnlyEntity) session.get(ReadOnlyEntity.class, id);
	}

	public void setReadOnly(ReadOnlyEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.setReadOnly(entity, true);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
