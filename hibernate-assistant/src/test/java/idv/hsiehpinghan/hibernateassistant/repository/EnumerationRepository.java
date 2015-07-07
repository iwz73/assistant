package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.EnumerationEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnumerationRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(EnumerationEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public EnumerationEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (EnumerationEntity) session.get(EnumerationEntity.class, id);
	}

}
