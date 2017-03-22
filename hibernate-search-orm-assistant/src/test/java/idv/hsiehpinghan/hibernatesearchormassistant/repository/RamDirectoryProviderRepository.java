package idv.hsiehpinghan.hibernatesearchormassistant.repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import idv.hsiehpinghan.hibernatesearchormassistant.entity.RamDirectoryProviderEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.InputStreamUtility;
import idv.hsiehpinghan.hibernatesearchormassistant.utility.ReaderUtility;

@Repository
public class RamDirectoryProviderRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public void save(RamDirectoryProviderEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	public void remove(RamDirectoryProviderEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(entity);
	}

	public void saveOrUpdate(RamDirectoryProviderEntity entity) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(entity);
	}

	public RamDirectoryProviderEntity findOne(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (RamDirectoryProviderEntity) session.get(RamDirectoryProviderEntity.class, id);
	}

	public String findClobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query<Clob> query = session.createQuery("select clob from RamDirectoryProviderEntity bte where bte.id = :id ",
				Clob.class);
		query.setParameter("id", id);
		Clob clob = query.uniqueResult();
		return convertToString(clob);
	}

	public String findBlobAsString(int id) throws SQLException, IOException {
		Session session = sessionFactory.getCurrentSession();
		Query<Blob> query = session.createQuery("select blob from RamDirectoryProviderEntity bte where bte.id = :id ",
				Blob.class);
		query.setParameter("id", id);
		Blob blob = (Blob) query.uniqueResult();
		return convertToString(blob);
	}

	public int reindexAll() {
		Session session = sessionFactory.getCurrentSession();
		Query<RamDirectoryProviderEntity> query = session.createQuery("from RamDirectoryProviderEntity ",
				RamDirectoryProviderEntity.class);
		List<RamDirectoryProviderEntity> entities = query.getResultList();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		for (RamDirectoryProviderEntity entity : entities) {
			fullTextSession.index(entity);
		}
		return entities.size();
	}

	@SuppressWarnings("unchecked")
	public List<RamDirectoryProviderEntity> luceneQuery(org.apache.lucene.search.Query query) {
		Session session = sessionFactory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, RamDirectoryProviderEntity.class);
		return fullTextQuery.list();
	}

	private String convertToString(java.sql.Clob clob) throws SQLException, IOException {
		Reader reader = clob.getCharacterStream();
		return ReaderUtility.readAsString(reader);
	}

	private String convertToString(java.sql.Blob blob) throws SQLException, IOException {
		InputStream inputStream = blob.getBinaryStream();
		return InputStreamUtility.readAsString(inputStream);
	}
}
