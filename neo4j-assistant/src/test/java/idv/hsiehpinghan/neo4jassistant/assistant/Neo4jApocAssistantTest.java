package idv.hsiehpinghan.neo4jassistant.assistant;

import java.util.Map;

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

	/**
	 * if fail, rm -rf /tmp/neo4j/data/* and restart neo4j, and try again.
	 */
//	@Test
	public void index() throws Exception {
		initData();
		indexAddNode();
		indexNodes();
		indexList();
		indexRemove();
	}

//	@Test
	public void help() throws Exception {
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

//	@Test
	public void loadJson() throws Exception {
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

	@Test
	public void spatial() throws Exception {
		geocodeOnce();
		geocode();
		reverseGeocode();
	}

	private void geocode() throws Exception {
		// @formatter:off
		String callStatement = String.format(
			"CALL apoc.spatial.geocode('中和水源路', 1) YIELD location\n" + 
			"RETURN location ");
		// @formatter:on
		StatementResult callResult = assistant.run(callStatement);		
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Map<String, Object> location = record.get(0).asMap();
			Assert.assertTrue(location.get("description").toString().contains("新北市"));
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void geocodeOnce() throws Exception {
		// @formatter:off
		String callStatement = String.format(
			"CALL apoc.spatial.geocodeOnce('中和水源路') YIELD location\n" + 
			"RETURN location ");
		// @formatter:on
		StatementResult callResult = assistant.run(callStatement);		
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Map<String, Object> location = record.get(0).asMap();
			Assert.assertTrue(location.get("description").toString().contains("新北市"));
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void reverseGeocode() throws Exception {
		// @formatter:off
		String callStatement = String.format(
			"CALL apoc.spatial.reverseGeocode(25.003548, 121.5058428) YIELD location " + 
			"RETURN location ");
		// @formatter:on
		StatementResult callResult = assistant.run(callStatement);		
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Map<String, Object> location = record.get(0).asMap();
			Assert.assertTrue(location.get("description").toString().contains("新北市"));
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void indexList() throws Exception {
		// @formatter:off
		String callStatement = String.format(
			"CALL apoc.index.list() ");
		// @formatter:on
		StatementResult callResult = assistant.run(callStatement);		
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			String indexName = String.format("Airport");			
			if(record.get(1).asString().equals(indexName) == true) {
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

	private void indexNodes() throws Exception {
		// @formatter:off
		String callStatement = String.format(
			"CALL apoc.index.nodes('Airport','name:inter*') YIELD node AS airport, weight " + 
			"RETURN airport.name, weight ");
		// @formatter:on
		StatementResult callResult = assistant.run(callStatement);		
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			++i;
		}
		Assert.assertEquals(i, 2);
	}

	private void indexRemove() throws Exception {
		// @formatter:off
		String indexStatement = String.format(
				"CALL apoc.index.remove('Airport') ");
		// @formatter:on
		StatementResult indexResult = assistant.read(indexStatement);
		String callStatement = String.format("CALL apoc.index.list()");
		StatementResult callResult = assistant.write(callStatement);		
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			String indexName = String.format("Airport");			
			if(record.get(1).asString().equals(indexName) == true) {
				++i;
			}
		}
		Assert.assertEquals(i, 0);
	}

	private void indexAddNode() throws Exception {
		// @formatter:off
		String indexStatement = String.format(
				"MATCH (a:Airport) " + 
				"CALL apoc.index.addNode(a,['name']) " +
				"RETURN count(*) ");
		// @formatter:on
		StatementResult indexResult = assistant.read(indexStatement);
		String callStatement = String.format("CALL apoc.index.list()");
		StatementResult callResult = assistant.write(callStatement);		
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			String indexName = String.format("Airport");	
			if(record.get(1).asString().equals(indexName) == true) {
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

	private void initData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"CREATE (slc:Airport {abbr:'SLC', id:14869, name:'SALT LAKE CITY INTERNATIONAL'}) " +
			"CREATE (oak:Airport {abbr:'OAK', id:13796, name:'METROPOLITAN OAKLAND INTERNATIONAL'}) " +
			"CREATE (bur:Airport {abbr:'BUR', id:10800, name:'BOB HOPE'}) " +
			"CREATE (f2:Flight {flight_num:6147, day:2, month:1, weekday:6, year:2016}) " +
			"CREATE (f9:Flight {flight_num:6147, day:9, month:1, weekday:6, year:2016}) " +
			"CREATE (f16:Flight {flight_num:6147, day:16, month:1, weekday:6, year:2016}) " +
			"CREATE (f23:Flight {flight_num:6147, day:23, month:1, weekday:6, year:2016}) " +
			"CREATE (f30:Flight {flight_num:6147, day:30, month:1, weekday:6, year:2016}) " +
			"CREATE (f2)-[:DESTINATION {arr_delay:-13, taxi_time:9}]->(oak) " +
			"CREATE (f9)-[:DESTINATION {arr_delay:-8, taxi_time:4}]->(bur) " +
			"CREATE (f16)-[:DESTINATION {arr_delay:-30, taxi_time:4}]->(slc) " +
			"CREATE (f23)-[:DESTINATION {arr_delay:-21, taxi_time:3}]->(slc) " +
			"CREATE (f30)-[:DESTINATION]->(slc) ");
		StatementResult createResult = assistant.write(createStatement);
	}
}
