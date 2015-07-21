package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedMultipleTableContainerEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmbeddedMultipleTableRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(EmbeddedMultipleTableContainerEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public EmbeddedMultipleTableContainerEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (EmbeddedMultipleTableContainerEntity) session.get(
				EmbeddedMultipleTableContainerEntity.class, id);
	}

}
