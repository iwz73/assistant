package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.TemporalEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TemporalRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(TemporalEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public TemporalEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (TemporalEntity) session.get(TemporalEntity.class, id);
	}

}
