package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedOneToManyBidirectionOneEntity;

@Repository
public class IndexedEmbeddedOneToManyBidirectionRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(IndexedEmbeddedOneToManyBidirectionOneEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public IndexedEmbeddedOneToManyBidirectionOneEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (IndexedEmbeddedOneToManyBidirectionOneEntity) session
				.get(IndexedEmbeddedOneToManyBidirectionOneEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<IndexedEmbeddedOneToManyBidirectionOneEntity> luceneQuery(org.apache.lucene.search.Query query) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query,
				IndexedEmbeddedOneToManyBidirectionOneEntity.class);
		return fullTextQuery.list();
	}
}
