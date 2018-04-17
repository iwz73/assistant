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

	@Test
	public void creatingNodeRelationshipNode_0() {
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

	private void createRelationship(String node_0, String relationship, String node_1) {
		String label = String.format("%s_label", relationship);
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
		String label = String.format("%s_label", node);
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

	// @Test
	// public void creatingASingleNodeWithALable() {
	// String nodeName = "creatingASingleNodeWithALable_node";
	// String labelName = "creatingASingleNodeWithALable_lable";
	// String statement = String.format("CREATE (%s:%s)", nodeName, labelName);
	// StatementResult result = assistant.run(statement);
	// Assert.assertEquals(result.summary().statement().text(), statement);
	// }
	//
	// @Test
	// public void creatingASingleNodeWithMultipleLable() {
	// String nodeName = "creatingASingleNodeWithMultipleLable_node";
	// String labelName_0 = "creatingASingleNodeWithMultipleLable_lable_0";
	// String labelName_1 = "creatingASingleNodeWithMultipleLable_lable_1";
	// String labelName_2 = "creatingASingleNodeWithMultipleLable_lable_2";
	// String statement = String.format("CREATE (%s:%s:%s:%s)", nodeName,
	// labelName_0, labelName_1, labelName_2);
	// StatementResult result = assistant.run(statement);
	// Assert.assertEquals(result.summary().statement().text(), statement);
	// }
	//
	// @Test
	// public void creatingASingleNodeWithProperties() {
	// String nodeName = "creatingASingleNodeWithProperties_node";
	// String labelName = "creatingASingleNodeWithProperties_lable";
	// String property_key_0 = "key_0";
	// String property_value_0 = "value_0";
	// String property_key_1 = "key_1";
	// String property_value_1 = "value_1";
	// String property_key_2 = "key_2";
	// String property_value_2 = "value_2";
	// String statement = String.format("CREATE (%s:%s {%s:%s, %s:%s, %s:%s})",
	// nodeName, labelName, property_key_0,
	// property_value_0, property_key_1, property_value_1, property_key_2,
	// property_value_2);
	// StatementResult result = assistant.run(statement);
	// Assert.assertEquals(result.summary().statement().text(), statement);
	// }
	//
	// @Test
	// public void creatingMultipleNodes() {
	// String nodeName_0 = "creatingMultipleNodes_node_0";
	// String nodeName_1 = "creatingMultipleNodes_node_1";
	// String nodeName_2 = "creatingMultipleNodes_node_2";
	// String statement = String.format("CREATE (%s),(%s),(%s)", nodeName_0,
	// nodeName_1, nodeName_2);
	// StatementResult result = assistant.run(statement);
	// Assert.assertEquals(result.summary().statement().text(), statement);
	// }
	//
	// @Test
	// public void retriveAllNodes() {
	// String statement = String.format("MATCH (n) RETURN n");
	// StatementResult result = assistant.run(statement);
	// Assert.assertTrue(result.hasNext());
	// }

}
