package idv.hsiehpinghan.neo4jassistant.assistant;

import java.io.Serializable;
import java.util.Map;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Neo4jOgmAssistant {
	@Autowired
	private SessionFactory sessionFactory;

	public void purgeDatabase() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.purgeDatabase();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (transaction != null) {
				transaction.close();
			}
		}
	}

	public void save(Object node) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(node);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			if (transaction != null) {
				transaction.close();
			}
		}
	}

	public <T, ID extends Serializable> T load(Class<T> type, String id, int depth) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			T entity = session.load(type, id, depth);
			transaction.commit();
			return entity;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if (transaction != null) {
				transaction.close();
			}
		}
	}

	public <T> Iterable<T> query(Class<T> type, String cypher, Map<String, ?> parameters) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Iterable<T> entities = session.query(type, cypher, parameters);
			transaction.commit();
			return entities;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if (transaction != null) {
				transaction.close();
			}
		}
	}

}
