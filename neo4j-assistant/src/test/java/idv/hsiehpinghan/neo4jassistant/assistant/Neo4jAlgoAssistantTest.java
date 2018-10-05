package idv.hsiehpinghan.neo4jassistant.assistant;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
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
	
	private void centralities() throws Exception {
//		initPageRankData();
//		pageRank();
//		personalizedPageRank();
//		initBetweennessData();
//		betweenness();
//		approximationBetweenness();
//		initClosenessData();
//		closeness();
//		initClosenessHarmonic();
//		closenessHarmonic();
	}

	
	private void communityDetection() throws Exception {
//		initLouvainData();
//		louvain();
		initLabelPropagationData();
		labelPropagation();
//		unionFind();
//		scc();
//		triangleCount();
	}
	
	private void labelPropagation() throws Exception  {
		String callStatement = String.format(
			"CALL algo.labelPropagation.stream('User', 'FOLLOW', {direction: 'OUTGOING', iterations: 10}) " +
			"YIELD nodeId, label "
		);
		StatementResult callResult = assistant.read(callStatement);
		Set<Integer> labels = new HashSet<>();
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Integer label = record.get(1).asInt();
			labels.add(label);
		}
		Assert.assertEquals(labels.size(), 2);
	}

	private void louvain() throws Exception  {
		String callStatement = String.format(
				"CALL algo.louvain.stream('User', 'FRIEND', {}) " + 
				"YIELD nodeId, community " + 
				"MATCH (user:User) WHERE id(user) = nodeId " + 
				"RETURN user.id AS user, community " + 
				"ORDER BY community ");
		StatementResult callResult = assistant.read(callStatement);
		Set<Integer> communities = new HashSet<>();
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Integer community = record.get(1).asInt();
			communities.add(community);
		}
		Assert.assertEquals(communities.size(), 2);
	}

	private void closenessHarmonic() throws Exception  {
		String callStatement = String.format(
				"CALL algo.closeness.harmonic.stream('Node', 'LINK') YIELD nodeId, centrality " + 
				"MATCH (n:Node) WHERE id(n) = nodeId " + 
				"RETURN n.id AS node, centrality " + 
				"ORDER BY centrality DESC " + 
				"LIMIT 20 ");
		StatementResult callResult = assistant.read(callStatement);
		Assert.assertEquals(callResult.next().get(0).asString(), "B");
	}

	private void closeness() throws Exception  {
		String callStatement = String.format(
				"CALL algo.closeness.stream('Node', 'LINK') " + 
				"YIELD nodeId, centrality " + 
				"MATCH (n:Node) WHERE id(n) = nodeId " + 
				"RETURN n.id AS node, centrality " + 
				"ORDER BY centrality DESC " + 
				"LIMIT 20 ");
		StatementResult callResult = assistant.read(callStatement);
		Assert.assertEquals(callResult.next().get(0).asString(), "C");
	}

	private void approximationBetweenness() throws Exception  {
		String callStatement = String.format(
				"CALL algo.betweenness.sampled.stream('User','MANAGE', " + 
				"{strategy:'random', probability:1.0, maxDepth:1, direction: 'out'}) " + 
				"YIELD nodeId, centrality " + 
				"MATCH (user) WHERE id(user) = nodeId " + 
				"RETURN user.id AS user,centrality " + 
				"ORDER BY centrality DESC ");	
		StatementResult callResult = assistant.read(callStatement);
		Assert.assertEquals(callResult.next().get(0).asString(), "Alice");
	}

	private void betweenness() throws Exception  {
		String callStatement = String.format(
				"CALL algo.betweenness.stream('User','MANAGE',{direction:'out'}) " + 
				"YIELD nodeId, centrality " + 
				"MATCH (user:User) WHERE id(user) = nodeId " + 
				"RETURN user.id AS user,centrality " + 
				"ORDER BY centrality DESC ");	
		StatementResult callResult = assistant.read(callStatement);
		Assert.assertEquals(callResult.next().get(0).asString(), "Alice");
	}
	
	private void personalizedPageRank() throws Exception  {
		String callStatement = String.format(
				" MATCH (siteA:Page {name: 'Site A'}) " +
				" CALL algo.pageRank.stream('Page', 'LINKS', {iterations:20, dampingFactor:0.85, sourceNodes: [siteA]}) " + 
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

	private void initBetweennessData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (nAlice:User {id:'Alice'}) " + 
			"MERGE (nBridget:User {id:'Bridget'}) " + 
			"MERGE (nCharles:User {id:'Charles'}) " + 
			"MERGE (nDoug:User {id:'Doug'}) " + 
			"MERGE (nMark:User {id:'Mark'}) " + 
			"MERGE (nMichael:User {id:'Michael'}) " + 
			"MERGE (nAlice)-[:MANAGE]->(nBridget) " + 
			"MERGE (nAlice)-[:MANAGE]->(nCharles) " + 
			"MERGE (nAlice)-[:MANAGE]->(nDoug) " + 
			"MERGE (nMark)-[:MANAGE]->(nAlice) " + 
			"MERGE (nCharles)-[:MANAGE]->(nMichael) ");
		StatementResult createResult = assistant.write(createStatement);
	}

	private void initClosenessData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (a:Node{id:'A'}) " + 
			"MERGE (b:Node{id:'B'}) " + 
			"MERGE (c:Node{id:'C'}) " + 
			"MERGE (d:Node{id:'D'}) " + 
			"MERGE (e:Node{id:'E'}) " + 
			"MERGE (a)-[:LINK]->(b) " + 
			"MERGE (b)-[:LINK]->(a) " + 
			"MERGE (b)-[:LINK]->(c) " + 
			"MERGE (c)-[:LINK]->(b) " + 
			"MERGE (c)-[:LINK]->(d) " + 
			"MERGE (d)-[:LINK]->(c) " + 
			"MERGE (d)-[:LINK]->(e) " + 
			"MERGE (e)-[:LINK]->(d) ");
		StatementResult createResult = assistant.write(createStatement);
	}
	
	private void initClosenessHarmonic() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (a:Node{id:'A'}) " + 
			"MERGE (b:Node{id:'B'}) " + 
			"MERGE (c:Node{id:'C'}) " + 
			"MERGE (d:Node{id:'D'}) " + 
			"MERGE (e:Node{id:'E'}) " + 
			"MERGE (a)-[:LINK]->(b) " + 
			"MERGE (b)-[:LINK]->(c) " + 
			"MERGE (d)-[:LINK]->(e) ");
		StatementResult createResult = assistant.write(createStatement);
	}

	private void initLouvainData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (nAlice:User {id:'Alice'}) " + 
			"MERGE (nBridget:User {id:'Bridget'}) " + 
			"MERGE (nCharles:User {id:'Charles'}) " + 
			"MERGE (nDoug:User {id:'Doug'}) " + 
			"MERGE (nMark:User {id:'Mark'}) " + 
			"MERGE (nMichael:User {id:'Michael'}) " + 
			"MERGE (nAlice)-[:FRIEND]->(nBridget) " + 
			"MERGE (nAlice)-[:FRIEND]->(nCharles) " + 
			"MERGE (nMark)-[:FRIEND]->(nDoug) " + 
			"MERGE (nBridget)-[:FRIEND]->(nMichael) " + 
			"MERGE (nCharles)-[:FRIEND]->(nMark) " + 
			"MERGE (nAlice)-[:FRIEND]->(nMichael) " + 
			"MERGE (nCharles)-[:FRIEND]->(nDoug) ");
		StatementResult createResult = assistant.write(createStatement);
	}
	
	private void initLabelPropagationData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (nAlice:User {id:'Alice'}) SET nAlice.seed_label=52 " + 
			"MERGE (nBridget:User {id:'Bridget'}) SET nBridget.seed_label=21 " + 
			"MERGE (nCharles:User {id:'Charles'}) SET nCharles.seed_label=43 " + 
			"MERGE (nDoug:User {id:'Doug'}) SET nDoug.seed_label=21 " + 
			"MERGE (nMark:User {id:'Mark'}) SET nMark.seed_label=19 " + 
			"MERGE (nMichael:User {id:'Michael'}) SET nMichael.seed_label=52 " + 
			"MERGE (nAlice)-[:FOLLOW]->(nBridget) " + 
			"MERGE (nAlice)-[:FOLLOW]->(nCharles) " + 
			"MERGE (nMark)-[:FOLLOW]->(nDoug) " + 
			"MERGE (nBridget)-[:FOLLOW]->(nMichael) " + 
			"MERGE (nDoug)-[:FOLLOW]->(nMark) " + 
			"MERGE (nMichael)-[:FOLLOW]->(nAlice) " + 
			"MERGE (nAlice)-[:FOLLOW]->(nMichael) " + 
			"MERGE (nBridget)-[:FOLLOW]->(nAlice) " + 
			"MERGE (nMichael)-[:FOLLOW]->(nBridget) " + 
			"MERGE (nCharles)-[:FOLLOW]->(nDoug) ");
		StatementResult createResult = assistant.write(createStatement);
	}

	private long getCurrentTimeMillis() throws InterruptedException {
		Thread.sleep(1);
		return System.currentTimeMillis();
	}
}
