package idv.hsiehpinghan.mongodbassistant.assistant;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Component
public class MongodbAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private MongoClient mongoClient;

	public void insertOne(String databaseName, String collectionName, Document document) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		collection.insertOne(document);
	}

	public Document findFirst(String databaseName, String collectionName, Bson bson) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.find(bson).first();
	}

	private MongoCollection<Document> getCollection(String databaseName, String collectionName) {
		MongoDatabase db = getDatabase(databaseName);
		return db.getCollection(collectionName);
	}

	private MongoDatabase getDatabase(String databaseName) {
		return mongoClient.getDatabase(databaseName);
	}

}
