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
	private static final String DATABASE_NAME = "mongodb_database";
	private static final String COLLECTION_NAME = "mongodb_collection";

	public static void init(ApplicationContext applicationContext) {
		MongoClient mongoClient = applicationContext.getBean(MongoClient.class);
		MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		collection.drop();
		List<Document> docs = new ArrayList<>(2);
		Document doc0 = generateDocument0();
		docs.add(doc0);
		Document doc1 = generateDocument1();
		docs.add(doc1);
		collection.insertMany(docs);
	}

	private static Document generateDocument0() {
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

	private static Document generateDocument1() {
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
