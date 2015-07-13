package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionTableContainerEntity2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementCollectionRepository2 {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ElementCollectionTableContainerEntity2 entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ElementCollectionTableContainerEntity2 findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ElementCollectionTableContainerEntity2) session.get(
				ElementCollectionTableContainerEntity2.class, id);
	}
}
