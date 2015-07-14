package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionStringMapEntity3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementCollectionRepository3 {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ElementCollectionStringMapEntity3 entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ElementCollectionStringMapEntity3 findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ElementCollectionStringMapEntity3) session.get(
				ElementCollectionStringMapEntity3.class, id);
	}
}
