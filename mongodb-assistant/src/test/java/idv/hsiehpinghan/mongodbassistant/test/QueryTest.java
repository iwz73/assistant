package idv.hsiehpinghan.mongodbassistant.test;

import java.util.Arrays;

import org.bson.BsonDocument;
import org.bson.BsonString;
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
public class QueryTest extends AbstractTestNGSpringContextTests {
	private MongoDatabase db;
	@Autowired
	private MongoClient mongoClient;

	@BeforeClass
	public void beforeClass() {
		MongodbTestUtility.init(applicationContext);
		db = mongoClient.getDatabase(MongodbTestUtility.DATABASE_NAME);
	}

	@Test
	public void querySelectors() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		eq(collection);
		in(collection);
	}

	/**
	 * db.mongodb_collection.find({ "string" : { "$eq" : "string_1" }
	 * }).pretty()
	 * 
	 * @param collection
	 */
	private void eq(MongoCollection<Document> collection) {
		Bson filter = new Document("string", new BsonDocument("$eq", new BsonString("string_1")));
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

	/**
	 * db.mongodb_collection.find({ "string" : { "$in" : [ "string_1",
	 * "string_2" ] } }).pretty()
	 * 
	 * @param collection
	 */
	private void in(MongoCollection<Document> collection) {
		Bson filter = new Document("string", new Document("$in", Arrays.asList("string_1", "string_2")));
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
