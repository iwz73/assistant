package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManyToManyBidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ManyToManyBidirectionFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ManyToManyBidirectionFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ManyToManyBidirectionFromEntity) session.get(
				ManyToManyBidirectionFromEntity.class, id);
	}

}
