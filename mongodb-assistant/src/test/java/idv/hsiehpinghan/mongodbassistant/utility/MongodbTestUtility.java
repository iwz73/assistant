package idv.hsiehpinghan.mongodbassistant.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.context.ApplicationContext;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongodbTestUtility {
	public static final String DATABASE_NAME = "mongodb_database";
	public static final String COLLECTION_NAME = "mongodb_collection";
	public static final String COLLECTION_JOIN_NAME = "mongodb_collection_join";
	public static final String COLLECTION_TREE_NAME = "mongodb_collection_tree";
	public static final String COLLECTION_GRAPH_NAME = "mongodb_collection_graph";
	public static final String COLLECTION_TRAVELER_NAME = "mongodb_collection_traveler";

	public static void init(ApplicationContext applicationContext) {
		MongoClient mongoClient = applicationContext.getBean(MongoClient.class);
		MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
		initCollection(db);
		initCollectionJoin(db);
		initCollectionTree(db);
		initCollectionGraph(db);
		initCollectionTraveler(db);
	}

	private static void initCollectionTraveler(MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION_TRAVELER_NAME);
		collection.drop();
		Document doc = new Document("_id", 10000);
		doc.append("name", "traveler_0");
		doc.append("hometown", "location_0");
		collection.insertOne(doc);
	}

	private static void initCollectionGraph(MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION_GRAPH_NAME);
		collection.drop();
		List<Document> docs = new ArrayList<>(6);
		Document doc0 = generateCollectionGraphDocument(1000, "location_0", Arrays.asList("location_1", "location_2"));
		docs.add(doc0);
		Document doc1 = generateCollectionGraphDocument(1001, "location_1", Arrays.asList("location_0", "location_3"));
		docs.add(doc1);
		Document doc2 = generateCollectionGraphDocument(1002, "location_2", Arrays.asList("location_0", "location_4"));
		docs.add(doc2);
		Document doc3 = generateCollectionGraphDocument(1003, "location_3", Arrays.asList("location_1", "location_4"));
		docs.add(doc3);
		Document doc4 = generateCollectionGraphDocument(1004, "location_4", Arrays.asList("location_3"));
		docs.add(doc4);
		collection.insertMany(docs);
	}

	private static void initCollectionTree(MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION_TREE_NAME);
		collection.drop();
		List<Document> docs = new ArrayList<>(6);
		Document doc0 = generateCollectionTreeDocument(100, "name_0", null);
		docs.add(doc0);
		Document doc1 = generateCollectionTreeDocument(101, "name_1", "name_0");
		docs.add(doc1);
		Document doc2 = generateCollectionTreeDocument(102, "name_2_0", "name_1");
		docs.add(doc2);
		Document doc3 = generateCollectionTreeDocument(103, "name_2_1", "name_1");
		docs.add(doc3);
		Document doc4 = generateCollectionTreeDocument(104, "name_3_0", "name_2_0");
		docs.add(doc4);
		Document doc5 = generateCollectionTreeDocument(105, "name_3_1", "name_2_1");
		docs.add(doc5);
		collection.insertMany(docs);
	}

	private static void initCollectionJoin(MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION_JOIN_NAME);
		collection.drop();
		List<Document> docs = new ArrayList<>(2);
		Document doc0 = generateCollectionJoinDocument0();
		docs.add(doc0);
		Document doc1 = generateCollectionJoinDocument1();
		docs.add(doc1);
		collection.insertMany(docs);
	}

	private static void initCollection(MongoDatabase db) {
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		collection.drop();
		collection.createIndex(new Document("pointLocation", "2dsphere"));
		collection.createIndex(new Document().append("string", "text").append("array", "text"));
		List<Document> docs = new ArrayList<>(2);
		Document doc0 = generateCollectionDocument0();
		docs.add(doc0);
		Document doc1 = generateCollectionDocument1();
		docs.add(doc1);
		collection.insertMany(docs);
	}

	private static Document generateCollectionGraphDocument(int id, String location, List<String> connectTo) {
		Document doc = new Document("_id", id);
		doc.append("location", location);
		doc.append("connectTo", connectTo);
		return doc;
	}

	private static Document generateCollectionTreeDocument(int id, String name, String reportTo) {
		Document doc = new Document("_id", id);
		doc.append("name", name);
		if (reportTo != null) {
			doc.append("reportTo", reportTo);
		}
		return doc;
	}

	private static Document generateCollectionJoinDocument0() {
		Document doc = new Document("_id", 10);
		doc.append("string_join", "string_0");
		doc.append("array", Arrays.asList("繁體中文字串測試_10", "其他不相關字串_10", "text_10"));
		return doc;
	}

	private static Document generateCollectionJoinDocument1() {
		Document doc = new Document("_id", 11);
		doc.append("string_join", "string_1");
		doc.append("array", Arrays.asList("繁體中文字串測試_11", "其他不相關字串_11", "text_11"));
		return doc;
	}

	private static Document generateCollectionDocument0() {
		Document doc = new Document("_id", 0);
		doc.append("double", 0.0);
		doc.append("string", "string_0");
		doc.append("array", Arrays.asList("繁體中文字串測試_0", "其他不相關字串_0", "text_0"));
		doc.append("binData", new byte[] { 0x1, 0x2, 0x3 });
		doc.append("objectId", new ObjectId("58463b0aa33dac208c802f20"));
		doc.append("bool", true);
		doc.append("date", new Date());
		doc.append("ttl", new Date());
		doc.append("null", null);
		doc.append("int", 0);
		doc.append("long", 0L);
		Map<String, String> map = new LinkedHashMap<>(3);
		map.put("key_0", "value_0_0");
		map.put("key_1", "value_0_1");
		map.put("key_2", "value_0_2");
		doc.append("document", generateSubDocument(new ObjectId("58463b0aa33dac208c802f00"), map));
		doc.append("documents", generateSubDocuments0());
		doc.append("pointLocation", generatePointLocation(Arrays.asList(121, 23.5)));
		return doc;
	}

	private static Document generateCollectionDocument1() {
		Document doc = new Document("_id", 1);
		doc.append("double", 1.1);
		doc.append("string", "string_1");
		doc.append("array", Arrays.asList("繁體中文字串測試_1", "其他不相關字串_1", "text_1"));
		doc.append("binData", new byte[] { 0x1, 0x2, 0x3 });
		doc.append("objectId", new ObjectId("58463b0aa33dac208c802f21"));
		doc.append("bool", false);
		doc.append("date", new Date());
		doc.append("ttl", new Date());
		doc.append("null", null);
		doc.append("int", 1);
		doc.append("long", 1L);
		Map<String, String> map = new LinkedHashMap<>(3);
		map.put("key_0", "value_1_0");
		map.put("key_1", "value_1_1");
		map.put("key_2", "value_1_2");
		doc.append("document", generateSubDocument(new ObjectId("58463b0aa33dac208c802f10"), map));
		doc.append("documents", generateSubDocuments1());
		doc.append("pointLocation", generatePointLocation(Arrays.asList(151, 53.5)));
		return doc;
	}

	private static Document generateSubDocument(ObjectId objectId, Map<String, String> map) {
		Document doc = new Document("_id", objectId);
		for (Map.Entry<String, String> ent : map.entrySet()) {
			doc.append(ent.getKey(), ent.getValue());
		}
		return doc;
	}

	private static List<Document> generateSubDocuments0() {
		List<Document> docs = new ArrayList<Document>(2);
		Map<String, String> map0 = new LinkedHashMap<>(3);
		map0.put("key_0", "value_0_0_0");
		map0.put("key_1", "value_0_0_1");
		map0.put("key_2", "value_0_0_2");
		docs.add(generateSubDocument(new ObjectId("58463b0aa33dac208c802f01"), map0));
		Map<String, String> map1 = new LinkedHashMap<>(3);
		map1.put("key_0", "value_0_1_0");
		map1.put("key_1", "value_0_1_1");
		map1.put("key_2", "value_0_1_2");
		docs.add(generateSubDocument(new ObjectId("58463b0aa33dac208c802f02"), map1));
		return docs;
	}

	private static List<Document> generateSubDocuments1() {
		List<Document> docs = new ArrayList<Document>(2);
		Map<String, String> map0 = new LinkedHashMap<>(3);
		map0.put("key_0", "value_1_0_0");
		map0.put("key_1", "value_1_0_1");
		map0.put("key_2", "value_1_0_2");
		docs.add(generateSubDocument(new ObjectId("58463b0aa33dac208c802f01"), map0));
		Map<String, String> map1 = new LinkedHashMap<>(3);
		map1.put("key_0", "value_1_1_0");
		map1.put("key_1", "value_1_1_1");
		map1.put("key_2", "value_1_1_2");
		docs.add(generateSubDocument(new ObjectId("58463b0aa33dac208c802f02"), map1));
		return docs;
	}

	private static Document generatePointLocation(List<Number> list) {
		Document doc = new Document();
		doc.append("type", "Point");
		doc.append("coordinates", list);
		return doc;
	}
}
