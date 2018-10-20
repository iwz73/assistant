package idv.hsiehpinghan.neo4jassistant.assistant;

import java.io.Serializable;
import java.util.Map;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Neo4jOgmAssistant {
	@Autowired
	private SessionFactory sessionFactory;

	public void purgeDatabase() {
		Session session = sessionFactory.openSession();
		session.purgeDatabase();
	}

	public void save(Object node, int depth) {
		Session session = sessionFactory.openSession();
		session.save(node, depth);
	}

	public <T, ID extends Serializable> T load(Class<T> type, Long id, int depth) {
		Session session = sessionFactory.openSession();
		return session.load(type, id, depth);
	}

	public <T> Iterable<T> query(Class<T> type, String cypher, Map<String, ?> parameters) {
		Session session = sessionFactory.openSession();
		return session.query(type, cypher, parameters);
	}
}
