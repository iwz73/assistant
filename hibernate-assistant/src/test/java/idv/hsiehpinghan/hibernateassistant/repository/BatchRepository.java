package idv.hsiehpinghan.hibernateassistant.repository;

import idv.hsiehpinghan.hibernateassistant.entity.BatchEntity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BatchRepository {
	@Autowired
	private SessionFactory sessionFactory;

	public int batchSave(Collection<BatchEntity> entities, int batchSize) {
		Session session = sessionFactory.getCurrentSession();
		Iterator<BatchEntity> iter = entities.iterator();
		int savedAmt = 0;
		while (iter.hasNext()) {
			session.persist(iter.next());
			++savedAmt;
			if ((savedAmt % batchSize) == 0) {
				session.flush();
				session.clear();
			}
		}
		return savedAmt;
	}

	public int batchUpdate(String string, int batchSize) {
		Session session = sessionFactory.getCurrentSession();
		ScrollableResults scrollableResults = session.createQuery(
				"from BatchEntity").scroll();
		int updatedAmt = 0;
		while (scrollableResults.next()) {
			BatchEntity entity = (BatchEntity) scrollableResults.get(0);
			entity.setString(string);
			++updatedAmt;
			if ((updatedAmt % batchSize) == 0) {
				session.flush();
				session.clear();
			}
		}
		return updatedAmt;
	}

	@SuppressWarnings("unchecked")
	public List<BatchEntity> findAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from BatchEntity").list();
	}

	public int deleteAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("delete BatchEntity").executeUpdate();
	}
}
