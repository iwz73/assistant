package idv.hsiehpinghan.neo4jassistant.assistant;

import java.util.Date;

import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Relationship;
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
	
	@Test
	public void create() {
		createSingleNode();
		createMultipleNodes();
		createSingleNodeWithLebel();
		createSingleNodeWithMultipleLebel();
		createSingleNodeWithMultipleProperty();
		createNodeAndRelationship();
		createNodeAndRelationshipWithLabelAndProperty();
		createRelationshipBetweenExistingNode();
		createPath();
	}
	
	@Test
	public void merge() {
		mergeNodeWithLabel();
		mergeNodeWithProperty();
		mergeNodeWithOnCreateAndOnMatch();
		mergeRelationship();
	}

	@Test
	public void set() {
		setNodeProperty();
		removeNodeProperty();
		setNodeLabel();
	}
	
	@Test
	public void delete() {
		deleteAllNode();
		deleteMultipleNodes();
		deleteSingleNode();
	}
	
	private void deleteMultipleNodes() {
		String createStatement = String.format("CREATE (n:l {p_0:'A', p_1:'B', p_2:'C'}) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String detachDeleteStatement = String.format(
			"MATCH (n:l {p_0:'A', p_1:'B', p_2:'C'}) " +
			"DETACH DELETE n"
		);
		assistant.run(detachDeleteStatement);
		String matchStatement = String.format(
			"MATCH (n:l {p_0:'A', p_1:'B', p_2:'C'}) " +
			"RETURN n"
		);
		StatementResult matchResult = assistant.run(matchStatement);
		Assert.assertFalse(matchResult.hasNext());
	}

	private void deleteSingleNode() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String detachDeleteStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"DETACH DELETE n",
			id
		);
		assistant.run(detachDeleteStatement);
		String matchStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"RETURN n",
			id
		);
		StatementResult matchResult = assistant.run(matchStatement);
		Assert.assertFalse(matchResult.hasNext());
	}

	private void deleteAllNode() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.run(detachDeleteStatement);
		String matchStatement = String.format(
				"MATCH (n) " +
				"RETURN n");
		StatementResult matchResult = assistant.run(matchStatement);
		Assert.assertFalse(matchResult.hasNext());
	}

	private void setNodeLabel() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String label_0 = "l_0";
		String label_1 = "l_1";
		String matchSetStatement = String.format(
				"MATCH (n) WHERE ID(n) = %d " +
				"SET n:%s:%s " +
				"RETURN n", id, label_0, label_1);
		StatementResult matchSetResult = assistant.run(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
		int i = 0;
		for(String label : matchSetResult.next().get(0).asNode().labels()) {
			++i;
		}
		Assert.assertEquals(i, 2);
	}

	private void removeNodeProperty() {
		String createStatement = String.format("CREATE (n {p:'A'}) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String matchSetStatement = String.format(
				"MATCH (n) WHERE ID(n) = %d " +
				"SET n.p = NULL " +
				"RETURN n", id);
		StatementResult matchSetResult = assistant.run(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
		int i = 0;
		for(Value value : matchSetResult.next().get(0).asNode().values()) {
			++i;
		}
		Assert.assertEquals(i, 0);
	}

	private void setNodeProperty() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String property_0 = "p_0";
		String property_1 = "p_1";
		String matchSetStatement = String.format(
				"MATCH (n) WHERE ID(n) = %d " +
				"SET n.p_0 = '%s', n.p_1 = '%s' " +
				"RETURN n", id, property_0, property_1);
		StatementResult matchSetResult = assistant.run(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
		int i = 0;
		for(Value value : matchSetResult.next().get(0).asNode().values()) {
			++i;
		}
		Assert.assertEquals(i, 2);
	}

	private void mergeRelationship() {
		final long NOW = new Date().getTime();
		String label_0 = "l_0_" + NOW;
		String label_1 = "l_1_" + NOW;
		String property_0 = "p_0_" + NOW;
		String property_1 = "p_1_" + NOW;
		String createStatement = String.format("CREATE (n_0:%s {p_0:'%s'}), (n_1:%s {p_1:'%s'})", label_0, property_0, label_1, property_1);
		assistant.run(createStatement);
		String mergeStatement = String.format(
			"MATCH (n_0:%s), (n_1:%s) " +
			"WHERE n_0.p_0 = '%s' AND n_1.p_1 = '%s' " +
			"MERGE (n_0)-[r:l_0]->(n_1) " +
			"RETURN n_0, r, n_1", label_0, label_1, property_0, property_1);
		assistant.run(mergeStatement);
		StatementResult mergeResult = assistant.run(mergeStatement);
		int i = 0;
		while (mergeResult.hasNext()) {
			Record record = mergeResult.next();
			int size = record.size();
			Assert.assertEquals(size, 3);
			++i;
		}
		Assert.assertEquals(i, 1);		
	}

	private void mergeNodeWithLabel() {
		final long NOW = new Date().getTime();
		String label = "l_" + NOW;
		String createStatement = String.format("CREATE (n:%s {p_0:'A'})", label);
		assistant.run(createStatement);
		String mergeStatement = String.format("MERGE (n:%s) RETURN n", label);
		StatementResult mergeResult = assistant.run(mergeStatement);
		int i = 0;
		while (mergeResult.hasNext()) {
			Record record = mergeResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			for(Value value : record.get(0).asNode().values()) {
				Assert.assertEquals(value.asString(), "A");
			}
			++i;
		}
		Assert.assertEquals(i, 1);		
	}
	
	private void mergeNodeWithProperty() {
		final long NOW = new Date().getTime();
		String property = "p_" + NOW;
		String createStatement = String.format("CREATE (n:l_0 {p_0:'%s'})", property);
		assistant.run(createStatement);
		String mergeStatement = String.format("MERGE (n {p_0:'%s'}) RETURN n", property);
		StatementResult mergeResult = assistant.run(mergeStatement);
		int i = 0;
		while (mergeResult.hasNext()) {
			Record record = mergeResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			for(String label : record.get(0).asNode().labels()) {
				Assert.assertEquals(label, "l_0");
			}
			++i;
		}
		Assert.assertEquals(i, 1);		
	}	

	private void mergeNodeWithOnCreateAndOnMatch() {
		final long NOW = new Date().getTime();
		String property = "p_" + NOW;
		String mergeStatement = String.format(
			"MERGE (n {p_0:'%s'}) " +
			"ON CREATE SET n.isCreate = true " +
			"ON MATCH SET n.isMatch = true " +
			"RETURN n", property);
		StatementResult mergeResult = assistant.run(mergeStatement);
		int i = 0;
		while (mergeResult.hasNext()) {
			Record record = mergeResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			Assert.assertTrue((boolean)record.get(0).asMap().get("isCreate"));
			++i;
		}
		Assert.assertEquals(i, 1);		
	}

	private void createSingleNode() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			int size = createResult.next().size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	private void createMultipleNodes() {
		String createStatement = String.format("CREATE (n_0), (n_1) RETURN n_0, n_1");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			int size = createResult.next().size();
			Assert.assertEquals(size, 2);
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	private void createSingleNodeWithLebel() {
		String createStatement = String.format("CREATE (n:l) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			int j = 0;
			for(String label : createResult.next().get(0).asNode().labels()) {
				Assert.assertEquals(label, "l");
				++j;
			}
			Assert.assertEquals(j, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	private void createSingleNodeWithMultipleLebel() {
		String createStatement = String.format("CREATE (n:l_0:l_1:l_2) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			int j = 0;
			for(String label : createResult.next().get(0).asNode().labels()) {
				++j;
			}
			Assert.assertEquals(j, 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void createSingleNodeWithMultipleProperty() {
		String createStatement = String.format("CREATE (n {p_0:'A', p_1:'B', p_2:'C'}) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			int j = 0;
			for(Value property : createResult.next().get(0).asNode().values()) {
				++j;
			}
			Assert.assertEquals(j, 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	private void createNodeAndRelationship() {
		String createStatement = String.format("CREATE (n_0)-[r:l_0]->(n_1) RETURN n_0, r, n_1");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			Record record = createResult.next();
			int size = record.size();
			Assert.assertEquals(size, 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void createNodeAndRelationshipWithLabelAndProperty() {
		String createStatement = String.format("CREATE (n_0)-[r:l_0 {p_0:'A', p_1:'B', p_2:'C'}]->(n_1) RETURN n_0, r, n_1");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			Record record = createResult.next();
			int size = record.size();
			Assert.assertEquals(size, 3);
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				if(value instanceof RelationshipValue) {
					Relationship relationship = value.asRelationship();
					Assert.assertEquals(relationship.type(), "l_0");
					Assert.assertEquals(relationship.asMap().size(), 3);
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void createRelationshipBetweenExistingNode() {
		final long NOW = new Date().getTime();
		String property_0 = "p_0_" + NOW;
		String property_1 = "p_1_" + NOW;
		String createStatement = String.format("CREATE (n_0:l_0 {p_0:'%s'}), (n_1:l_1 {p_1:'%s'}) RETURN n_0, n_1", property_0, property_1);
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			Record record = createResult.next();
			int size = record.size();
			Assert.assertEquals(size, 2);
			String matchCreateStatement = String.format(
					"MATCH (n_0:l_0), (n_1:l_1) WHERE n_0.p_0='%s' AND n_1.p_1='%s' " +
					"CREATE (n_0)-[r:l_0]->(n_1) RETURN n_0, r, n_1", property_0, property_1);
			StatementResult matchCreateResult = assistant.run(matchCreateStatement);
			int j = 0;
			while (matchCreateResult.hasNext()) {
				Record matchRecord = matchCreateResult.next();
				int matchSize = matchRecord.size();
				Assert.assertEquals(matchSize, 3);
				++j;
			}
			Assert.assertEquals(j, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void createPath() {
		String createStatement = String.format("CREATE (n_0)-[r_0:l_0]->(n_1)-[r_1:l_1]->(n_2) RETURN n_0, r_0, n_1, r_1, n_2");
		StatementResult createResult = assistant.run(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			Record record = createResult.next();
			int size = record.size();
			Assert.assertEquals(size, 5);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	
//	private final long NOW = new Date().getTime();
//	private final String NODE_0_LABEL_0 = "create_label_0_0" + NOW;
//	private final String RELATIONSHIP_LABEL_0 = "create_label_0_1" + NOW;
//	private final String NODE_1_LABEL_0 = "create_label_0_2" + NOW;
//	private final String NODE_0_LABEL_1 = "create_label_1_0" + NOW;
//	private final String RELATIONSHIP_LABEL_1 = "create_label_1_1" + NOW;
//	private final String NODE_1_LABEL_1 = "create_label_1_2" + NOW;
//	private final String NODE_0_LABEL_2 = "create_label_2_0" + NOW;
//	private final String RELATIONSHIP_LABEL_2 = "create_label_2_1" + NOW;
//	private final String NODE_1_LABEL_2 = "create_label_2_2" + NOW;
//	private final String PROPERTY_KEY_0 = "property_key_0";
//	private final String PROPERTY_VALUE_0 = "property_value_0";
//	private final String PROPERTY_KEY_1 = "property_key_1";
//	private final String PROPERTY_VALUE_1 = "property_value_1";
//	private final String PROPERTY_KEY_2 = "property_key_2";
//	private final String PROPERTY_VALUE_2 = "property_value_2";
//
//	@Autowired
//	private Neo4jAssistant assistant;
//
//	@Test
//	public void create() {
//		createRelationship(NODE_0_LABEL_0, RELATIONSHIP_LABEL_0, NODE_1_LABEL_0);
//		createRelationship(NODE_0_LABEL_1, RELATIONSHIP_LABEL_1, NODE_1_LABEL_1);
//		createRelationship(NODE_0_LABEL_2, RELATIONSHIP_LABEL_2, NODE_1_LABEL_2);
//	}
//
//	@Test(dependsOnMethods = { "create" })
//	public void retrieve() {
//		String statement = String.format("MATCH (n_0:%s)-[r_0:%s]-> (n_1:%s) "
//				+ "WHERE r_0.property_key_0='property_value_0' and r_0.property_key_1='property_value_1' "
//				+ "RETURN r_0.property_key_2", NODE_0_LABEL_1, RELATIONSHIP_LABEL_1, NODE_1_LABEL_1);
//		StatementResult result = assistant.run(statement);
//		int j = 0;
//		while (result.hasNext()) {
//			Record record = result.next();
//			Assert.assertEquals(record.size(), 1);
//			Assert.assertEquals(record.get(0).asString(), PROPERTY_VALUE_2);
//			++j;
//		}
//		Assert.assertEquals(j, 1);
//	}
//
//	@Test(dependsOnMethods = { "retrieve" })
//	public void update() {
//		final String NEW_PROPERTY_VALUE_0 = "property_value_a";
//		final String NEW_LABEL_0 = "new_label_0";
//		final String NEW_LABEL_1 = "new_label_1";
//		String statement = String.format("MATCH (n_0:%s)-[r_0:%s]-> (n_1:%s) "
//				+ "WHERE r_0.property_key_0='property_value_0' and r_0.property_key_1='property_value_1' "
//				+ "SET n_0.property_key_0='%s' " + "REMOVE n_0.property_key_1 " + "SET n_0:%s:%s " + "RETURN n_0",
//				NODE_0_LABEL_1, RELATIONSHIP_LABEL_1, NODE_1_LABEL_1, NEW_PROPERTY_VALUE_0, NEW_LABEL_0, NEW_LABEL_1);
//		StatementResult result = assistant.run(statement);
//		List<String> labelList = Arrays.asList(NODE_0_LABEL_1, NEW_LABEL_0, NEW_LABEL_1);
//		int i = 0;
//		while (result.hasNext()) {
//			Record record = result.next();
//			Assert.assertEquals(record.size(), 1);
//			Node node = record.get(0).asNode();
//			Assert.assertEquals(node.get("property_key_0").asString(), NEW_PROPERTY_VALUE_0);
//			Assert.assertTrue(node.get("property_key_1").isNull());
//			Iterable<String> labels = node.labels();
//			int j = 0;
//			for (String label : labels) {
//				labelList.contains(label);
//				++j;
//			}
//			Assert.assertEquals(j, 3);
//			++i;
//		}
//		Assert.assertEquals(i, 1);
//	}
//
//	@Test(dependsOnMethods = { "update" })
//	public void delete() {
//		String deleteStatement = String.format("MATCH (n_0:%s)-[r_0:%s]-> (n_1:%s) "
//				+ "WHERE r_0.property_key_0='property_value_0' and r_0.property_key_1='property_value_1' "
//				+ "DELETE n_0, r_0 " + "RETURN n_0", NODE_0_LABEL_1, RELATIONSHIP_LABEL_1, NODE_1_LABEL_1);
//		StatementResult deleteResult = assistant.run(deleteStatement);
//		int i = 0;
//		while (deleteResult.hasNext()) {
//			Record record = deleteResult.next();
//			Assert.assertEquals(record.size(), 1);
//			Node node = record.get(0).asNode();
//			Assert.assertFalse(isExist(node));
//			++i;
//		}
//		Assert.assertEquals(i, 1);
//	}
//
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
//
//	private void createRelationship(String node_0_label, String relationship_label, String node_1_label) {
//		String statement = String.format(
//				"CREATE (node_0:%s)-[relationship_0:%s {%s:'%s', %s:'%s', %s:'%s'}]->(node_1:%s) RETURN node_0,relationship_0,node_1",
//				node_0_label, relationship_label, PROPERTY_KEY_0, PROPERTY_VALUE_0, PROPERTY_KEY_1, PROPERTY_VALUE_1,
//				PROPERTY_KEY_2, PROPERTY_VALUE_2, node_1_label);
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
//	private void createNode(String label) {
//		String statement = String.format("CREATE (node_0:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN node_0", label,
//				PROPERTY_KEY_0, PROPERTY_VALUE_0, PROPERTY_KEY_1, PROPERTY_VALUE_1, PROPERTY_KEY_2, PROPERTY_VALUE_2);
//		StatementResult result = assistant.run(statement);
//		Assert.assertTrue(result.hasNext());
//	}
//
//	private void mergeNode(String label) {
//		String statement = String.format("MERGE (node_0:%s {%s:'%s', %s:'%s', %s:'%s'}) RETURN node_0", label,
//				PROPERTY_KEY_0, PROPERTY_VALUE_0, PROPERTY_KEY_1, PROPERTY_VALUE_1, PROPERTY_KEY_2, PROPERTY_VALUE_2);
//		StatementResult result = assistant.run(statement);
//		Assert.assertTrue(result.hasNext());
//	}
//
//	private boolean isExist(Node node) {
//		String retrieveStatement = String.format("MATCH (n_0) " + "WHERE ID(n_0) = %d " + "RETURN n_0", node.id());
//		StatementResult result = assistant.run(retrieveStatement);
//		return result.hasNext();
//	}

}
