package idv.hsiehpinghan.mongodbassistant.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.mongodbassistant.utility.MongodbTestUtility;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class AggregateTest extends AbstractTestNGSpringContextTests {
	private MongoDatabase db;
	@Autowired
	private MongoClient mongoClient;

	@BeforeClass
	public void beforeClass() {
		MongodbTestUtility.init(applicationContext);
		db = mongoClient.getDatabase(MongodbTestUtility.DATABASE_NAME);
	}

	@Test
	public void stage() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
		collStats(collection);
		unwind(collection);
		geoNear(collection);
	}

	@Test
	public void booleanOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void setOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void comparisonOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void arithmeticOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void stringOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void textSearchOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void arrayOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void variableOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void literalOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void dateOperators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void conditionalExpressions() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void dataType() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	@Test
	public void accumulators() {
		MongoCollection<Document> collection = db.getCollection(MongodbTestUtility.COLLECTION_NAME);
	}

	/**
	 * db.mongodb_collection.aggregate([ { "$collStats" : { "latencyStats" : {
	 * "histograms" : true }, "storageStats" : {} } } ]).pretty()
	 * 
	 * @param collection
	 */
	private void collStats(MongoCollection<Document> collection) {
		List<Document> pipeline = new ArrayList<Document>(1);
		Document pipeline_0 = new Document("$collStats", new Document()
				.append("latencyStats", new Document("histograms", true)).append("storageStats", new Document()));
		pipeline.add(pipeline_0);
		AggregateIterable<Document> iterable = collection.aggregate(pipeline);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				System.err.println("collStats : " + doc);
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}

	/**
	 * db.mongodb_collection.aggregate([ { "$unwind" : "$array" } ]).pretty()
	 */
	private void unwind(MongoCollection<Document> collection) {
		List<Document> pipeline = new ArrayList<Document>(1);
		Document pipeline_0 = new Document("$unwind", "$array");
		pipeline.add(pipeline_0);
		AggregateIterable<Document> iterable = collection.aggregate(pipeline);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				System.err.println("unwind : " + doc);
				++i;
			}
		}
		Assert.assertEquals(i, 6);
	}

	/**
	 * db.mongodb_collection.aggregate([ { "$geoNear" : { spherical: true, near:
	 * { type: "Point", coordinates: [ 121, 23.5 ] }, distanceField:
	 * "dist.calculated", includeLocs: "dist.location", maxDistance: 1, num: 10
	 * } } ]).pretty()
	 */
	private void geoNear(MongoCollection<Document> collection) {
		List<Document> pipeline = new ArrayList<Document>(1);
		Document pipeline_0 = new Document("$geoNear", new Document().append("spherical", true)
				.append("near", new Document().append("type", "Point").append("coordinates", Arrays.asList(121, 23.5)))
				.append("distanceField", "dist.calculated").append("includeLocs", "dist.location")
				.append("maxDistance", 1).append("num", 10));
		pipeline.add(pipeline_0);
		AggregateIterable<Document> iterable = collection.aggregate(pipeline);
		int i = 0;
		try (MongoCursor<Document> cursor = iterable.iterator()) {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				System.err.println("geoNear : " + doc);
				++i;
			}
		}
		Assert.assertEquals(i, 1);
	}
}
