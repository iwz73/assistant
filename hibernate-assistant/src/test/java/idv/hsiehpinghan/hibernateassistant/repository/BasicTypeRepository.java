package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.BasicTypeEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BasicTypeRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(BasicTypeEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public BasicTypeEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (BasicTypeEntity) session.get(BasicTypeEntity.class, id);
	}

}
