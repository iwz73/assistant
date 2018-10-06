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
//		initLabelPropagationData();
//		labelPropagation();
//		initUnionFindData();
//		unionFind();
//		unionFindWithWeight();
//		initSccData();
//		scc();
//		initTriangleData();
//		triangle();
//		triangleCount();
	}

	private void pathFinding() throws Exception {
//		initSpanningTreeData();
//		spanningTreeMinimum();
//		spanningTreeKmin();
//		initShortestPathData();
//		shortestPath();
//		allShortestPaths();
//		kShortestPaths();
//		initAstarData();
//		astar();
//		initRandomWalkData();
//		randomWalk();
	}
	
	private void similarity() throws Exception {
		initJaccardData();
		jaccard();
//		cosine();
//		euclidean();
	}

	private void jaccard() throws Exception  {
		String callStatement = String.format(
			"MATCH (p:Person)-[:LIKES]->(cuisine) " + 
			"WITH {item:id(p), categories: collect(id(cuisine))} as userData " + 
			"WITH collect(userData) as data " + 
			"CALL algo.similarity.jaccard.stream(data) " + 
			"YIELD item1, item2, count1, count2, intersection, similarity " + 
			"RETURN algo.getNodeById(item1).name AS from, algo.getNodeById(item2).name AS to, " + 
			"intersection, similarity " + 
			"ORDER BY similarity DESC "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			String from = record.get(0).asString();
			String to = record.get(1).asString();
			double similarity = record.get(3).asDouble();
			if(from.equals("Michael") && to.equals("Karin")) {
				Assert.assertEquals(similarity, 0.25);
			}	 
			++i;
		}
		Assert.assertEquals(i, 3);
	}
	
	private void randomWalk() throws Exception  {
		String callStatement = String.format(
			"MATCH (home:Page {name: 'Home'}) " + 
			"CALL algo.randomWalk.stream(id(home), 3, 1) " + 
			"YIELD nodeIds " + 
			"UNWIND nodeIds AS nodeId " + 
			"MATCH (n) WHERE id(n) = nodeId " + 
			"RETURN n.name AS page "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			++i;
		}
		
	}

	private void astar() throws Exception  {
		String callStatement = String.format(
			"MATCH (start:Station{name:'King\\'s Cross St. Pancras'}),(end:Station{name:'Kentish " + 
			"Town'}) " + 
			"CALL algo.shortestPath.astar.stream(start, end, 'time', 'latitude', 'longitude', " + 
			"{defaultValue:1.0}) " + 
			"YIELD nodeId, cost " + 
			"MATCH (n) where id(n) = nodeId " + 
			"RETURN n.name as station,cost "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			int cost = record.get(1).asInt();
			i += cost;
		}
		Assert.assertEquals(i, 14);
	}
	
	private void kShortestPaths() throws Exception  {
		String callStatement = String.format(
			"MATCH (start:Loc{name:'A'}), (end:Loc{name:'F'}) " + 
			"CALL algo.kShortestPaths(start, end, 3, 'cost' ,{}) " + 
			"MATCH p=()-[r:PATH_0|:PATH_1|:PATH_2]->() RETURN p "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			++i;
		}
		Assert.assertEquals(i, 3);
	}

	private void allShortestPaths() throws Exception  {
		String callStatement = String.format(
			"CALL algo.allShortestPaths.stream('cost',{nodeQuery:'Loc',defaultValue:1.0}) " + 
			"YIELD sourceNodeId, targetNodeId, distance " + 
			"WITH sourceNodeId, targetNodeId, distance " + 
			"WHERE algo.isFinite(distance) = true " + 
			"MATCH (source:Loc) WHERE id(source) = sourceNodeId " + 
			"MATCH (target:Loc) WHERE id(target) = targetNodeId " + 
			"WITH source, target, distance WHERE source <> target " + 
			"RETURN source.name AS source, target.name AS target, distance " + 
			"ORDER BY distance DESC "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			++i;
		}
		Assert.assertEquals(i, 14);
	}
	
	private void shortestPath() throws Exception  {
		String callStatement = String.format(
			"MATCH (start:Loc{name:'A'}), (end:Loc{name:'F'}) " + 
			"CALL algo.shortestPath.stream(start, end, 'cost') " + 
			"YIELD nodeId, cost " + 
			"MATCH (other:Loc) WHERE id(other) = nodeId " + 
			"RETURN other.name AS name, cost "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			String name = record.get(0).asString();
			int cost = record.get(1).asInt();
			switch (name) {
			case "A":
				Assert.assertEquals(cost, 0);
				break;
			case "B":	
			case "C":
				Assert.assertEquals(cost, 50);
				break;
			case "D":
				Assert.assertEquals(cost, 90);
				break;
			case "E":
				Assert.assertEquals(cost, 120);
				break;
			case "F":
				Assert.assertEquals(cost, 160);
				break;
			default:
				throw new RuntimeException(String.format("name(%s) not implement !!!", name));
			}
			++i;
		}
		Assert.assertEquals(i, 5);
	}

	private void spanningTreeKmin() throws Exception  {
		String callStatement = String.format(
			"MATCH (n:Place{id:'D'}) " + 
			"CALL algo.spanningTree.kmin('Place', 'LINK', 'cost',id(n), 3, {writeProperty:'kminst'}) " + 
			"YIELD loadMillis, computeMillis, writeMillis, effectiveNodeCount " + 
			"RETURN loadMillis,computeMillis,writeMillis, effectiveNodeCount "
		);
		assistant.write(callStatement);
		String matchStatement = String.format(
			"MATCH(n) RETURN n.id AS id, n.kminst AS kminst "
			);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			String id = record.get(0).asString();
			int kminst = record.get(1).asInt();
			switch (id) {
			case "A":
			case "B":
			case "C":	
				Assert.assertEquals(kminst, 1);
				break;
			case "D":
				Assert.assertEquals(kminst, 3);
				break;
			case "E":
				Assert.assertEquals(kminst, 4);
				break;
			case "F":
				Assert.assertEquals(kminst, 5);
				break;
			case "G":
				Assert.assertEquals(kminst, 6);
				break;
			default:
				throw new RuntimeException(String.format("id(%s) not implement !!!", id));
			}
			++i;
		}
		Assert.assertEquals(i, 7);
	}

	private void spanningTreeMinimum() throws Exception  {
		String callStatement = String.format(
			"MATCH (n:Place {id:'D'}) " + 
			"CALL algo.spanningTree.minimum('Place', 'LINK', 'cost', id(n), {write:true, writeProperty:'MINST'}) " + 
			"YIELD loadMillis, computeMillis, writeMillis, effectiveNodeCount " + 
			"RETURN loadMillis, computeMillis, writeMillis, effectiveNodeCount "
		);
		assistant.write(callStatement);
		String matchStatement = String.format(
			"MATCH path = (n:Place {id:'D'})-[:MINST*]-() " + 
			"WITH relationships(path) AS rels " + 
			"UNWIND rels AS rel " + 
			"WITH DISTINCT rel AS rel " + 
			"RETURN startNode(rel).id AS source, endNode(rel).id AS destination, rel.cost AS cost "
			);
		StatementResult matchResult = assistant.read(matchStatement);
		int i = 0;
		while (matchResult.hasNext()) {
			Record record = matchResult.next();
			int cost = record.get(2).asInt();
			i += cost;
		}
		Assert.assertEquals(i, 12);
	}

	private void triangleCount() throws Exception  {
		String callStatement = String.format(
			"CALL algo.triangleCount.stream('Person', 'KNOWS', {concurrency:4}) " + 
			"YIELD nodeId, triangles, coefficient " + 
			"MATCH (p:Person) WHERE id(p) = nodeId " + 
			"RETURN p.id AS name, triangles, coefficient " + 
			"ORDER BY coefficient DESC "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			String name = record.get(0).asString();
			int triangles = record.get(1).asInt();
			switch (name) {
			case "Karin":
				Assert.assertEquals(triangles, 1);
				break;
			case "Mark":
				Assert.assertEquals(triangles, 1);
				break;
			case "Chris":
				Assert.assertEquals(triangles, 2);
				break;
			case "Will":
				Assert.assertEquals(triangles, 2);
				break;
			case "Michael":
				Assert.assertEquals(triangles, 3);
				break;
			case "Alice":
				Assert.assertEquals(triangles, 0);
				break;
			default:
				throw new RuntimeException(String.format("name(%s) not implement !!!", name));
			}
			++i;
		}
		Assert.assertEquals(i, 6);
	}

	private void triangle() throws Exception  {
		String callStatement = String.format(
			"CALL algo.triangle.stream('Person','KNOWS') " + 
			"YIELD nodeA,nodeB,nodeC " + 
			"MATCH (a:Person) WHERE id(a) = nodeA " + 
			"MATCH (b:Person) WHERE id(b) = nodeB " + 
			"MATCH (c:Person) WHERE id(c) = nodeC " + 
			"RETURN a.id AS nodeA, b.id AS nodeB, c.id AS nodeC "
		);
		StatementResult callResult = assistant.read(callStatement);
		int i = 0;
		while (callResult.hasNext()) {
			Record record = callResult.next();
			++i;
		}
		Assert.assertEquals(i, 3);
	}
	
	private void scc() throws Exception  {
		String callStatement = String.format(
			"CALL algo.scc.stream('User','FOLLOW', {}) " + 
			"YIELD nodeId, partition "
		);
		StatementResult callResult = assistant.read(callStatement);
		Set<Integer> labels = new HashSet<>();
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Integer label = record.get(1).asInt();
			labels.add(label);
		}
		Assert.assertEquals(labels.size(), 3);
	}

	private void unionFindWithWeight() throws Exception  {
		String callStatement = String.format(
			"CALL algo.unionFind.stream('User', 'FRIEND', {weightProperty:'weight', defaultValue:0.0, threshold:1.0, concurrency: 1}) " + 
			"YIELD nodeId,setId " + 
			"MATCH (u:User) WHERE id(u) = nodeId " + 
			"RETURN u.id AS user, setId "
		);
		StatementResult callResult = assistant.read(callStatement);
		Set<Integer> labels = new HashSet<>();
		while (callResult.hasNext()) {
			Record record = callResult.next();
			Integer label = record.get(1).asInt();
			labels.add(label);
		}
		Assert.assertEquals(labels.size(), 3);
	}
	
	private void unionFind() throws Exception  {
		String callStatement = String.format(
			"CALL algo.unionFind.stream('User', 'FRIEND', {}) " + 
			"YIELD nodeId,setId " + 
			"MATCH (u:User) WHERE id(u) = nodeId " + 
			"RETURN u.id AS user, setId "
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

	private void initUnionFindData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (nAlice:User {id:'Alice'}) " + 
			"MERGE (nBridget:User {id:'Bridget'}) " + 
			"MERGE (nCharles:User {id:'Charles'}) " + 
			"MERGE (nDoug:User {id:'Doug'}) " + 
			"MERGE (nMark:User {id:'Mark'}) " + 
			"MERGE (nMichael:User {id:'Michael'}) " + 
			"MERGE (nAlice)-[:FRIEND {weight:0.5}]->(nBridget) " + 
			"MERGE (nAlice)-[:FRIEND {weight:4}]->(nCharles) " + 
			"MERGE (nMark)-[:FRIEND {weight:1}]->(nDoug) " + 
			"MERGE (nMark)-[:FRIEND {weight:2}]->(nMichael) ");
		StatementResult createResult = assistant.write(createStatement);
	}

	private void initSccData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (nAlice:User {id:'Alice'}) " + 
			"MERGE (nBridget:User {id:'Bridget'}) " + 
			"MERGE (nCharles:User {id:'Charles'}) " + 
			"MERGE (nDoug:User {id:'Doug'}) " + 
			"MERGE (nMark:User {id:'Mark'}) " + 
			"MERGE (nMichael:User {id:'Michael'}) " + 
			"MERGE (nAlice)-[:FOLLOW]->(nBridget) " + 
			"MERGE (nAlice)-[:FOLLOW]->(nCharles) " + 
			"MERGE (nMark)-[:FOLLOW]->(nDoug) " + 
			"MERGE (nMark)-[:FOLLOW]->(nMichael) " + 
			"MERGE (nBridget)-[:FOLLOW]->(nMichael) " + 
			"MERGE (nDoug)-[:FOLLOW]->(nMark) " + 
			"MERGE (nMichael)-[:FOLLOW]->(nAlice) " + 
			"MERGE (nAlice)-[:FOLLOW]->(nMichael) " + 
			"MERGE (nBridget)-[:FOLLOW]->(nAlice) " + 
			"MERGE (nMichael)-[:FOLLOW]->(nBridget) ");
		StatementResult createResult = assistant.write(createStatement);
	}

	private void initTriangleData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (alice:Person{id:'Alice'}) " + 
			"MERGE (michael:Person{id:'Michael'}) " + 
			"MERGE (karin:Person{id:'Karin'}) " + 
			"MERGE (chris:Person{id:'Chris'}) " + 
			"MERGE (will:Person{id:'Will'}) " + 
			"MERGE (mark:Person{id:'Mark'}) " + 
			"MERGE (michael)-[:KNOWS]->(karin) " + 
			"MERGE (michael)-[:KNOWS]->(chris) " + 
			"MERGE (will)-[:KNOWS]->(michael) " + 
			"MERGE (mark)-[:KNOWS]->(michael) " + 
			"MERGE (mark)-[:KNOWS]->(will) " + 
			"MERGE (alice)-[:KNOWS]->(michael) " + 
			"MERGE (will)-[:KNOWS]->(chris) " + 
			"MERGE (chris)-[:KNOWS]->(karin) ");
		StatementResult createResult = assistant.write(createStatement);
	}
	
	private void initSpanningTreeData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (a:Place {id:'A'}) " + 
			"MERGE (b:Place {id:'B'}) " + 
			"MERGE (c:Place {id:'C'}) " + 
			"MERGE (d:Place {id:'D'}) " + 
			"MERGE (e:Place {id:'E'}) " + 
			"MERGE (f:Place {id:'F'}) " + 
			"MERGE (g:Place {id:'G'}) " + 
			"MERGE (d)-[:LINK {cost:4}]->(b) " + 
			"MERGE (d)-[:LINK {cost:6}]->(e) " + 
			"MERGE (b)-[:LINK {cost:1}]->(a) " + 
			"MERGE (b)-[:LINK {cost:3}]->(c) " + 
			"MERGE (a)-[:LINK {cost:2}]->(c) " + 
			"MERGE (c)-[:LINK {cost:5}]->(e) " + 
			"MERGE (f)-[:LINK {cost:1}]->(g) ");
		StatementResult createResult = assistant.write(createStatement);
	}
	
	private void initShortestPathData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (a:Loc {name:'A'}) " + 
			"MERGE (b:Loc {name:'B'}) " + 
			"MERGE (c:Loc {name:'C'}) " + 
			"MERGE (d:Loc {name:'D'}) " + 
			"MERGE (e:Loc {name:'E'}) " + 
			"MERGE (f:Loc {name:'F'}) " + 
			"MERGE (a)-[:ROAD {cost:50}]->(b) " + 
			"MERGE (a)-[:ROAD {cost:50}]->(c) " + 
			"MERGE (a)-[:ROAD {cost:100}]->(d) " + 
			"MERGE (b)-[:ROAD {cost:40}]->(d) " + 
			"MERGE (c)-[:ROAD {cost:40}]->(d) " + 
			"MERGE (c)-[:ROAD {cost:80}]->(e) " + 
			"MERGE (d)-[:ROAD {cost:30}]->(e) " + 
			"MERGE (d)-[:ROAD {cost:80}]->(f) " + 
			"MERGE (e)-[:ROAD {cost:40}]->(f) ");
		StatementResult createResult = assistant.write(createStatement);
	}
	
	private void initAstarData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (a:Station{name:'King\\'s Cross St. Pancras'}) " + 
			"SET a.latitude = 51.5308,a.longitude = -0.1238 " + 
			"MERGE (b:Station{name:'Euston'}) " + 
			"SET b.latitude = 51.5282, b.longitude = -0.1337 " + 
			"MERGE (c:Station{name:'Camden Town'}) " + 
			"SET c.latitude = 51.5392, c.longitude = -0.1426 " + 
			"MERGE (d:Station{name:'Mornington Crescent'}) " + 
			"SET d.latitude = 51.5342, d.longitude = -0.1387 " + 
			"MERGE (e:Station{name:'Kentish Town'}) " + 
			"SET e.latitude = 51.5507, e.longitude = -0.1402 " + 
			"MERGE (a)-[:CONNECTION{time:2}]->(b) " + 
			"MERGE (b)-[:CONNECTION{time:3}]->(c) " + 
			"MERGE (b)-[:CONNECTION{time:2}]->(d) " + 
			"MERGE (d)-[:CONNECTION{time:2}]->(c) " + 
			"MERGE (c)-[:CONNECTION{time:2}]->(e) ");
		StatementResult createResult = assistant.write(createStatement);
	}

	private void initRandomWalkData() throws Exception  {
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

	private void initJaccardData() throws Exception  {
		String detachDeleteStatement = String.format("MATCH (n) DETACH DELETE n");
		assistant.write(detachDeleteStatement);
		String createStatement = String.format(
			"MERGE (french:Cuisine {name:'French'}) " + 
			"MERGE (italian:Cuisine {name:'Italian'}) " + 
			"MERGE (indian:Cuisine {name:'Indian'}) " + 
			"MERGE (lebanese:Cuisine {name:'Lebanese'}) " + 
			"MERGE (portuguese:Cuisine {name:'Portuguese'}) " + 
			"MERGE (zhen:Person {name: 'Zhen'}) " + 
			"MERGE (praveena:Person {name: 'Praveena'}) " + 
			"MERGE (michael:Person {name: 'Michael'}) " + 
			"MERGE (arya:Person {name: 'Arya'}) " + 
			"MERGE (karin:Person {name: 'Karin'}) " + 
			"MERGE (praveena)-[:LIKES]->(indian) " + 
			"MERGE (praveena)-[:LIKES]->(portuguese) " + 
			"MERGE (zhen)-[:LIKES]->(french) " + 
			"MERGE (zhen)-[:LIKES]->(indian) " + 
			"MERGE (michael)-[:LIKES]->(french) " + 
			"MERGE (michael)-[:LIKES]->(italian) " + 
			"MERGE (michael)-[:LIKES]->(indian) " + 
			"MERGE (arya)-[:LIKES]->(lebanese) " + 
			"MERGE (arya)-[:LIKES]->(italian) " + 
			"MERGE (arya)-[:LIKES]->(portuguese) " + 
			"MERGE (karin)-[:LIKES]->(lebanese) " + 
			"MERGE (karin)-[:LIKES]->(italian) ");
		StatementResult createResult = assistant.write(createStatement);
	}
	
	private long getCurrentTimeMillis() throws InterruptedException {
		Thread.sleep(1);
		return System.currentTimeMillis();
	}
}
