package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToOneBidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToOneBidirectionFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToOneBidirectionFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToOneBidirectionFromEntity) session.get(
				OneToOneBidirectionFromEntity.class, id);
	}

}
