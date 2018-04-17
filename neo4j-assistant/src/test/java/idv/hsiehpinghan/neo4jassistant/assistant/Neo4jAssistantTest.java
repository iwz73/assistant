package idv.hsiehpinghan.neo4jassistant.assistant;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.neo4jassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class Neo4jAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private Neo4jAssistant assistant;

//	http://www.baeldung.com/java-neo4j
	
	@Test
	public void creatingNodeRelationshipNode() {
		String node_0 = "n_0_0";
		String node_1 = "n_0_1";
		String relationship = "r";
		String statement = String.format(
				"CREATE (%s:n_0_0_lable {n_0_0_key_0:'n_0_0_value_0', n_0_0_key_1:'n_0_0_value_1', n_0_0_key_2:'n_0_0_value_2'})-[%s:r_label {r_key_0:'r_value_0', r_key_1:'r_value_1', r_key_2:'r_value_2'}]->(%s:n_0_1_lable {n_0_1_key_0:'n_0_1_value_0', n_0_1_key_1:'n_0_1_value_1', n_0_1_key_2:'n_0_1_value_2'}) RETURN %s,%s",
				node_0, relationship, node_1, node_0, node_1);
		StatementResult result = assistant.run(statement);
		int i = 0;
		while (result.hasNext()) {
			Record record = result.next();
			Assert.assertEquals(record.size(), 2);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	@Test
	public void creatingRelationshipBetweenExistingNodes() {
		String node_0 = "n_1_0";
		createNode(node_0);
		String node_1 = "n_1_1";
		createNode(node_1);
		String relationship = "r_1";
		createRelationship(node_0, relationship, node_1);
	}

	@Test
	public void creatingMultipleNodes() {
		String node_0 = "n_2_0";
		String node_1 = "n_2_1";
		String node_2 = "n_2_2";
		String statement = String.format("CREATE (%s),(%s),(%s) RETURN %s,%s,%s", node_0, node_1, node_2, node_0,
				node_1, node_2);
		StatementResult result = assistant.run(statement);
		int i = 0;
		while (result.hasNext()) {
			Record record = result.next();
			Assert.assertEquals(record.size(), 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	@Test
	public void creatingPath() {
		String node_0 = "n_3_0";
		String node_1 = "n_3_1";
		String node_2 = "n_3_2";
		String statement = String.format("CREATE (%s)-[:r_0]->(%s)-[:r_1]->(%s) RETURN %s,%s,%s", node_0, node_1,
				node_2, node_0, node_1, node_2);
		StatementResult result = assistant.run(statement);
		int i = 0;
		while (result.hasNext()) {
			Record record = result.next();
			Assert.assertEquals(record.size(), 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	@Test
	public void mergingNode() {
		String node = "n_4_0";
		mergeNode(node);
	}

	private void createRelationship(String node_0, String relationship, String node_1) {
		String label = generateLable(relationship);
		String property_key_0 = String.format("%s_key_0", relationship);
		String property_value_0 = String.format("%s_value_0", relationship);
		String property_key_1 = String.format("%s_key_1", relationship);
		String property_value_1 = String.format("%s_value_1", relationship);
		String property_key_2 = String.format("%s_key_2", relationship);
		String property_value_2 = String.format("%s_value_2", relationship);
		String statement = String.format("CREATE (%s)-[%s:%s {%s:'%s', %s:'%s', %s:'%s'}]->(%s) RETURN %s,%s", node_0,
				relationship, label, property_key_0, property_value_0, property_key_1, property_value_1, property_key_2,
				property_value_2, node_1, node_0, node_1);
		StatementResult result = assistant.run(statement);
		int i = 0;
		while (result.hasNext()) {
			Record record = result.next();
			Assert.assertEquals(record.size(), 2);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void createNode(String node) {
		String label = generateLable(node);
		String property_key_0 = String.format("%s_key_0", node);
		String property_value_0 = String.format("%s_value_0", node);
		String property_key_1 = String.format("%s_key_1", node);
		String property_value_1 = String.format("%s_value_1", node);
		String property_key_2 = String.format("%s_key_2", node);
		String property_value_2 = String.format("%s_value_2", node);
		String statement = String.format("CREATE (%s:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN %s", node, label,
				property_key_0, property_value_0, property_key_1, property_value_1, property_key_2, property_value_2,
				node);
		StatementResult result = assistant.run(statement);
		Assert.assertTrue(result.hasNext());
	}

	private void mergeNode(String node) {
		String label = generateLable(node);
		String property_key_0 = String.format("%s_key_0", node);
		String property_value_0 = String.format("%s_value_0", node);
		String property_key_1 = String.format("%s_key_1", node);
		String property_value_1 = String.format("%s_value_1", node);
		String property_key_2 = String.format("%s_key_2", node);
		String property_value_2 = String.format("%s_value_2", node);
		String statement = String.format("MERGE (%s:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN %s", node, label,
				property_key_0, property_value_0, property_key_1, property_value_1, property_key_2, property_value_2,
				node);
		StatementResult result = assistant.run(statement);
		Assert.assertTrue(result.hasNext());
	}

	private String generateLable(String nodeOrRelationship) {
		String label = String.format("%s_label", nodeOrRelationship);
		return label;
	}
}
