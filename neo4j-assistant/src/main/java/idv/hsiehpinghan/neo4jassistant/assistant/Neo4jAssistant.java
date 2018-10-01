package idv.hsiehpinghan.neo4jassistant.assistant;

import java.util.Map;

import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Neo4jAssistant {
	@Autowired
	private Driver driver;

	public StatementResult write(String statement) {
		try (Session session = driver.session()) {
			return session.writeTransaction(tx -> {
				return tx.run(statement);
			});
		}
	}

	public StatementResult read(String statement) {
		try (Session session = driver.session()) {
			return session.readTransaction(tx -> {
				return tx.run(statement);
			});
		}
	}

	public StatementResult readWithParameter(String statement, Map<String, Object> parameter) {
		try (Session session = driver.session()) {
			return session.readTransaction(tx -> {
				return tx.run(statement, parameter);
			});
		}
	}
	
	public StatementResult run(String statement) {
		try (Session session = driver.session()) {
			return session.run(statement);
		}
	}

}
