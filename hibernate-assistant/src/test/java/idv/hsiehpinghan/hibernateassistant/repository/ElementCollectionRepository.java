package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementCollectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ElementCollectionEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ElementCollectionEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ElementCollectionEntity) session.get(
				ElementCollectionEntity.class, id);
	}
}
