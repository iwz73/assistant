package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.IndexedEmbeddedEntity;

@Repository
public class IndexedEmbeddedRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(IndexedEmbeddedEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public IndexedEmbeddedEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (IndexedEmbeddedEntity) session.get(IndexedEmbeddedEntity.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<IndexedEmbeddedEntity> luceneQuery(org.apache.lucene.search.Query query) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, IndexedEmbeddedEntity.class);
		return fullTextQuery.list();
	}

}
