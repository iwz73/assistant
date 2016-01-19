package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyUnidirectionFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToManyUnidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdate(ManyToManyUnidirectionFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public ManyToManyUnidirectionFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToManyUnidirectionFromEntity) session.get(
				ManyToManyUnidirectionFromEntity.class, id);
	}

}
