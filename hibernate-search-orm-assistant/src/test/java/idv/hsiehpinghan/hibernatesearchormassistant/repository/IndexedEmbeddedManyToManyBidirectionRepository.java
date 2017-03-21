package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedManyToManyBidirectionFromEntity;

@Repository
public class IndexedEmbeddedManyToManyBidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdate(IndexedEmbeddedManyToManyBidirectionFromEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public IndexedEmbeddedManyToManyBidirectionFromEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (IndexedEmbeddedManyToManyBidirectionFromEntity) session
				.get(IndexedEmbeddedManyToManyBidirectionFromEntity.class, id);
	}

}
