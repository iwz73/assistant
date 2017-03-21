package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
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

	@SuppressWarnings("unchecked")
	public List<IndexedEmbeddedManyToManyBidirectionFromEntity> luceneQuery(org.apache.lucene.search.Query query) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query,
				IndexedEmbeddedManyToManyBidirectionFromEntity.class);
		return fullTextQuery.list();
	}

}
