package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.AttributeConverterEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeConverterRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(AttributeConverterEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public AttributeConverterEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (AttributeConverterEntity) session.get(
				AttributeConverterEntity.class, id);
	}
}
