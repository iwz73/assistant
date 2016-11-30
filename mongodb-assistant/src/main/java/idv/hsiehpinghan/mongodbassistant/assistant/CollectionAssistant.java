package idv.hsiehpinghan.mongodbassistant.assistant;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@Component
public class CollectionAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private MongoClient mongoClient;

	public String createIndex(String databaseName, String collectionName, boolean isAscending, String... fieldNames) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		Bson bson = null;
		if (isAscending == true) {
			bson = Indexes.ascending(fieldNames);
		} else {
			bson = Indexes.descending(fieldNames);
		}
		return collection.createIndex(bson);
	}

	public String createCompoundIndex(String databaseName, String collectionName, Bson bson) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.createIndex(bson);
	}

	public String createTextIndex(String databaseName, String collectionName, String textFieldName,
			IndexOptions indexOptions) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.createIndex(Indexes.text(textFieldName), indexOptions);
	}

	public String createHashedIndex(String databaseName, String collectionName, String fieldName) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.createIndex(Indexes.hashed(fieldName));
	}

	public String createGeo2dsphereIndex(String databaseName, String collectionName, String fieldName) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.createIndex(Indexes.geo2dsphere(fieldName));
	}

	public void insertOne(String databaseName, String collectionName, Document document) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		collection.insertOne(document);
	}

	public void insertMany(String databaseName, String collectionName, List<? extends Document> documents) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		collection.insertMany(documents);
	}

	public UpdateResult updateOne(String databaseName, String collectionName, Bson filter, Bson update) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.updateOne(filter, update);
	}

	public UpdateResult updateMany(String databaseName, String collectionName, Bson filter, Bson update) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.updateMany(filter, update);
	}

	public DeleteResult deleteOne(String databaseName, String collectionName, Bson filter) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.deleteOne(filter);
	}

	public DeleteResult deleteMany(String databaseName, String collectionName, Bson filter) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.deleteMany(filter);
	}

	public void drop(String databaseName, String collectionName) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		collection.drop();
	}

	public Document findFirst(String databaseName, String collectionName, Bson bson) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.find(bson).first();
	}

	public List<Document> find(String databaseName, String collectionName, Bson bson) {
		List<Document> result = new ArrayList<>();
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		FindIterable<Document> iterable = collection.find(bson);
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				result.add(cursor.next());
			}
		}
		return result;
	}

	public long count(String databaseName, String collectionName) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		return collection.count();
	}

	public void printAllDocument(String databaseName, String collectionName) {
		MongoCollection<Document> collection = getCollection(databaseName, collectionName);
		try (MongoCursor<Document> cursor = collection.find().iterator()) {
			while (cursor.hasNext()) {
				System.err.println(cursor.next().toJson());
			}
		}
	}

	private MongoCollection<Document> getCollection(String databaseName, String collectionName) {
		MongoDatabase db = getDatabase(databaseName);
		return db.getCollection(collectionName);
	}

	private MongoDatabase getDatabase(String databaseName) {
		return mongoClient.getDatabase(databaseName);
	}

}
