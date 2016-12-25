package idv.hsiehpinghan.mongodbassistant.test;

import java.util.Arrays;

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
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.mongodbassistant.utility.MongodbTestUtility;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class UpdateTest extends AbstractTestNGSpringContextTests {
	private MongoDatabase db;
	@Autowired
	private MongoClient mongoClient;

	@BeforeClass
	public void beforeClass() {
		MongodbTestUtility.init(applicationContext);
		db = mongoClient.getDatabase(MongodbTestUtility.DATABASE_NAME);
	}

	@Test
	public void fields() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		inc(collection);
		currentDate(collection);
	}

	@Test
	public void array() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		updateSpecificArrayElement(collection);
		pushEach(collection);
	}

	@Test
	public void bitwise() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void isolation() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void updateOption() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		updateAllDocument(collection);
	}

	/**
	 * db.mongodb_collection.update( { "null" : null }, { $addToSet : { "array"
	 * : "text_0" } }, { upsert: true, multi: true } )
	 * 
	 * @param collection
	 */
	private void updateAllDocument(MongoCollection<Document> collection) {
		Bson filter = new Document("null", null);
		Bson update = new Document("$addToSet", new Document("array", "text_0"));
		UpdateOptions updateOptions = new UpdateOptions().upsert(true);
		UpdateResult updateResult = collection.updateMany(filter, update, updateOptions);
		Assert.assertEquals(updateResult.getModifiedCount(), 1);
		Bson projection = new Document("array", "1");
		FindIterable<Document> iterable = collection.find(filter).projection(projection);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				System.err.println("updateAllDocument : " + doc);
				++i;
			}
		}
		Assert.assertEquals(i, 2);
	}

	/**
	 * db.mongodb_collection.update({ "int" : 1 },{ "$push" : { "array" : {
	 * "$each" : [ "push_0", "push_1", "push_2" ] } } })
	 * 
	 * @param collection
	 */
	private void pushEach(MongoCollection<Document> collection) {
		Bson filter = new Document("int", 1);
		Bson update = new Document("$push",
				new Document("array", new Document("$each", Arrays.asList("push_0", "push_1", "push_2"))));
		UpdateResult updateResult = collection.updateMany(filter, update);
		Assert.assertEquals(updateResult.getModifiedCount(), 1);
		Bson projection = new Document("array", "1");
		FindIterable<Document> iterable = collection.find(filter).projection(projection);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				System.err.println("pushEach : " + doc);
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

	/**
	 * db.mongodb_collection.update({ "array" : "text_1" },{ "$set" : {
	 * "array.$" : "setArray$" } })
	 * 
	 * @param collection
	 */
	private void updateSpecificArrayElement(MongoCollection<Document> collection) {
		Bson filter = new Document("array", "text_1");
		Bson update = new Document("$set", new Document("array.$", "setArray$"));
		UpdateResult updateResult = collection.updateMany(filter, update);
		Assert.assertEquals(updateResult.getModifiedCount(), 1);
	}

	/**
	 * db.mongodb_collection.update({ "int" : 1 },{ "$inc" : { "double" : -2 }
	 * })
	 * 
	 * @param collection
	 */
	private void inc(MongoCollection<Document> collection) {
		Bson filter = new Document("int", 1);
		Bson update = new Document("$inc", new Document("double", -2));
		UpdateResult updateResult = collection.updateMany(filter, update);
		Assert.assertEquals(updateResult.getModifiedCount(), 1);
	}

	/**
	 * db.mongodb_collection.update({ "int" : 1 },{ "$currentDate" : { "date": {
	 * "$type" : "timestamp" } } })
	 * 
	 * @param collection
	 */
	private void currentDate(MongoCollection<Document> collection) {
		Bson filter = new Document("int", 1);
		Bson update = new Document("$currentDate", new Document("date", new Document("$type", "timestamp")));
		UpdateResult updateResult = collection.updateMany(filter, update);
		Assert.assertEquals(updateResult.getModifiedCount(), 1);
	}
}
