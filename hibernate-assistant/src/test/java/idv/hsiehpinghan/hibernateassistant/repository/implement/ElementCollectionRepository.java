package idv.hsiehpinghan.hibernateassistant.repository.implement;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.repository.IElementCollectionRepository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ElementCollectionRepository implements
		IElementCollectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(ElementCollectionEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	@Override
	public ElementCollectionEntity findById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (ElementCollectionEntity) session.get(
				ElementCollectionEntity.class, id);
	}
}
