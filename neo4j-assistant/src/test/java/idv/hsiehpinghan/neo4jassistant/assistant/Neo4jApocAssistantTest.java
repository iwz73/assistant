package idv.hsiehpinghan.neo4jassistant.assistant;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.types.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.neo4jassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class Neo4jApocAssistantTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private Neo4jAssistant assistant;

	@Test
	public void procedure() throws Exception {
		loadJson();
		help();
	}

	private void help() throws Exception {
		String name = "dijkstra";
		// @formatter:off
		String callStatement = String.format("CALL apoc.help('%s') ", name);
		// @formatter:on
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			++i;
		}
		Assert.assertTrue(0 < i);
	}

	private void loadJson() throws Exception {
		String url = "https://raw.githubusercontent.com/neo4j-contrib/neo4j-apoc-procedures/3.4/src/test/resources/person.json";
		// @formatter:off
		String callStatement = String.format("WITH '%s' AS url " + 
			"CALL apoc.load.json(url) YIELD value as person " +
			"MERGE (p:Person {name:person.name}) " +
			"ON CREATE SET p.age = person.age, p.children = size(person.children) " + 
			"RETURN p ", 
			url);
		// @formatter:on
		StatementResult callResult = assistant.write(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Node node = record.get(0).asNode();
			Assert.assertEquals(node.get("name").asString(), "Michael");
			Assert.assertEquals(node.get("age").asInt(), 41);
			Assert.assertEquals(node.get("children").asInt(), 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private long getCurrentTimeMillis() throws InterruptedException {
		Thread.sleep(1);
		return System.currentTimeMillis();
	}
}
