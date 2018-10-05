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
public class Neo4jAlgoAssistantTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private Neo4jAssistant assistant;

//	@Test
	public void procedure() throws Exception  {
		callAlgorithmList();
	}

	@Test
	public void algorithm() throws Exception  {
		centralities();
		communityDetection();
		pathFinding();
		similarity();
		preprocessing();
	}
	
	private void preprocessing() {
//		oneHotEncoding();
	}
	
	private void similarity() {
//		jaccard()
//		cosine();
//		euclidean();
	}

	private void pathFinding() {
//		mst();
//		shortestPath();
//		shortestPath();
//		allShortestPaths();
//		astar();
//		kShortestPaths();
//		randomWalk();
	}
	
	private void communityDetection() {
//		louvain();
//		labelPropagation();
//		unionFind();
//		scc();
//		triangleCount();
	}
	
	private void centralities() throws Exception {
		initPageRankData();
		pageRank();
		personalizedPageRank();
//		betweenness();
//		closeness();
//		harmonic();
	}

	private void personalizedPageRank() throws Exception  {
		String callStatement = String.format(
				" MATCH (siteA:Page {name: 'Site A'}) " +
				" CALL algo.pageRank.stream('Page', 'LINKS', {iterations:20, dampingFactor:0.85, sourceNodes: [siteA]})}) " + 
				" YIELD nodeId, score " + 
				" MATCH (node) WHERE id(node) = nodeId " + 
				" RETURN node.name AS page,score " + 
				" ORDER BY score DESC ");	
		StatementResult callResult = assistant.read(callStatement);
		Assert.assertEquals(callResult.next().get(0).asString(), "Home");
	}

	private void pageRank() throws Exception  {
		String callStatement = String.format(
				" CALL algo.pageRank.stream('Page', 'LINKS', {iterations:20, dampingFactor:0.85}) " + 
				" YIELD nodeId, score " + 
				" MATCH (node) WHERE id(node) = nodeId " + 
				" RETURN node.name AS page,score " + 
				" ORDER BY score DESC ");	
		StatementResult callResult = assistant.read(callStatement);
		Assert.assertEquals(callResult.next().get(0).asString(), "Home");
	}

	private void callAlgorithmList() throws Exception  {
		String callStatement = String.format(
				"CALL algo.list() ");	
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			++i;
		}
		Assert.assertTrue(0 < i);
	}

	private void initPageRankData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (home:Page {name:'Home'}) " + 
			"MERGE (about:Page {name:'About'}) " + 
			"MERGE (product:Page {name:'Product'}) " + 
			"MERGE (links:Page {name:'Links'}) " + 
			"MERGE (a:Page {name:'Site A'}) " + 
			"MERGE (b:Page {name:'Site B'}) " + 
			"MERGE (c:Page {name:'Site C'}) " + 
			"MERGE (d:Page {name:'Site D'}) " + 
			"MERGE (home)-[:LINKS]->(about) " + 
			"MERGE (about)-[:LINKS]->(home) " + 
			"MERGE (product)-[:LINKS]->(home) " + 
			"MERGE (home)-[:LINKS]->(product) " + 
			"MERGE (links)-[:LINKS]->(home) " + 
			"MERGE (home)-[:LINKS]->(links) " + 
			"MERGE (links)-[:LINKS]->(a) " + 
			"MERGE (a)-[:LINKS]->(home) " + 
			"MERGE (links)-[:LINKS]->(b) " + 
			"MERGE (b)-[:LINKS]->(home) " + 
			"MERGE (links)-[:LINKS]->(c) " + 
			"MERGE (c)-[:LINKS]->(home) " + 
			"MERGE (links)-[:LINKS]->(d) " + 
			"MERGE (d)-[:LINKS]->(home) ");
		StatementResult createResult = assistant.write(createStatement);
	}

	private long getCurrentTimeMillis() throws InterruptedException {
		Thread.sleep(1);
		return System.currentTimeMillis();
	}
}
