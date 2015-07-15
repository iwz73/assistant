package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEnumerationMapEntity4;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementCollectionRepository4 {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(ElementCollectionEnumerationMapEntity4 entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public ElementCollectionEnumerationMapEntity4 findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ElementCollectionEnumerationMapEntity4) session.get(
				ElementCollectionEnumerationMapEntity4.class, id);
	}
}
