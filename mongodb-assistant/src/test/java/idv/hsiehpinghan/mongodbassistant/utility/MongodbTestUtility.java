package idv.hsiehpinghan.mongodbassistant.utility;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.springframework.context.ApplicationContext;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;

import idv.hsiehpinghan.mongodbassistant.assistant.CollectionAssistant;

public class MongodbTestUtility {
	private static final String DATABASE_NAME = "mongodb_database";
	private static final String COLLECTION_NAME = "mongodb_collection";
	
	public static void init(ApplicationContext applicationContext) {
		MongoClient mongoClient = applicationContext.getBean(MongoClient.class);
		MongoDatabase db = mongoClient.getDatabase(DATABASE_NAME);
		MongoCollection<Document> collection = db.getCollection(COLLECTION_NAME);
		collection.drop();
		collection.createIndex(new BsonDocument("pointLocation", new BsonString("2dsphere")));
		
//		db.mongodb_collection.createIndex( { "pointLocation" : "2dsphere" } )
		
//		Indexes.text(fieldName)
//
//		
//		db.mongodb_collection.createIndex({
//			"string" : "text",
//			"array" : "text"
//		})
		
//		Document document = generateDocument0();
		
	}

//	private Document generateDocument0() {
//		Document doc = new Document("_id", objectId);
//		doc.append("double", DOUBLE);
//		doc.append("string", s);
//		doc.append("array", ARRAY);
//		doc.append("binData", BIN_DATA);
//		doc.append("objectId", OBJECT_ID);
//		doc.append("bool", BOOL);
//		doc.append("date", DATE);
//		doc.append("null", NULL);
//		doc.append("int", i);
//		doc.append("long", LONG);
//		doc.append("enumeration", ENUMERATION.toString());
//		doc.append("document", generateSubDocument());
//		doc.append("pointLocation", generatePointLocation());
//		doc.append("lineStringLocation", generateLineStringLocation());
//		doc.append("polygonLocation", generatePolygonLocation());
//		doc.append("multiPointLocation", generateMultiPointLocation());
//		doc.append("multiLineStringLocation", generateMultiLineStringLocation());
//		doc.append("multiPolygonLocation", generateMultiPolygonLocation());
//		doc.append("geometryCollectionLocation", generateGeometryCollectionLocation());
//		return doc;
//	}
}
