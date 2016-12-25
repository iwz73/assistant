package idv.hsiehpinghan.mongodbassistant.test;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.mongodbassistant.utility.MongodbTestUtility;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class QueryModifiersTest extends AbstractTestNGSpringContextTests {
	private MongoDatabase db;
	@Autowired
	private MongoClient mongoClient;

	@BeforeClass
	public void beforeClass() {
		MongodbTestUtility.init(applicationContext);
		db = mongoClient.getDatabase(MongodbTestUtility.DATABASE_NAME);
	}

	@Test
	public void modifiers() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		hint(collection);
	}

	/**
	 * db.mongodb_collection.find({ "$query" : { "string" : "string_1" },
	 * "$hint" : { "_fts" : "text", "_ftsx" : 1 } }).pretty()
	 * 
	 * @param collection
	 */
	private void hint(MongoCollection<Document> collection) {
		Bson filter = new Document().append("$query", new Document("string", "string_1")).append("$hint",
				new Document().append("_fts", "text").append("_ftsx", 1));
		FindIterable<Document> iterable = collection.find(filter);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				cursor.next();
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

}
