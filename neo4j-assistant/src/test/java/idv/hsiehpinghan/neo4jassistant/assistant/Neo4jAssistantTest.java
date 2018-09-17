package idv.hsiehpinghan.neo4jassistant.assistant;

import java.util.Date;

import org.neo4j.driver.internal.value.IntegerValue;
import org.neo4j.driver.internal.value.RelationshipValue;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Value;
import org.neo4j.driver.v1.types.Node;
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

	@Test
	public void remove() {
		removeSingleProperty();
		removeSingleLabel();
		removeMultiLabel();
	}

	@Test
	public void foreach() throws Exception {
		String label = "l_" + getCurrentTimeMillis();
		String property = "p_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0:%s)-[r_0:l_0]->(n_1)-[r_1:l_1]->(n_2 {p_0:'%s'})", label, property);
		assistant.run(createStatement);
		String matchStatement = String.format(
				"MATCH p = (n_0)-[*]->(n_2) " +
				"WHERE n_0:%s AND n_2.p_0 = '%s' " +
				"FOREACH (n IN nodes(p)| SET n.p_a = TRUE) " +
				"RETURN p ", label, property);
		StatementResult matchResult = assistant.run(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			int j = 0;
			for(Node node : matchResult.next().get(0).asPath().nodes()) {
				Assert.assertTrue(node.get("p_a").asBoolean());
				++j;
			}
			Assert.assertEquals(j, 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	@Test
	public void match() throws Exception  {
		matchAllNode();
		matchNodeByLabel();
		matchNodeByRelationshipLabel();
		optionalMatchNode();
	}

	@Test
	public void where() throws Exception  {
		whereWithMultipleConditions();
		whereWithRelationship();
	}

	@Test
	public void count() throws Exception  {
		basicCount();
		relationshipCount();
	}

	@Test
	public void orderBy() throws Exception  {
		String property = "p_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0 {p_0:'B', p_1:1, p_2:'%s'}), (n_1 {p_0:'A', p_1:0, p_2:'%s'}) RETURN n_0, n_1", property, property);
		assistant.run(createStatement);
		String orderByStatement = String.format("MATCH (n {p_2:'%s'}) RETURN n ORDER BY n.p_0 ASC, n.p_1 DESC", property);
		StatementResult countResult = assistant.run(orderByStatement);
		int i = 0;
		while (countResult.hasNext()) {
			Record record = countResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			String p_0 = record.get(0).asNode().get("p_0").asString();
			if(i == 0) {
				Assert.assertEquals(p_0, "A");
			} else if(i == 1) {
				Assert.assertEquals(p_0, "B");
			} else {
				throw new RuntimeException(String.format("j(%d) not implements !!!", i));
			}
			++i;
		}
		Assert.assertTrue(i > 0);
	}

	@Test
	public void limit() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0:%s), (n_1:%s) RETURN n_0, n_1", label, label);
		StatementResult createResult = assistant.run(createStatement);
		String limitStatement = String.format("MATCH (n:%s) RETURN n LIMIT 1", label);
		StatementResult limitResult = assistant.run(limitStatement);
		int i = 0;
		while (limitResult.hasNext()) {
			int size = limitResult.next().size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	@Test
	public void skip() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0:%s {p:'A'}), (n_1:%s {p:'B'}) RETURN n_0, n_1", label, label);
		StatementResult createResult = assistant.run(createStatement);
		String skipStatement = String.format("MATCH (n:%s) RETURN n ORDER BY n.p ASC SKIP 1", label);
		StatementResult skipResult = assistant.run(skipStatement);
		int i = 0;
		while (skipResult.hasNext()) {
			Record record = skipResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			Node node = record.get(0).asNode();
			Assert.assertEquals(node.get("p").asString(), "B");
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	@Test
	public void with() throws Exception  {
		String label_0 = "l_0_" + getCurrentTimeMillis();
		String label_1 = "l_1_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0)-[r_0:%s]->(n_1)-[r_1:%s]->(n_2 {p:'A'}) RETURN n_0, r_0, n_1, r_1, n_2", label_0, label_1);
		StatementResult createResult = assistant.run(createStatement);
		String withStatement = String.format(
			"MATCH (n_0)-[r:%s]->(n_1) " +
			"WITH n_1 " +
			"MATCH (n_1)-[r:%s]->(n_2)" +
			"RETURN n_2 ",
			label_0, label_1
		);
		StatementResult withResult = assistant.run(withStatement);
		int i = 0;
		while (withResult.hasNext()) {
			Record record = withResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			Assert.assertEquals(record.get(0).asNode().get("p").asString(), "A");
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	@Test
	public void index() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		createIndex(label);
		dropIndex(label);
	}
	
	@Test
	public void constraint() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		createConstraint(label);
		dropConstraint(label);
	}
	
	private void dropConstraint(String label) {
		String constraintStatement = String.format("DROP CONSTRAINT ON (n:%s) ASSERT n.p IS UNIQUE", label);
		StatementResult constraintResult = assistant.run(constraintStatement);
	}
	
	private void createConstraint(String label) {
		String createStatement = String.format("CREATE (n:%s {p:'A'}) RETURN n", label);
		assistant.run(createStatement);
		String constraintStatement = String.format("CREATE CONSTRAINT ON (n:%s) ASSERT n.p IS UNIQUE", label);
		StatementResult constraintResult = assistant.run(constraintStatement);
		Exception ex = null;
		try {
			assistant.run(createStatement);
		} catch (Exception e) {
			ex = e;
		}
		Assert.assertEquals(ex.getClass().getName(), "org.neo4j.driver.v1.exceptions.ClientException");
	}
	
	private void dropIndex(String label) {
		String indexStatement = String.format("DROP INDEX ON :%s(n)", label);
		StatementResult indexResult = assistant.run(indexStatement);
	}
	
	private void createIndex(String label) {
		String createStatement = String.format("CREATE (n_0:%s), (n_1:%s) RETURN n_0, n_1", label, label);
		StatementResult createResult = assistant.run(createStatement);
		String indexStatement = String.format("CREATE INDEX ON :%s(n)", label);
		StatementResult indexResult = assistant.run(indexStatement);
	}
	
	private void relationshipCount() throws Exception  {
		String relationshipLabel = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0)-[r:%s]->(n_1:l {p:'A'}) RETURN n_0, r, n_1", relationshipLabel);
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String countStatement = String.format("MATCH (n_0)-[r]->(n_1:l {p:'A'}) RETURN type(r), count(*)");
		StatementResult countResult = assistant.run(countStatement);
		int i = 0;
		while (countResult.hasNext()) {
			Record record = countResult.next();
			int size = record.size();
			Assert.assertEquals(size, 2);
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				if(value instanceof IntegerValue) {
					Assert.assertEquals(value.asInt(), 1);
				}
			}
			++i;
		}
		Assert.assertTrue(i > 0);
	}

	private void basicCount() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s) RETURN n", label);
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String countStatement = String.format("MATCH (n:%s) RETURN n, count(*)", label);
		StatementResult countResult = assistant.run(countStatement);
		int i = 0;
		while (countResult.hasNext()) {
			Record record = countResult.next();
			int size = record.size();
			Assert.assertEquals(size, 2);
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				if(value instanceof IntegerValue) {
					Assert.assertEquals(value.asInt(), 1);
				}
			}
			++i;
		}
		Assert.assertTrue(i > 0);
	}

	private void whereWithRelationship() throws Exception  {
		String relationshipLabel = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0)-[r:%s]->(n_1:l {p:'A'}) RETURN n_0, r, n_1", relationshipLabel);
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String whereStatement = String.format(
			"MATCH (n_0) " +
			"WHERE (n_0)-[:%s]->(:l {p:'A'}) " +
			"RETURN n_0",
			relationshipLabel
		);
		StatementResult whereResult = assistant.run(whereStatement);
		Assert.assertTrue(whereResult.hasNext());
	}

	private void whereWithMultipleConditions() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s {p_0:'A', p_1:3}) RETURN n", label);
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String whereStatement = String.format(
			"MATCH (n:%s) " +
			"WHERE n.p_0='A' AND n.p_1 > 0 " +
			"RETURN n",
			label
		);
		StatementResult whereResult = assistant.run(whereStatement);
		Assert.assertTrue(whereResult.hasNext());
	}

	private void optionalMatchNode() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s) RETURN n", label);
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format(
			"MATCH (n:%s) " +
			"OPTIONAL MATCH (n)-[r:not_exist]->(n_not_exist) " +
			"RETURN n, r, n_not_exist", label);
		StatementResult matchSetResult = assistant.run(matchSetStatement);
		int i = 0;
		while (matchSetResult.hasNext()) {
			matchSetResult.next();
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void matchNodeByRelationshipLabel() throws Exception  {
		String relationshipLabel = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0)-[r:%s]->(n_1) RETURN n_0, r, n_1", relationshipLabel);
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format("MATCH (n_0)-[r:%s]->(n_1) RETURN n_1", relationshipLabel);
		StatementResult matchSetResult = assistant.run(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
	}

	private void matchNodeByLabel() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s) RETURN n", label);
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format("MATCH (n:%s) RETURN n", label);
		StatementResult matchSetResult = assistant.run(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
	}

	private void matchAllNode() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format("MATCH (n) RETURN n");
		StatementResult matchSetResult = assistant.run(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
	}

	private void removeMultiLabel() {
		String createStatement = String.format("CREATE (n:l_0:l_1:l_2) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String removeStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"REMOVE n:l_0:l_2 " +
			"RETURN n",
			id
		);
		StatementResult removeResult = assistant.run(removeStatement);
		int i = 0;
		while (removeResult.hasNext()) {
			int j = 0;
			for(String label : removeResult.next().get(0).asNode().labels()) {
				++j;
			}
			Assert.assertEquals(j, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void removeSingleLabel() {
		String createStatement = String.format("CREATE (n:l_0:l_1:l_2) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String removeStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"REMOVE n:l_1 " +
			"RETURN n",
			id
		);
		StatementResult removeResult = assistant.run(removeStatement);
		int i = 0;
		while (removeResult.hasNext()) {
			int j = 0;
			for(String label : removeResult.next().get(0).asNode().labels()) {
				++j;
			}
			Assert.assertEquals(j, 2);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void removeSingleProperty() {
		String createStatement = String.format("CREATE (n {p_0:'A', p_1:'B', p_2:'C'}) RETURN n");
		StatementResult createResult = assistant.run(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String removeStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"REMOVE n.p_1 " +
			"RETURN n",
			id
		);
		StatementResult removeResult = assistant.run(removeStatement);
		int i = 0;
		while (removeResult.hasNext()) {
			int j = 0;
			for(Value property : removeResult.next().get(0).asNode().values()) {
				++j;
			}
			Assert.assertEquals(j, 2);
			++i;
		}
		Assert.assertEquals(i, 1);
		
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

	private long getCurrentTimeMillis() throws InterruptedException {
		Thread.sleep(1);
		return System.currentTimeMillis();
	}
}
