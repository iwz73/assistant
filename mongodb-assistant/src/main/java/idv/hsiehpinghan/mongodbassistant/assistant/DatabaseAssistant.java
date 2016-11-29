package idv.hsiehpinghan.mongodbassistant.assistant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;

@Component
public class DatabaseAssistant {
	// private Logger logger = Logger.getLogger(this.getClass().getName());

	@Autowired
	private MongoClient mongoClient;

	public void createCollection(String databaseName, String collectionName,
			CreateCollectionOptions createCollectionOptions) {
		MongoDatabase database = getDatabase(databaseName);
		database.createCollection(collectionName, createCollectionOptions);
	}

	public List<String> listCollectionNames(String databaseName, String collectionName) {
		List<String> result = new ArrayList<>();
		MongoDatabase database = getDatabase(databaseName);
		try (MongoCursor<String> cursor = database.listCollectionNames().iterator()) {
			while (cursor.hasNext()) {
				result.add(cursor.next());
			}
		}
		return result;
	}

	public void drop(String databaseName, String collectionName) {
		MongoDatabase database = getDatabase(databaseName);
		database.drop();
	}

	private MongoDatabase getDatabase(String databaseName) {
		return mongoClient.getDatabase(databaseName);
	}

}
