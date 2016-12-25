package idv.hsiehpinghan.mongodbassistant.test;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.DistinctIterable;
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

	@Test
	public void logical() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		or(collection);
	}

	@Test
	public void element() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		exists(collection);
	}

	@Test
	public void evaluation() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		mod(collection);
	}

	@Test
	public void geospatial() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		geoWithin(collection);
	}

	@Test
	public void array() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		all(collection);
	}

	@Test
	public void other() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		projection(collection);
		skip(collection);
		sort(collection);
		distinct(collection);
		count(collection);
	}

	/**
	 * db.mongodb_collection.find({ "string" : { "$eq" : "string_1" }
	 * }).pretty()
	 * 
	 * @param collection
	 */
	private void eq(MongoCollection<Document> collection) {
		Bson filter = new Document("string", new Document("$eq", "string_1"));
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

	/**
	 * db.mongodb_collection.find({ "$or" : [ { "int" : 0 }, { "string" :
	 * "string_1" } ] }).pretty()
	 * 
	 * @param collection
	 */
	private void or(MongoCollection<Document> collection) {
		Bson filter = new Document(
				new Document("$or", Arrays.asList(new Document("int", 0), new Document("string", "string_1"))));
		FindIterable<Document> iterable = collection.find(filter);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				cursor.next();
				++i;
			}
		}
		Assert.assertEquals(i, 2);
	}

	/**
	 * db.mongodb_collection.find({ "null" : { "$exists" : true } }).pretty()
	 * 
	 * @param collection
	 */
	private void exists(MongoCollection<Document> collection) {
		Bson filter = new Document("null", new Document("$exists", true));
		FindIterable<Document> iterable = collection.find(filter);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				cursor.next();
				++i;
			}
		}
		Assert.assertEquals(i, 2);
	}

	/**
	 * db.mongodb_collection.find({ "int" : { "$mod" : [2, 0] } }).pretty()
	 * 
	 * @param collection
	 */
	private void mod(MongoCollection<Document> collection) {
		Bson filter = new Document("int", new Document("$mod", Arrays.asList(2, 0)));
		FindIterable<Document> iterable = collection.find(filter);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Assert.assertEquals(doc.getInteger("int").intValue(), 0);
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

	/**
	 * db.mongodb_collection.find({ "pointLocation" : { $geoWithin: { $geometry:
	 * { "type" : "Polygon" , "coordinates" : [ [ [ 110, 10 ], [ 130, 10 ], [
	 * 130, 30 ], [110, 30], [ 110, 10 ] ] ] } } } }).pretty()
	 * 
	 * @param collection
	 */
	private void geoWithin(MongoCollection<Document> collection) {
		List<List<List<Integer>>> coordinates = Arrays
				.asList(Arrays.asList(Arrays.asList(110, 10), Arrays.asList(110, 10), Arrays.asList(130, 10),
						Arrays.asList(130, 30), Arrays.asList(110, 30), Arrays.asList(110, 10)));
		Bson filter = new Document("pointLocation", new Document("$geoWithin", new Document("$geometry",
				new Document().append("type", "Polygon").append("coordinates", coordinates))));
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
	 * db.mongodb_collection.find({ "array" : { "$all" : [ [ "繁體中文字串測試_1",
	 * "其他不相關字串_1", "text_1" ] ] } }).pretty()
	 * 
	 * @param collection
	 */
	private void all(MongoCollection<Document> collection) {
		Bson filter = new Document("array",
				new Document("$all", Arrays.asList(Arrays.asList("繁體中文字串測試_1", "其他不相關字串_1", "text_1"))));
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
	 * db.mongodb_collection.find( {}, { "_id" : 1, "string" : 1 } ).pretty()
	 * 
	 * @param collection
	 */
	private void projection(MongoCollection<Document> collection) {
		Bson projection = new Document().append("_id", 1).append("string", 1);
		FindIterable<Document> iterable = collection.find().projection(projection);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				System.err.println("projection({ \"_id\" : 1, \"string\" : 1 }) : " + doc);
				++i;
			}
		}
		Assert.assertEquals(i, 2);
	}

	/**
	 * db.mongodb_collection.find({}).skip(1).pretty()
	 * 
	 * @param collection
	 */
	private void skip(MongoCollection<Document> collection) {
		FindIterable<Document> iterable = collection.find().skip(1);
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
	 * db.mongodb_collection.find({}).sort({ "_id" : -1 }).pretty()
	 * 
	 * @param collection
	 */
	private void sort(MongoCollection<Document> collection) {
		Bson sort = new Document("_id", -1);
		FindIterable<Document> iterable = collection.find().sort(sort);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				System.err.println("sort({\"_id\" : -1}) : " + doc);
				++i;
			}
		}
		Assert.assertEquals(i, 2);
	}

	/**
	 * db.mongodb_collection.distinct("string")
	 * 
	 * @param collection
	 */
	private void distinct(MongoCollection<Document> collection) {
		DistinctIterable<String> iterable = collection.distinct("string", String.class);
		int i = 0;
		try (MongoCursor<String> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				String str = cursor.next();
				System.err.println("distinct(string) : " + str);
				++i;
			}
		}
		Assert.assertEquals(i, 2);
	}

	/**
	 * db.mongodb_collection.count()
	 * 
	 * @param collection
	 */
	private void count(MongoCollection<Document> collection) {
		long size = collection.count();
		Assert.assertEquals(size, 2);
	}
}
