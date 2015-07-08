package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectContainerEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmbeddedObjectRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(EmbeddedObjectContainerEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public EmbeddedObjectContainerEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (EmbeddedObjectContainerEntity) session.get(
				EmbeddedObjectContainerEntity.class, id);
	}
}
