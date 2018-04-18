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
	
	
//
//
//	@Test
//	public void create() {
//		String statement = String.format("");
//		
//		CREATE (self:Company {name:"Baeldung"}) RETURN self
//	}
//	
	@Test
	public void creatingNodeRelationshipNode() {
		String statement = String.format(
				"CREATE (node_0:lable_0 {property_key_0:'property_value_0', property_key_1:'property_value_1', property_key_2:'property_value_2'})-[relationship_0:label_1 {property_key_3:'property_value_3', property_key_4:'property_value_4', property_key_5:'property_value_5'}]->(node_1:lable_2 {property_key_6:'property_value_6', property_key_7:'property_value_7', property_key_8:'property_value_8'}) RETURN node_0,relationship_0,node_1");
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
		String statement = "CREATE (node_0),(node_1),(node_2) RETURN node_0,node_1,node_2";
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
		String statement = "CREATE (node_0)-[:relationship_0_label]->(node_1)-[:relationship_1_label]->(node_2) RETURN node_0,node_1,node_2";
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

	private void createRelationship(String node_0_label, String relationship_label, String node_1_label) {
		String property_key_0 = "property_key_0";
		String property_value_0 = "property_value_0";
		String property_key_1 = "property_key_1";
		String property_value_1 = "property_value_1";
		String property_key_2 = "property_key_2";
		String property_value_2 = "property_value_2";
		String statement = String.format("CREATE (node_0:%s)-[relationship_0:%s {%s:'%s', %s:'%s', %s:'%s'}]->(node_1:%s) RETURN node_0,relationship_0,node_1", node_0_label,
				relationship_label, property_key_0, property_value_0, property_key_1, property_value_1, property_key_2,
				property_value_2, node_1_label);
		StatementResult result = assistant.run(statement);
		int i = 0;
		while (result.hasNext()) {
			Record record = result.next();
			Assert.assertEquals(record.size(), 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void createNode(String label) {
		String property_key_0 = "property_key_0";
		String property_value_0 = "property_value_0";
		String property_key_1 = "property_key_1";
		String property_value_1 = "property_value_1";
		String property_key_2 = "property_key_2";
		String property_value_2 = "property_value_2";
		String statement = String.format("CREATE (node_0:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN node_0", label,
				property_key_0, property_value_0, property_key_1, property_value_1, property_key_2, property_value_2);
		StatementResult result = assistant.run(statement);
		Assert.assertTrue(result.hasNext());
	}

	private void mergeNode(String label) {
		String property_key_0 = "property_key_0";
		String property_value_0 = "property_value_0";
		String property_key_1 = "property_key_1";
		String property_value_1 = "property_value_1";
		String property_key_2 = "property_key_2";
		String property_value_2 = "property_value_2";
		String statement = String.format("MERGE (node_0:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN node_0", label,
				property_key_0, property_value_0, property_key_1, property_value_1, property_key_2, property_value_2);
		StatementResult result = assistant.run(statement);
		Assert.assertTrue(result.hasNext());
	}

}
