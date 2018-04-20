package idv.hsiehpinghan.neo4jassistant.assistant;

import java.util.Date;

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
	private final long NOW = new Date().getTime();
	private final String NODE_0_LABEL_0 = "create_label_0_0" + NOW;
	private final String RELATIONSHIP_LABEL_0 = "create_label_0_1" + NOW;
	private final String NODE_1_LABEL_0 = "create_label_0_2" + NOW;
	private final String NODE_0_LABEL_1 = "create_label_1_0" + NOW;
	private final String RELATIONSHIP_LABEL_1 = "create_label_1_1" + NOW;
	private final String NODE_1_LABEL_1 = "create_label_1_2" + NOW;
	private final String NODE_0_LABEL_2 = "create_label_2_0" + NOW;
	private final String RELATIONSHIP_LABEL_2 = "create_label_2_1" + NOW;
	private final String NODE_1_LABEL_2 = "create_label_2_2" + NOW;
	private final String PROPERTY_KEY_0 = "property_key_0";
	private final String PROPERTY_VALUE_0 = "property_value_0";
	private final String PROPERTY_KEY_1 = "property_key_1";
	private final String PROPERTY_VALUE_1 = "property_value_1";
	private final String PROPERTY_KEY_2 = "property_key_2";
	private final String PROPERTY_VALUE_2 = "property_value_2";

	@Autowired
	private Neo4jAssistant assistant;

	@Test
	public void create() {
		createRelationship(NODE_0_LABEL_0, RELATIONSHIP_LABEL_0, NODE_1_LABEL_0);
		createRelationship(NODE_0_LABEL_1, RELATIONSHIP_LABEL_1, NODE_1_LABEL_1);
		createRelationship(NODE_0_LABEL_2, RELATIONSHIP_LABEL_2, NODE_1_LABEL_2);
	}

	@Test(dependsOnMethods= {"create"})
	public void retrieve() {
		String statement = String.format("MATCH (n_0:%s)-[r_0:%s]-> (n_1:%s) WHERE r_0.property_key_0='property_value_0' and r_0.property_key_1='property_value_1' RETURN r_0.property_key_2", NODE_0_LABEL_1, RELATIONSHIP_LABEL_1, NODE_1_LABEL_1);
		StatementResult result = assistant.run(statement);
		int j = 0;
		while (result.hasNext()) {
			Record record = result.next();
			Assert.assertEquals(record.size(), 1);
			Assert.assertEquals(record.get(0).asString(), PROPERTY_VALUE_2);
			++j;
		}
		Assert.assertEquals(j, 1);
	}
	
//	@Test
//	public void creatingNodeRelationshipNode() {
//		String statement = "CREATE (node_0:lable_0 {property_key_0:'property_value_0', property_key_1:'property_value_1', property_key_2:'property_value_2'})-[relationship_0:label_1 {property_key_3:'property_value_3', property_key_4:'property_value_4', property_key_5:'property_value_5'}]->(node_1:lable_2 {property_key_6:'property_value_6', property_key_7:'property_value_7', property_key_8:'property_value_8'}) RETURN node_0,relationship_0,node_1";
//		StatementResult result = assistant.run(statement);
//		int i = 0;
//		while (result.hasNext()) {
//			Record record = result.next();
//			Assert.assertEquals(record.size(), 3);
//			++i;
//		}
//		Assert.assertEquals(i, 1);
//	}
//
//	@Test
//	public void creatingRelationshipBetweenExistingNodes() {
//		String node_0 = "n_1_0";
//		createNode(node_0);
//		String node_1 = "n_1_1";
//		createNode(node_1);
//		String relationship = "r_1";
//		createRelationship(node_0, relationship, node_1);
//	}
//
//	@Test
//	public void creatingMultipleNodes() {
//		String statement = "CREATE (node_0),(node_1),(node_2) RETURN node_0,node_1,node_2";
//		StatementResult result = assistant.run(statement);
//		int i = 0;
//		while (result.hasNext()) {
//			Record record = result.next();
//			Assert.assertEquals(record.size(), 3);
//			++i;
//		}
//		Assert.assertEquals(i, 1);
//	}
//
//	@Test
//	public void creatingPath() {
//		String statement = "CREATE (node_0)-[:relationship_0_label]->(node_1)-[:relationship_1_label]->(node_2) RETURN node_0,node_1,node_2";
//		StatementResult result = assistant.run(statement);
//		int i = 0;
//		while (result.hasNext()) {
//			Record record = result.next();
//			Assert.assertEquals(record.size(), 3);
//			++i;
//		}
//		Assert.assertEquals(i, 1);
//	}
//
//	@Test
//	public void mergingNode() {
//		String node = "n_4_0";
//		mergeNode(node);
//	}
	
	private void createRelationship(String node_0_label, String relationship_label, String node_1_label) {
//		String property_key_0 = "property_key_0";
//		String property_value_0 = "property_value_0";
//		String property_key_1 = "property_key_1";
//		String property_value_1 = "property_value_1";
//		String property_key_2 = "property_key_2";
//		String property_value_2 = "property_value_2";
		String statement = String.format("CREATE (node_0:%s)-[relationship_0:%s {%s:'%s', %s:'%s', %s:'%s'}]->(node_1:%s) RETURN node_0,relationship_0,node_1", node_0_label,
				relationship_label, PROPERTY_KEY_0, PROPERTY_VALUE_0, PROPERTY_KEY_1, PROPERTY_VALUE_1, PROPERTY_KEY_2,
				PROPERTY_VALUE_2, node_1_label);
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
		String statement = String.format("CREATE (node_0:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN node_0", label,
				PROPERTY_KEY_0, PROPERTY_VALUE_0, PROPERTY_KEY_1, PROPERTY_VALUE_1, PROPERTY_KEY_2, PROPERTY_VALUE_2);
		StatementResult result = assistant.run(statement);
		Assert.assertTrue(result.hasNext());
	}

	private void mergeNode(String label) {
		String statement = String.format("MERGE (node_0:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN node_0", label,
				PROPERTY_KEY_0, PROPERTY_VALUE_0, PROPERTY_KEY_1, PROPERTY_VALUE_1, PROPERTY_KEY_2, PROPERTY_VALUE_2);
		StatementResult result = assistant.run(statement);
		Assert.assertTrue(result.hasNext());
	}

}
