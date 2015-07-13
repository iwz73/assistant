package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionContainerEntity1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementCollectionRepository1 {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ElementCollectionContainerEntity1 entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ElementCollectionContainerEntity1 findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ElementCollectionContainerEntity1) session.get(
				ElementCollectionContainerEntity1.class, id);
	}
}
