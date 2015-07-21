package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.MultipleTableEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MultipleTableRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(MultipleTableEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public MultipleTableEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (MultipleTableEntity) session.get(MultipleTableEntity.class, id);
	}

}
