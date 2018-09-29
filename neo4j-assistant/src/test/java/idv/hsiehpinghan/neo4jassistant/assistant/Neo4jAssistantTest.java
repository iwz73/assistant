package idv.hsiehpinghan.neo4jassistant.assistant;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		createNodeWithEscapeCharacter();
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
	public void set() throws Exception {
		setNodeProperty();
		removeNodeProperty();
		setLabel();
		replaceNodeAllProperty();
		updateNodeAllProperty();
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
		String createStatement = String.format("CREATE (n_0:%s)-[r_0:t_0]->(n_1)-[r_1:t_1]->(n_2 {p_0:'%s'})", label, property);
		assistant.write(createStatement);
		String matchStatement = String.format(
				"MATCH p = (n_0)-[*]->(n_2) " +
				"WHERE n_0:%s AND n_2.p_0 = '%s' " +
				"FOREACH (n IN nodes(p)| SET n.p_a = TRUE) " +
				"RETURN p ", label, property);
		StatementResult matchResult = assistant.read(matchStatement);
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
		matchNodeByRelationshipType();
		matchNodeByMultiRelationshipType();
		matchNodeWithTwoRelationship();
		matchNodeWithTwoToThreeRelationship();
		matchNodeWithMoreThanTwoRelationship();
		matchNodeWithMoreThanTwoRelationshipAndLabel();
		matchNodeWithLessOrEqualToThreeRelationship();
		matchAnyLengthRelationship();
		matchAnyLengthRelationshipWithProperty();
		optionalMatchNode();
	}

	@Test
	public void where() throws Exception  {
		whereWithMultipleConditions();
		whereWithRelationship();
		whereWithDynamicalProperty();
		whereWithChainingComparison();
	}

	@Test
	public void count() throws Exception  {
		basicCount();
		relationshipCount();
		nodeCount();
	}

	@Test
	public void orderBy() throws Exception  {
		String property = "p_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0 {p_0:'B', p_1:1, p_2:'%s'}), (n_1 {p_0:'A', p_1:0, p_2:'%s'}) RETURN n_0, n_1", property, property);
		assistant.write(createStatement);
		String orderByStatement = String.format("MATCH (n {p_2:'%s'}) RETURN n ORDER BY n.p_0 ASC, n.p_1 DESC", property);
		StatementResult countResult = assistant.read(orderByStatement);
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
		StatementResult createResult = assistant.write(createStatement);
		String limitStatement = String.format("MATCH (n:%s) RETURN n LIMIT 1", label);
		StatementResult limitResult = assistant.read(limitStatement);
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
		StatementResult createResult = assistant.write(createStatement);
		String skipStatement = String.format("MATCH (n:%s) RETURN n ORDER BY n.p ASC SKIP 1", label);
		StatementResult skipResult = assistant.read(skipStatement);
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
		StatementResult createResult = assistant.write(createStatement);
		String withStatement = String.format(
			"MATCH (n_0)-[r:%s]->(n_1) " +
			"WITH n_1 " +
			"MATCH (n_1)-[r:%s]->(n_2)" +
			"RETURN n_2 ",
			label_0, label_1
		);
		StatementResult withResult = assistant.read(withStatement);
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
	
	@Test
	public void withParameter() throws Exception  {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String matchStatement = "MATCH (n) WHERE ID(n) = $id RETURN n";
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("id", id);
		StatementResult matchResult = assistant.readWithParameter(matchStatement, parameter);
		Assert.assertTrue(matchResult.hasNext());
	}

	/**
	 * reference : https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html
	 */
	@Test
	public void regex() throws Exception  {
		String createStatement = String.format("CREATE (n {p:'regex_test'}) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchStatement = "MATCH (n) WHERE n.p =~ 'regex_.*' RETURN n";
		StatementResult matchResult = assistant.read(matchStatement);
		Assert.assertTrue(matchResult.hasNext());
	}

	@Test
	public void unwind() throws Exception  {
		String unwindStatement = String.format("WITH [2, 4, 7, 9, 12] AS numberlist " + 
				"UNWIND numberlist AS number " + 
				"WITH number " + 
				"WHERE number = 4 OR (number > 6 AND number < 10) " + 
				"RETURN number ");
		StatementResult unwindResult = assistant.read(unwindStatement);
		int i = 0;
		while (unwindResult.hasNext()) {
			Record record = unwindResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 3);
	}

	@Test
	public void temporal() throws Exception  {
		String temporalStatement = String.format("WITH localdatetime({ year:2018, month:1, day:1, hour:0, minute:0, second:0 }) AS localDateTime, " + 
				"duration({ years: 1, nanoseconds: 1 }) AS duration " + 
				"RETURN localDateTime + duration, localDateTime - duration, localDateTime + duration('P1M'), 2 * duration('P1M'), duration('P1M') / 2 ");
		StatementResult temporalResult = assistant.read(temporalStatement);
		int i = 0;
		while (temporalResult.hasNext()) {
			Record record = temporalResult.next();
			for(int j = 0, size = record.size(); j < size; ++j) {
				switch (j) {
				case 0:
					Assert.assertEquals(record.get(j).asLocalDateTime().toString(), "2019-01-01T00:00:00.000000001");
					break;
				case 1:
					Assert.assertEquals(record.get(j).asLocalDateTime().toString(), "2016-12-31T23:59:59.999999999");
					break;
				case 2:
					Assert.assertEquals(record.get(j).asLocalDateTime().toString(), "2018-02-01T00:00");
					break;
				case 3:
					Assert.assertEquals(record.get(j).asIsoDuration().toString(), "P2M0DT0S");
					break;
				case 4:
					Assert.assertEquals(record.get(j).asIsoDuration().toString(), "P0M15DT18873S");
					break;
				default:
					throw new RuntimeException(String.format("index(%d) not implements !!!", j));
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	@Test
	public void map() throws Exception {
		dynamicallyAccessingMap();
		projectionMap();
	}

	@Test
	public void list() {
		concatenateList();
		checkIfNumberIsInList();
		checkIfListIsInList();
		accessingElementsInList();
		dynamicallyAccessingList();
		rangeAndSubList();
		listComprehension();
	}

	@Test
	public void pattern() throws Exception {
		nodesWithRelationship();
		nodesWithRelationshipOfBothDirection();
	}

	@Test
	public void spatial() throws Exception {
		geographicCoordinateReferenceSystems();
		cartesianCoordinateReferenceSystems();
	}
	
	@Test
	public void function() throws Exception {
		shortestPath();
		allShortestPaths();
		exists();
		extract();
		filter();
		reduce();
	}

	@Test
	public void distinct() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s)-[:t]->(n_1:%s) " +
			"CREATE (n_0)-[:t]->(n_1) " +
			"CREATE (n_0)-[:t]->(n_1) "
			, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH (n_0:%s)-[:t]->(n_1:%s) RETURN DISTINCT n_0, n_1",
			label, label
		);	
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.size(), 2);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	@Test
	public void procedure() throws Exception  {
		callLabels();
		callLabelsWithYield();
		callLabelsWithYieldAndWhere();
		callConstraints();
	}
	
	private void callConstraints() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE CONSTRAINT ON (n:%s) ASSERT n.p IS UNIQUE", label);
		StatementResult createResult = assistant.write(createStatement);
		String callStatement = String.format(
				"CALL db.constraints() ");	
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			if(String.format("CONSTRAINT ON ( %s:%s ) ASSERT %s.p IS UNIQUE", label, label, label).equals(record.get(0).asString()) == true) {
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

	private void callLabelsWithYieldAndWhere() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s) "
			, label);
		StatementResult createResult = assistant.write(createStatement);
		String callStatement = String.format(
				"CALL db.labels() YIELD label " +
				"WHERE label =~ 'l_.*' " +
				"RETURN count(label) ");	
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Assert.assertTrue(record.get(0).asInt() > 0);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void callLabelsWithYield() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s) "
			, label);
		StatementResult createResult = assistant.write(createStatement);
		String callStatement = String.format(
				"CALL db.labels() YIELD label " +
				"RETURN count(label) ");	
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Assert.assertTrue(record.get(0).asInt() > 0);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void callLabels() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s) "
			, label);
		StatementResult createResult = assistant.write(createStatement);
		String callStatement = String.format("CALL db.labels ");	
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			if(label.equals(record.get(0).asString()) == true) {
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

	private void reduce() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s {p:1})-[:t]->(n_1:%s {p:2}) "
			, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p=(n_0:%s)-->(n_1:%s) " +
			"RETURN reduce(accumulator = 0, node IN nodes(p) | accumulator + node.p)",
			label, label
		);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.get(0).asLong(), 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void filter() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s {p:'A'})-[:t]->(n_1:%s {p:'B'}) "
			, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p=(n_0:%s)-->(n_1:%s) " +
			"RETURN filter(node IN nodes(p) WHERE node.p = 'B') ",
			label, label
		);	
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.size(), 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void extract() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s {p:'A'})-[:t]->(n_1:%s {p:'B'}) "
			, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p=(n_0:%s)-->(n_1:%s) " +
			"RETURN extract(node IN nodes(p) | node.p) ",
			label, label
		);	
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.get(0).asList().toString(), "[A, B]");
			Assert.assertEquals(record.size(), 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void exists() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s {p:'A'}) " +
			"CREATE (n_1:%s) "
			, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH (n:%s) " +
			"WHERE exists(n.p) " +
			"RETURN n",
			label, label
		);	
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.size(), 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void allShortestPaths() throws Exception  {
		String label_0 = "l_" + getCurrentTimeMillis();
		String label_1 = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s)-[:t]->(n_1:%s) " +
			"CREATE (n_0)-[:t]->(n_1) " +
			"CREATE (n_0)-[:t]->()-[:t]->(n_1) " +
			"CREATE (n_0)-[:t]->()-[:t]->()-[:t]->(n_1) "
			, label_0, label_1);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = allShortestPaths((n_0:%s)-[*]->(n_1:%s)) RETURN length(p)",
			label_0, label_1
		);	
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.get(0).asInt(), 1);
			++i;
		}
		Assert.assertEquals(i, 2);
	}

	private void shortestPath() throws Exception  {
		String label_0 = "l_" + getCurrentTimeMillis();
		String label_1 = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s)-[:t]->(n_1:%s) " +
			"CREATE (n_0)-[:t]->()-[:t]->(n_1) " +
			"CREATE (n_0)-[:t]->()-[:t]->()-[:t]->(n_1) "
			, label_0, label_1);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = shortestPath((n_0:%s)-[*]->(n_1:%s)) RETURN length(p)",
			label_0, label_1
		);	
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.get(0).asInt(), 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void cartesianCoordinateReferenceSystems() {
		String spatialStatement = "WITH point({ x:0, y:0, z:0 }) AS p1, " +
				"point({ x:3, y:4, z:0 }) AS p2 " + 
				"RETURN distance(p1,p2) ";
		StatementResult spatialResult = assistant.read(spatialStatement);
		int i = 0;
		while (spatialResult.hasNext()) {
			Record record = spatialResult.next();
			Assert.assertEquals(record.get(0).toString(), "5.0");
			++i;
		}
		Assert.assertEquals(i, 1);	
	}

	private void geographicCoordinateReferenceSystems() {
		String spatialStatement = "WITH point({ latitude:toFloat('13.43'), longitude:toFloat('56.21')}) AS p1, " +
				"point({ latitude:toFloat('13.10'), longitude:toFloat('56.41')}) AS p2 " + 
				"RETURN distance(p1,p2) as meter ";
		StatementResult spatialResult = assistant.read(spatialStatement);
		int i = 0;
		while (spatialResult.hasNext()) {
			Record record = spatialResult.next();
			Assert.assertEquals(record.get(0).toString(), "42650.62522789842");
			++i;
		}
		Assert.assertEquals(i, 1);	
	}

	private void nodesWithRelationshipOfBothDirection() throws Exception  {
		String label_0 = "l_0" + getCurrentTimeMillis();
		String label_1 = "l_1" + getCurrentTimeMillis();
		// @formatter:off
		String createStatement = String.format(
			"CREATE (n_0:%s)-[:t]->(n_1:%s) "
			, label_0, label_1); 
		// @formatter:on
		assistant.write(createStatement);
		String matchStatement = String.format("MATCH p = (n_0:%s)--(n_1:%s) RETURN p", label_0, label_1);
		StatementResult matchResult = assistant.read(matchStatement);
		Assert.assertTrue(matchResult.hasNext());
	}

	private void nodesWithRelationship() throws Exception  {
		String label_0 = "l_0" + getCurrentTimeMillis();
		String label_1 = "l_1" + getCurrentTimeMillis();
		// @formatter:off
		String createStatement = String.format(
			"CREATE (n_0:%s)-[:t]->(n_1:%s) "
			, label_0, label_1); 
		// @formatter:on
		assistant.write(createStatement);
		String matchStatement = String.format("MATCH p = (n_0:%s)-->(n_1:%s) RETURN p", label_0, label_1);
		StatementResult matchResult = assistant.read(matchStatement);
		Assert.assertTrue(matchResult.hasNext());
	}

	private void listComprehension() {
		String listStatement = "RETURN [x IN range(0,3) WHERE x % 2 = 0 | x^3] ";
		StatementResult listResult = assistant.read(listStatement);
		int i = 0;
		while (listResult.hasNext()) {
			List<Object> list = listResult.next().get(0).asList();
			for(int j = 0, size = list.size(); j < size; ++j) {
				String result = list.get(j).toString();
				switch (j) {
				case 0:
					Assert.assertEquals(result, "0.0");
					break;
				case 1:
					Assert.assertEquals(result, "8.0");
					break;
				default:
					throw new RuntimeException(String.format("index(%d) not implements !!!", j));
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);	
	}
	
	private void rangeAndSubList() {
		String listStatement = String.format(
				"RETURN range(0, 10)[0..-5] ");
		StatementResult listResult = assistant.read(listStatement);
		int i = 0;
		while (listResult.hasNext()) {
			List<Object> list = listResult.next().get(0).asList();
			for(int j = 0, size = list.size(); j < size; ++j) {
				Long result = (Long)list.get(j);
				Assert.assertEquals(result.intValue(), j);
			}
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void dynamicallyAccessingList() {
		String listStatement = String.format(
				"WITH ['A', 'B', 'C', 'D', 'E'] AS list " + 
				"RETURN list[$index] ");
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("index", 3);
		StatementResult listResult = assistant.readWithParameter(listStatement, parameter);
		int i = 0;
		while (listResult.hasNext()) {
			Record record = listResult.next();
			for(int j = 0, size = record.size(); j < size; ++j) {
				String result = record.get(j).asString();
				switch (j) {
				case 0:
					Assert.assertEquals(result, "D");
					break;
				default:
					throw new RuntimeException(String.format("index(%d) not implements !!!", j));
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);	
	}

	private void accessingElementsInList() {
		String listStatement = String.format(
				"WITH ['A', 'B', 'C', 'D', 'E'] AS list " + 
				"RETURN list[1..3] ");
		StatementResult listResult = assistant.read(listStatement);
		int i = 0;
		while (listResult.hasNext()) {
			List<Object> list = listResult.next().get(0).asList();
			for(int j = 0, size = list.size(); j < size; ++j) {
				String result = list.get(j).toString();
				switch (j) {
				case 0:
					Assert.assertEquals(result, "B");
					break;
				case 1:
					Assert.assertEquals(result, "C");
					break;
				default:
					throw new RuntimeException(String.format("index(%d) not implements !!!", j));
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);	
	}

	private void checkIfListIsInList() {
		String listStatement = String.format(
				"RETURN [2, 1] IN [0,[2, 1], 3] ");
		StatementResult listResult = assistant.read(listStatement);
		int i = 0;
		while (listResult.hasNext()) {
			boolean result = listResult.next().get(0).asBoolean();
			switch (i) {
			case 0:
				Assert.assertTrue(result);
				break;
			default:
				break;
			}
			++i;
		}
		Assert.assertEquals(i, 1);	
	}

	private void checkIfNumberIsInList() {
		String listStatement = String.format(
				"WITH [1, 3, 5, 7] AS list " + 
				"UNWIND list AS ele " + 
				"WITH ele " + 
				"WHERE ele IN [3, 4, 5]" + 
				"RETURN ele");
		StatementResult listResult = assistant.read(listStatement);
		int i = 0;
		while (listResult.hasNext()) {
			int ele = listResult.next().get(0).asInt();
			switch (i) {
			case 0:
				Assert.assertEquals(ele, 3);
				break;
			case 1:
				Assert.assertEquals(ele, 5);
				break;
			default:
				break;
			}
			++i;
		}
		Assert.assertEquals(i, 2);	
	}

	private void concatenateList() {
		String listStatement = String.format(
				"RETURN [0,1,2]+[3,4] ");
		StatementResult listResult = assistant.read(listStatement);
		int i = 0;
		while (listResult.hasNext()) {
			List<Object> list = listResult.next().get(0).asList();
			for(int j = 0, size = list.size(); j < size; ++j) {
				Assert.assertEquals(((Long)list.get(j)).intValue(), j);
			}
			++i;
		}
		Assert.assertEquals(i, 1);	
	}

	private void projectionMap() throws InterruptedException {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0:%s {p_0: 0, p_1: 1, p_a: 'A', p_b: 'B'})-[r:t]->(n_1:%s {p_0: 0, p_1: 1, p_a: 'A', p_b: 'B'})", label, label);
		assistant.write(createStatement);
		String matchStatement = String.format(
				"MATCH (n_0:%s)-[:t]->(n_1:%s) " +
				"RETURN n_0 { .*, n_1: collect(n_1 { .p_0, .p_a }) } ", label, label);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			@SuppressWarnings("unchecked")
			Map<String, Object> outerMap = (Map<String, Object>)matchResult.next().asMap().get("n_0");
			Assert.assertEquals(outerMap.get("p_0"), 0L);
			Assert.assertEquals(outerMap.get("p_1"), 1L);
			Assert.assertEquals(outerMap.get("p_a"), "A");
			Assert.assertEquals(outerMap.get("p_b"), "B");
			@SuppressWarnings("unchecked")
			Map<String, Object> innerMap = (Map<String, Object>)((List<Object>)outerMap.get("n_1")).get(0);
			Assert.assertEquals(innerMap.get("p_0"), 0L);
			Assert.assertEquals(innerMap.get("p_a"), "A");
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void dynamicallyAccessingMap() {
		String mapStatement = String.format(
				"WITH { key_0: 'value_0', key_1: 3 } AS map " + 
				"RETURN map[$key] ");
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("key", "key_0");
		StatementResult mapResult = assistant.readWithParameter(mapStatement, parameter);
		int i = 0;
		while (mapResult.hasNext()) {
			Record record = mapResult.next();
			Assert.assertEquals(record.get(0).asString(), "value_0");
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void dropConstraint(String label) {
		String constraintStatement = String.format("DROP CONSTRAINT ON (n:%s) ASSERT n.p IS UNIQUE", label);
		StatementResult constraintResult = assistant.write(constraintStatement);
	}
	
	private void createConstraint(String label) {
		String createStatement = String.format("CREATE (n:%s {p:'A'}) RETURN n", label);
		assistant.write(createStatement);
		String constraintStatement = String.format("CREATE CONSTRAINT ON (n:%s) ASSERT n.p IS UNIQUE", label);
		StatementResult constraintResult = assistant.write(constraintStatement);
		Exception ex = null;
		try {
			assistant.write(createStatement);
		} catch (Exception e) {
			ex = e;
		}
		Assert.assertEquals(ex.getClass().getName(), "org.neo4j.driver.v1.exceptions.ClientException");
	}
	
	private void dropIndex(String label) {
		String indexStatement = String.format("DROP INDEX ON :%s(p)", label);
		StatementResult indexResult = assistant.write(indexStatement);
	}
	
	private void createIndex(String label) {
		String createStatement = String.format("CREATE (n_0:%s {p:'A'}), (n_1:%s {p:'B'}) RETURN n_0, n_1", label, label);
		StatementResult createResult = assistant.write(createStatement);
		String indexStatement = String.format("CREATE INDEX ON :%s(p)", label);
		StatementResult indexResult = assistant.write(indexStatement);
	}

	private void nodeCount() throws Exception  {
		String property = "p_0" + getCurrentTimeMillis();
		// @formatter:off
		String createStatement_0_0 = String.format(
			"CREATE (n_0 {p:'%s'})-[r_0_0:t {p:'%s'}]->(n_1 {p:'%s'})-[r_1_0:t {p:'%s'}]->(n_2 {p:'%s'}) " + 
			"CREATE (n_0)-[r_0_1:t {p:'%s'}]->(n_1)-[r_1_1:t {p:'%s'}]->(n_2) "
			, property, property, property, property, property, property, property); 
		// @formatter:on
		assistant.write(createStatement_0_0);
		String countStatement = String.format("MATCH (n_0 {p:'%s'})-[r_0 {p:'%s'}]->(n_1 {p:'%s'})-[r_1 {p:'%s'}]->(n_2 {p:'%s'}) RETURN n_0, n_2, count(*)", property, property, property, property, property);
		StatementResult countResult = assistant.read(countStatement);
		int i = 0;
		while (countResult.hasNext()) {
			Record record = countResult.next();
			int size = record.size();
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				if(value instanceof IntegerValue) {
					Assert.assertEquals(value.asInt(), 4);
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void relationshipCount() throws Exception  {
		String property = "p_0" + getCurrentTimeMillis();
		// @formatter:off
		String createStatement_0_0 = String.format(
			"CREATE (n_0 {p:'%s'})-[r_0_0:t {p:'%s'}]->(n_1 {p:'%s'})-[r_1_0:t {p:'%s'}]->(n_2 {p:'%s'})-[r_2_0:t {p:'%s'}]->(n_3 {p:'%s'}) " + 
			"CREATE (n_0)-[r_0_1:t {p:'%s'}]->(n_1)-[r_1_1:t {p:'%s'}]->(n_2)-[r_2_1:t {p:'%s'}]->(n_3) "
			, property, property, property, property, property, property, property, property, property, property); 
		// @formatter:on
		assistant.write(createStatement_0_0);
		String countStatement = String.format("MATCH (n_0 {p:'%s'})-[r_0 {p:'%s'}]->(n_1 {p:'%s'})-[r_1 {p:'%s'}]->(n_2 {p:'%s'})-[r_2 {p:'%s'}]->(n_3 {p:'%s'}) RETURN r_0, r_2, count(*)", property, property, property, property, property, property, property);
		StatementResult countResult = assistant.read(countStatement);
		int i = 0;
		while (countResult.hasNext()) {
			Record record = countResult.next();
			int size = record.size();
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				if(value instanceof IntegerValue) {
					Assert.assertEquals(value.asInt(), 2);
				}
			}
			++i;
		}
		Assert.assertEquals(i, 4);
	}

	private void basicCount() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s) RETURN n", label);
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String countStatement = String.format("MATCH (n:%s) RETURN n, count(*)", label);
		StatementResult countResult = assistant.read(countStatement);
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
	
	private void whereWithChainingComparison() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s {p:3})", label);
		StatementResult createResult = assistant.write(createStatement);
		String whereStatement = String.format(
			"MATCH (n:%s) WHERE 2 < n.p <= 3 RETURN n ",
			label
		);
		StatementResult whereResult = assistant.read(whereStatement);
		Assert.assertTrue(whereResult.hasNext());
	}
	
	private void whereWithDynamicalProperty() throws Exception  {
		String label_0 = "l_" + getCurrentTimeMillis();
		String label_1 = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0:%s {score_A:3}) " + 
			"CREATE (n_1:%s {type:'A'}) ", 
			label_0, label_1);
		StatementResult createResult = assistant.write(createStatement);
		String whereStatement = String.format(
			"MATCH (n_0:%s), (n_1:%s) " +
			"WHERE n_0['score_' + n_1.type] = 3 " +
			"RETURN n_0['score_' + n_1.type] ",
			label_0, label_1
		);
		StatementResult whereResult = assistant.read(whereStatement);
		int i = 0;
		while (whereResult.hasNext()) {
			Record record = whereResult.next();
			Value value = record.get(0);
			Assert.assertEquals(value.asInt(), 3);
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	private void whereWithRelationship() throws Exception  {
		String relationshipType = "t_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0)-[r:%s]->(n_1:l {p:'A'}) RETURN n_0, r, n_1", relationshipType);
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String whereStatement = String.format(
			"MATCH (n_0) " +
			"WHERE (n_0)-[:%s]->(:l {p:'A'}) " +
			"RETURN n_0",
			relationshipType
		);
		StatementResult whereResult = assistant.read(whereStatement);
		Assert.assertTrue(whereResult.hasNext());
	}

	private void whereWithMultipleConditions() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s {p_0:'A', p_1:3}) RETURN n", label);
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String whereStatement = String.format(
			"MATCH (n:%s) " +
			"WHERE n.p_0='A' AND n.p_1 > 0 " +
			"RETURN n",
			label
		);
		StatementResult whereResult = assistant.read(whereStatement);
		Assert.assertTrue(whereResult.hasNext());
	}

	private void optionalMatchNode() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s) RETURN n", label);
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format(
			"MATCH (n:%s) " +
			"OPTIONAL MATCH (n)-[r:not_exist]->(n_not_exist) " +
			"RETURN n, r, n_not_exist", label);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		int i = 0;
		while (matchSetResult.hasNext()) {
			matchSetResult.next();
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void matchNodeWithLessOrEqualToThreeRelationship() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_1:%s)-[:t]->(n_0_2:%s) " +
			"CREATE (n_1_0:%s)-[:t]->(n_1_1:%s)-[:t]->(n_1_2:%s) " +
			"CREATE (n_2_0:%s)-[:t]->(n_2_1:%s)-[:t]->(n_2_2:%s)-[:t]->(n_2_3:%s) "
			, label, label, label, label, label
			, label, label, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = (n_0:%s)-[*..3]->(n_1:%s) RETURN p",
			label, label
		);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 10);
	}

	private void matchAnyLengthRelationship() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_1:%s)-[:t]->(n_0_2:%s) " +
			"CREATE (n_1_0:%s)-[:t]->(n_1_1:%s)-[:t]->(n_1_2:%s) "
			, label, label, label, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = (n_0:%s)-[*]->(n_1:%s) RETURN p",
			label, label
		);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 4);
	}

	private void matchAnyLengthRelationshipWithProperty() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_0:%s)-[:t {p:'A'}]->(n_0_1:%s) " +
			"CREATE (n_1_0:%s)-[:t {p:'B'}]->(n_1_1:%s) " 
			, label, label, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p =(:%s)-[* {p:'B'}]->(:%s) RETURN p",
			label, label
		);	
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void matchNodeWithMoreThanTwoRelationshipAndLabel() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_1:%s)-[:t]->(n_0_2:%s) " +
			"CREATE (n_1_0:%s)-[:t]->(n_1_1:%s)-[:t]->(n_1_2:%s) " +
			"CREATE (n_2_0:%s)-[:t]->(n_2_1:%s)-[:t]->(n_2_2:%s)-[:t]->(n_2_3:%s) " +
			"CREATE (n_3_0:%s)-[:t]->(n_3_1:%s)-[:t]->(n_3_2:%s)-[:t]->(n_3_3:%s)-[:t]->(n_3_4:%s) "
			, label, label, label, label, label, label, label, label, 
			label, label, label, label, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = (n_0:%s)-[:t *2..]->(n_1:%s) RETURN p",
			label, label
		);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 10);
	}

	private void matchNodeWithMoreThanTwoRelationship() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_1:%s)-[:t]->(n_0_2:%s) " +
			"CREATE (n_1_0:%s)-[:t]->(n_1_1:%s)-[:t]->(n_1_2:%s) " +
			"CREATE (n_2_0:%s)-[:t]->(n_2_1:%s)-[:t]->(n_2_2:%s)-[:t]->(n_2_3:%s) " +
			"CREATE (n_3_0:%s)-[:t]->(n_3_1:%s)-[:t]->(n_3_2:%s)-[:t]->(n_3_3:%s)-[:t]->(n_3_4:%s) "
			, label, label, label, label, label, label, label, label, 
			label, label, label, label, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = (n_0:%s)-[*2..]->(n_1:%s) RETURN p",
			label, label
		);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 10);
	}

	private void matchNodeWithTwoToThreeRelationship() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_1:%s)-[:t]->(n_0_2:%s) " +
			"CREATE (n_1_0:%s)-[:t]->(n_1_1:%s)-[:t]->(n_1_2:%s) " +
			"CREATE (n_2_0:%s)-[:t]->(n_2_1:%s)-[:t]->(n_2_2:%s)-[:t]->(n_2_3:%s) " +
			"CREATE (n_3_0:%s)-[:t]->(n_3_1:%s)-[:t]->(n_3_2:%s)-[:t]->(n_3_3:%s)-[:t]->(n_3_4:%s) "
			, label, label, label, label, label, label, label, label, 
			label, label, label, label, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = (n_0:%s)-[*2..3]->(n_1:%s) RETURN p",
			label, label
		);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 9);
	}

	private void matchNodeWithTwoRelationship() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_1:%s)-[:t]->(n_0_2:%s) " +
			"CREATE (n_1_0:%s)-[:t]->(n_1_1:%s)-[:t]->(n_1_2:%s) "
			, label, label, label, label, label);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
			"MATCH p = (n_0:%s)-[*2]->(n_1:%s) RETURN p",
			label, label
		);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int size = record.size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	private void matchNodeByMultiRelationshipType() throws Exception  {
		String relationshipType_0 = "l_0" + getCurrentTimeMillis();
		String relationshipType_1 = "l_1" + getCurrentTimeMillis();
		String createStatement = String.format(
			"CREATE (n_0_0)-[r_0:%s]->(n_0_1) " +
			"CREATE (n_1_0)-[r_1:%s]->(n_1_1) ", 
			relationshipType_0, relationshipType_1);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format("MATCH p = (n_0)-[r:%s|:%s]->(n_1) RETURN p", relationshipType_0, relationshipType_1);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			matchResult.next();
			++i;
		}
		Assert.assertEquals(i, 2);
	}

	private void matchNodeByRelationshipType() throws Exception  {
		String relationshipType = "t_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n_0)-[r:%s]->(n_1) RETURN n_0, r, n_1", relationshipType);
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format("MATCH (n_0)-[r:%s]->(n_1) RETURN n_1", relationshipType);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
	}

	private void matchNodeByLabel() throws Exception  {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s) RETURN n", label);
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format("MATCH (n:%s) RETURN n", label);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
	}

	private void matchAllNode() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String matchSetStatement = String.format("MATCH (n) RETURN n");
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
	}

	private void removeMultiLabel() {
		String createStatement = String.format("CREATE (n:l_0:l_1:l_2) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String removeStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"REMOVE n:l_0:l_2 " +
			"RETURN n",
			id
		);
		StatementResult removeResult = assistant.write(removeStatement);
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
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String removeStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"REMOVE n:l_1 " +
			"RETURN n",
			id
		);
		StatementResult removeResult = assistant.write(removeStatement);
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
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String removeStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"REMOVE n.p_1 " +
			"RETURN n",
			id
		);
		StatementResult removeResult = assistant.write(removeStatement);
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
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String detachDeleteStatement = String.format(
			"MATCH (n:l {p_0:'A', p_1:'B', p_2:'C'}) " +
			"DETACH DELETE n"
		);
		assistant.write(detachDeleteStatement);
		String matchStatement = String.format(
			"MATCH (n:l {p_0:'A', p_1:'B', p_2:'C'}) " +
			"RETURN n"
		);
		StatementResult matchResult = assistant.read(matchStatement);
		Assert.assertFalse(matchResult.hasNext());
	}

	private void deleteSingleNode() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String detachDeleteStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"DETACH DELETE n",
			id
		);
		assistant.write(detachDeleteStatement);
		String matchStatement = String.format(
			"MATCH (n) " +
			"WHERE ID(n) = %d " +
			"RETURN n",
			id
		);
		StatementResult matchResult = assistant.read(matchStatement);
		Assert.assertFalse(matchResult.hasNext());
	}

	private void deleteAllNode() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String matchStatement = String.format(
				"MATCH (n) " +
				"RETURN n");
		StatementResult matchResult = assistant.read(matchStatement);
		Assert.assertFalse(matchResult.hasNext());
	}

	private void replaceNodeAllProperty() throws InterruptedException {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s {p_0:'A'})", label);
		StatementResult createResult = assistant.write(createStatement);
		String matchSetStatement = String.format(
				"MATCH (n:%s) " +
				"SET n = {p_1:'B'} " +
				"RETURN n.p_0, n.p_1 ", label);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
		int i = 0;
		while (matchSetResult.hasNext()) {
			Record record = matchSetResult.next();
			int size = record.size();
			Assert.assertEquals(size, 2);
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				switch (j) {
				case 0:
					Assert.assertTrue(value.isNull());
					break;
				case 1:
					Assert.assertEquals(value.asString(), "B");
					break;
				default:
					break;
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);
	}
	
	private void updateNodeAllProperty() throws InterruptedException {
		String label = "l_" + getCurrentTimeMillis();
		String createStatement = String.format("CREATE (n:%s {p_0:'A'})", label);
		StatementResult createResult = assistant.write(createStatement);
		String matchSetStatement = String.format(
				"MATCH (n:%s) " +
				"SET n += {p_1:'B'} " +
				"RETURN n.p_0, n.p_1 ", label);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
		int i = 0;
		while (matchSetResult.hasNext()) {
			Record record = matchSetResult.next();
			int size = record.size();
			Assert.assertEquals(size, 2);
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				switch (j) {
				case 0:
					Assert.assertEquals(value.asString(), "A");
					break;
				case 1:
					Assert.assertEquals(value.asString(), "B");
					break;
				default:
					break;
				}
			}
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void setLabel() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String label_0 = "l_0";
		String label_1 = "l_1";
		String matchSetStatement = String.format(
				"MATCH (n) WHERE ID(n) = %d " +
				"SET n:%s:%s " +
				"RETURN n", id, label_0, label_1);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
		int i = 0;
		for(String label : matchSetResult.next().get(0).asNode().labels()) {
			++i;
		}
		Assert.assertEquals(i, 2);
	}

	private void removeNodeProperty() {
		String createStatement = String.format("CREATE (n {p:'A'}) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String matchSetStatement = String.format(
				"MATCH (n) WHERE ID(n) = %d " +
				"SET n.p = NULL " +
				"RETURN n", id);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
		Assert.assertTrue(matchSetResult.hasNext());
		int i = 0;
		for(Value value : matchSetResult.next().get(0).asNode().values()) {
			++i;
		}
		Assert.assertEquals(i, 0);
	}

	private void setNodeProperty() {
		String createStatement = String.format("CREATE (n) RETURN n");
		StatementResult createResult = assistant.write(createStatement);
		Assert.assertTrue(createResult.hasNext());
		long id = createResult.next().get(0).asNode().id();
		String property_0 = "p_0";
		String property_1 = "p_1";
		String matchSetStatement = String.format(
				"MATCH (n) WHERE ID(n) = %d " +
				"SET n.p_0 = '%s', n.p_1 = '%s' " +
				"RETURN n", id, property_0, property_1);
		StatementResult matchSetResult = assistant.write(matchSetStatement);
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
		assistant.write(createStatement);
		String mergeStatement = String.format(
			"MATCH (n_0:%s), (n_1:%s) " +
			"WHERE n_0.p_0 = '%s' AND n_1.p_1 = '%s' " +
			"MERGE (n_0)-[r:t_0]->(n_1) " +
			"RETURN n_0, r, n_1", label_0, label_1, property_0, property_1);
		assistant.write(mergeStatement);
		StatementResult mergeResult = assistant.write(mergeStatement);
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
		assistant.write(createStatement);
		String mergeStatement = String.format("MERGE (n:%s) RETURN n", label);
		StatementResult mergeResult = assistant.write(mergeStatement);
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
		assistant.write(createStatement);
		String mergeStatement = String.format("MERGE (n {p_0:'%s'}) RETURN n", property);
		StatementResult mergeResult = assistant.write(mergeStatement);
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
		StatementResult mergeResult = assistant.write(mergeStatement);
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
		StatementResult createResult = assistant.write(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			int size = createResult.next().size();
			Assert.assertEquals(size, 1);
			++i;
		}
		Assert.assertEquals(i, 1);
	}

	private void createNodeWithEscapeCharacter() {
		String escapeCharacter = "TYPE\\nWITH SPACE";
		String createStatement = String.format("CREATE ()-[:`%s`]->()", escapeCharacter);
		StatementResult createResult = assistant.write(createStatement);
		String matchStatement = String.format(
				"MATCH ()-[r:`%s`]->() " + 
				"RETURN type(r)",
				escapeCharacter);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			Assert.assertEquals(record.get(0).asString(), escapeCharacter);
			++i;
		}
		Assert.assertEquals(i, 1);
	}	

	private void createMultipleNodes() {
		String createStatement = String.format("CREATE (n_0), (n_1) RETURN n_0, n_1");
		StatementResult createResult = assistant.write(createStatement);
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
		StatementResult createResult = assistant.write(createStatement);
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
		StatementResult createResult = assistant.write(createStatement);
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
		StatementResult createResult = assistant.write(createStatement);
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
		String createStatement = String.format("CREATE (n_0)-[r:t_0]->(n_1) RETURN n_0, r, n_1");
		StatementResult createResult = assistant.write(createStatement);
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
		String createStatement = String.format("CREATE (n_0)-[r:t_0 {p_0:'A', p_1:'B', p_2:'C'}]->(n_1) RETURN n_0, r, n_1");
		StatementResult createResult = assistant.write(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			Record record = createResult.next();
			int size = record.size();
			Assert.assertEquals(size, 3);
			for(int j = 0; j < size; ++j) {
				Value value = record.get(j);
				if(value instanceof RelationshipValue) {
					Relationship relationship = value.asRelationship();
					Assert.assertEquals(relationship.type(), "t_0");
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
		StatementResult createResult = assistant.write(createStatement);
		int i = 0;
		while (createResult.hasNext()) {
			Record record = createResult.next();
			int size = record.size();
			Assert.assertEquals(size, 2);
			String matchCreateStatement = String.format(
					"MATCH (n_0:l_0), (n_1:l_1) WHERE n_0.p_0='%s' AND n_1.p_1='%s' " +
					"CREATE (n_0)-[r:t_0]->(n_1) RETURN n_0, r, n_1", property_0, property_1);
			StatementResult matchCreateResult = assistant.write(matchCreateStatement);
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
		String createStatement = String.format("CREATE (n_0)-[r_0:t_0]->(n_1)-[r_1:t_1]->(n_2) RETURN n_0, r_0, n_1, r_1, n_2");
		StatementResult createResult = assistant.write(createStatement);
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
