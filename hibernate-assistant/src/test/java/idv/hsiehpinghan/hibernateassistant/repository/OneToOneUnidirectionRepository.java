package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionFromEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OneToOneUnidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(OneToOneUnidirectionFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public OneToOneUnidirectionFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (OneToOneUnidirectionFromEntity) session.get(
				OneToOneUnidirectionFromEntity.class, id);
	}

}
