package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.LoadOneEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoadRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public Long save(LoadOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(entity);
	}

	public LoadOneEntity load(long id) {
		Session session = sessionFactory.getCurrentSession();
		return (LoadOneEntity) session.load(LoadOneEntity.class, id);
	}

}
