package idv.hsiehpinghan.neo4jassistant.assistant;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.neo4jassistant.node.BasicNode;

@Component
public class Neo4jOgmAssistant {
	@Autowired
	private SessionFactory sessionFactory;

	public void purgeDatabase() {
		Session session = sessionFactory.openSession();
		session.purgeDatabase();
	}

	public void save(BasicNode node, int depth) {
		Session session = sessionFactory.openSession();
		session.save(node, depth);
	}

	public BasicNode load(Class<BasicNode> type, Long id, int depth) {
		Session session = sessionFactory.openSession();
		return session.load(type, id, depth);
	}
}
