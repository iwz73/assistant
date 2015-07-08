package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneManyEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToOneRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ManyToOneManyEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ManyToOneManyEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToOneManyEntity) session.get(ManyToOneManyEntity.class, id);
	}

}
