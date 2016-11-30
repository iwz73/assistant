package idv.hsiehpinghan.mongodbassistant.assistant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class CollectionAssistantTest extends AbstractTestNGSpringContextTests {
	private final int SIZE = 10;
	private final int SUB_DOCUMENT_SIZE = 3;
	private final String TRADITIONAL_CHINESE = "traditional chinese";
	private final String DATABASE_NAME = "Mongodb_Assistant_Collection_Database";
	private final String COLLECTION_NAME = "Collection";
	private final ObjectId ID = new ObjectId();
	private final double DOUBLE = 1.1; // double
	private final String STRING = "string"; // string
	// Object 3 “object”
	private final List<String> ARRAY = Arrays.asList("繁體中文字串測試_0", "其他不相關字串_1", "array_2"); // array
	private final byte[] BIN_DATA = getBinData(); // binData
	// Undefined 6 “undefined” Deprecated.
	private final ObjectId OBJECT_ID = new ObjectId(); // objectId
	private final boolean BOOL = true; // bool
	private final Date DATE = new Date(); // date
	private final Object NULL = null; // null
	// Regular Expression 11 “regex”
	// DBPointer 12 “dbPointer” Deprecated.
	// JavaScript 13 “javascript”
	// Symbol 14 “symbol” Deprecated.
	// JavaScript (with scope) 15 “javascriptWithScope”
	private final int INT = Integer.MAX_VALUE; // int
	// Timestamp 17 “timestamp”
	private final Long LONG = 18L; // long
	// Min key -1 “minKey”
	// Max key 127 “maxKey”

	@Autowired
	private CollectionAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		assistant.drop(DATABASE_NAME, COLLECTION_NAME);
	}

	@Test
	public void insertOne() throws Exception {
		Document document = generateDocument(ID, INT);
		assistant.insertOne(DATABASE_NAME, COLLECTION_NAME, document);
	}

	@Test(dependsOnMethods = { "insertOne" })
	public void findFirst() {
		Bson bson = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		assertEquals(document, INT);
	}

	@Test(dependsOnMethods = { "findFirst" })
	public void insertMany() {
		List<Document> documents = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			ObjectId objectId = new ObjectId();
			Document document = generateDocument(objectId, i);
			documents.add(document);
		}
		assistant.insertMany(DATABASE_NAME, COLLECTION_NAME, documents);
	}

	@Test(dependsOnMethods = { "insertMany" })
	public void count() {
		long amount = assistant.count(DATABASE_NAME, COLLECTION_NAME);
		Assert.assertEquals(amount, SIZE + 1);
	}

	@Test(dependsOnMethods = { "insertMany" })
	public void find() {
		testGt();
		testLte();
		testAnd();
		testProjection();
		testSort();
	}

	@Test(dependsOnMethods = { "find" })
	public void updateOne() {
		testSetUpdate();
		testUpdatesUpdate();
		testUpdatesCurrentDateAndTimestamp();
	}

	@Test(dependsOnMethods = { "updateOne" })
	public void updateMany() {
		final int I = 100;
		Bson filter = Filters.lte("int", 5);
		Bson update = new Document("$set", new Document("int", I));
		UpdateResult updateResult = assistant.updateMany(DATABASE_NAME, COLLECTION_NAME, filter, update);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 6);
		Bson bson = Filters.eq("int", I);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, bson).size();
		Assert.assertEquals(amount, 6);
	}

	@Test(dependsOnMethods = { "updateMany" })
	public void replaceOne() {
		final Double DOUBLE_VALUE = 3.3;
		final String STRING_VALUE = "stringValue";
		Bson filter = Filters.eq("_id", ID);
		Document replacement = generateReplacement(DOUBLE_VALUE, STRING_VALUE);
		UpdateResult updateResult = assistant.replaceOne(DATABASE_NAME, COLLECTION_NAME, filter, replacement);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 1);
		Bson bson = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		Assert.assertEquals(document.getDouble("double"), DOUBLE_VALUE);
		Assert.assertEquals(document.getString("string"), STRING_VALUE);
	}
	
	@Test(dependsOnMethods = { "replaceOne" })
	public void deleteOne() {
		Bson filter = Filters.eq("_id", ID);
		DeleteResult deleteResult = assistant.deleteOne(DATABASE_NAME, COLLECTION_NAME, filter);
		long deletedCount = deleteResult.getDeletedCount();
		Assert.assertEquals(deletedCount, 1);
		Bson bson = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		Assert.assertNull(document);
	}

	@Test(dependsOnMethods = { "deleteOne" })
	public void deleteMany() {
		Bson filter = Filters.lte("int", 9);
		DeleteResult deleteResult = assistant.deleteMany(DATABASE_NAME, COLLECTION_NAME, filter);
		long deletedCount = deleteResult.getDeletedCount();
		Assert.assertEquals(deletedCount, 4);
		Bson bson = Filters.lte("int", 9);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, bson).size();
		Assert.assertEquals(amount, 0);
	}

	@Test(dependsOnMethods = { "deleteMany" })
	public void createIndex() {
		boolean isAscending = true;
		String[] fieldNames = new String[] { "string", "int" };
		String indexName = assistant.createIndex(DATABASE_NAME, COLLECTION_NAME, isAscending, fieldNames);
		Assert.assertEquals(indexName, "string_1_int_1");
	}

	@Test(dependsOnMethods = { "createIndex" })
	public void createCompoundIndex() {
		Bson bson = Indexes.compoundIndex(Indexes.descending("double"), Indexes.ascending("long"));
		String indexName = assistant.createCompoundIndex(DATABASE_NAME, COLLECTION_NAME, bson);
		Assert.assertEquals(indexName, "double_-1_long_1");
	}

	@Test(dependsOnMethods = { "createCompoundIndex" })
	public void createTextIndex() {
		IndexOptions indexOptions = new IndexOptions();
		// indexOptions.defaultLanguage(TRADITIONAL_CHINESE); because vesion is
		// 3.0.4, not test yet !!!
		String indexName = assistant.createTextIndex(DATABASE_NAME, COLLECTION_NAME, "array", indexOptions);
		Assert.assertEquals(indexName, "array_text");
	}

	@Test(dependsOnMethods = { "createTextIndex" })
	public void createHashedIndex() {
		String indexName = assistant.createHashedIndex(DATABASE_NAME, COLLECTION_NAME, "objectId");
		Assert.assertEquals(indexName, "objectId_hashed");
	}

	@Test(dependsOnMethods = { "createHashedIndex" })
	public void createGeo2dsphereIndex() {
		String indexName = null;
		indexName = assistant.createGeo2dsphereIndex(DATABASE_NAME, COLLECTION_NAME, "pointLocation");
		Assert.assertEquals(indexName, "pointLocation_2dsphere");
		indexName = assistant.createGeo2dsphereIndex(DATABASE_NAME, COLLECTION_NAME, "lineStringLocation");
		Assert.assertEquals(indexName, "lineStringLocation_2dsphere");
		indexName = assistant.createGeo2dsphereIndex(DATABASE_NAME, COLLECTION_NAME, "polygonLocation");
		Assert.assertEquals(indexName, "polygonLocation_2dsphere");
		indexName = assistant.createGeo2dsphereIndex(DATABASE_NAME, COLLECTION_NAME, "multiPointLocation");
		Assert.assertEquals(indexName, "multiPointLocation_2dsphere");
		indexName = assistant.createGeo2dsphereIndex(DATABASE_NAME, COLLECTION_NAME, "multiLineStringLocation");
		Assert.assertEquals(indexName, "multiLineStringLocation_2dsphere");
		indexName = assistant.createGeo2dsphereIndex(DATABASE_NAME, COLLECTION_NAME, "multiPolygonLocation");
		Assert.assertEquals(indexName, "multiPolygonLocation_2dsphere");
		indexName = assistant.createGeo2dsphereIndex(DATABASE_NAME, COLLECTION_NAME, "geometryCollectionLocation");
		Assert.assertEquals(indexName, "geometryCollectionLocation_2dsphere");
	}

	@AfterClass
	public void afterClass() {
		assistant.printAllDocument(DATABASE_NAME, COLLECTION_NAME);
	}

	private void testGt() {
		Bson bson = Filters.gt("int", 3);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, bson).size();
		Assert.assertEquals(amount, 7);
	}

	private void testLte() {
		Bson bson = Filters.lte("int", 5);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, bson).size();
		Assert.assertEquals(amount, 6);
	}

	private void testAnd() {
		List<Bson> filters = new ArrayList<>();
		filters.add(Filters.gt("int", 3));
		filters.add(Filters.lte("int", 5));
		Bson bson = Filters.and(filters);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, bson).size();
		Assert.assertEquals(amount, 2);
	}

	private void testProjection() {
		Bson bson = Filters.eq("_id", ID);
		Bson projection = generateProjection();
		List<Document> documents = assistant.findWithProjection(DATABASE_NAME, COLLECTION_NAME, bson, projection);
		for (Document document : documents) {
			assertProjectionEquals(document);
		}
	}

	private void testSort() {
		List<Bson> filters = new ArrayList<>();
		filters.add(Filters.gt("int", 3));
		filters.add(Filters.lte("int", 5));
		Bson bson = Filters.and(filters);
		Bson sort = generateSort();
		List<Document> documents = assistant.findWithSort(DATABASE_NAME, COLLECTION_NAME, bson, sort);
		Double beforeDouble = Double.MAX_VALUE;
		for (Document document : documents) {
			Double currentDouble = document.getDouble("double");
			Assert.assertTrue(currentDouble.compareTo(beforeDouble) < 0);
		}
	}

	private Bson generateProjection() {
		Document document = new Document();
		document.append("_id", 0);
		document.append("double", 1);
		document.append("string", 1);
		return document;
	}

	private Bson generateSort() {
		String[] sorts = new String[] { "double" };
		return Sorts.descending(sorts);
	}

	private void assertEquals(Document document, int i) {
		Assert.assertEquals(document.getDouble("double"), DOUBLE);
		Assert.assertEquals(document.getString("string"), STRING);
		Assert.assertEquals(document.get("array"), ARRAY);
		assertEquals(((Binary) document.get("binData")).getData(), BIN_DATA);
		Assert.assertEquals(document.getObjectId("objectId"), OBJECT_ID);
		Assert.assertEquals((Boolean) document.getBoolean("bool"), (Boolean) BOOL);
		Assert.assertEquals(document.getDate("date"), DATE);
		Assert.assertEquals(document.get("null"), NULL);
		Assert.assertEquals((Integer) document.getInteger("int"), (Integer) i);
		Assert.assertEquals(document.getLong("long"), LONG);
		assertSubDocumentEquals((Document) document.get("document"));
	}

	private void assertProjectionEquals(Document document) {
		Assert.assertNull(document.get("_id"));
		Assert.assertEquals(document.getDouble("double"), DOUBLE);
		Assert.assertEquals(document.getString("string"), STRING);
		Assert.assertNull(document.get("array"));
		Assert.assertNull(document.get("binData"));
		Assert.assertNull(document.getObjectId("objectId"));
		Assert.assertNull(document.getBoolean("bool"));
		Assert.assertNull(document.getDate("date"));
		Assert.assertNull(document.get("null"));
		Assert.assertNull(document.getInteger("int"));
		Assert.assertNull(document.getLong("long"));
		Assert.assertNull(document.get("document"));
	}

	private void assertSubDocumentEquals(Document document) {
		for (int i = 0; i < SUB_DOCUMENT_SIZE; ++i) {
			Assert.assertEquals(document.get("key_" + i), "value_" + i);
		}
	}

	private Document generateDocument(ObjectId objectId, int i) {
		Document doc = new Document("_id", objectId);
		doc.append("double", DOUBLE);
		doc.append("string", STRING);
		doc.append("array", ARRAY);
		doc.append("binData", BIN_DATA);
		doc.append("objectId", OBJECT_ID);
		doc.append("bool", BOOL);
		doc.append("date", DATE);
		doc.append("null", NULL);
		doc.append("int", i);
		doc.append("long", LONG);
		doc.append("document", generateSubDocument());
		doc.append("pointLocation", generatePointLocation());
		doc.append("lineStringLocation", generateLineStringLocation());
		doc.append("polygonLocation", generatePolygonLocation());
		doc.append("multiPointLocation", generateMultiPointLocation());
		doc.append("multiLineStringLocation", generateMultiLineStringLocation());
		doc.append("multiPolygonLocation", generateMultiPolygonLocation());
		doc.append("geometryCollectionLocation", generateGeometryCollectionLocation());
		return doc;
	}

	private Document generateReplacement(Double doubleValue, String stringValue) {
		Document doc = new Document();
		doc.append("double", doubleValue);
		doc.append("string", stringValue);
		return doc;
	}
	
	private Document generateSubDocument() {
		Document doc = new Document();
		for (int i = 0; i < SUB_DOCUMENT_SIZE; ++i) {
			doc.append("key_" + i, "value_" + i);
		}
		return doc;
	}

	private Document generatePointLocation() {
		Document doc = new Document();
		doc.append("type", "Point");
		doc.append("coordinates", Arrays.asList(121, 23.5));
		return doc;
	}

	private Document generateLineStringLocation() {
		Document doc = new Document();
		doc.append("type", "LineString");
		doc.append("coordinates", Arrays.asList(Arrays.asList(121, 23.5), Arrays.asList(123, 22)));
		return doc;
	}

	private Document generatePolygonLocation() {
		Document doc = new Document();
		doc.append("type", "Polygon");
		doc.append("coordinates", Arrays.asList(Arrays.asList(Arrays.asList(120, 23.5), Arrays.asList(121, 24),
				Arrays.asList(122, 23), Arrays.asList(120, 23.5))));
		return doc;
	}

	private Document generateMultiPointLocation() {
		Document doc = new Document();
		doc.append("type", "MultiPoint");
		doc.append("coordinates", Arrays.asList(Arrays.asList(120, 23.5), Arrays.asList(83, 86), Arrays.asList(142, 45),
				Arrays.asList(156, 76)));
		return doc;
	}

	private Document generateMultiLineStringLocation() {
		Document doc = new Document();
		doc.append("type", "MultiLineString");
		doc.append("coordinates",
				Arrays.asList(Arrays.asList(Arrays.asList(1, 1), Arrays.asList(11, 11)),
						Arrays.asList(Arrays.asList(2, 2), Arrays.asList(22, 22)),
						Arrays.asList(Arrays.asList(3, 3), Arrays.asList(33, 33))));
		return doc;
	}

	private Document generateMultiPolygonLocation() {
		Document doc = new Document();
		doc.append("type", "MultiPolygon");
		doc.append("coordinates",
				Arrays.asList(
						Arrays.asList(Arrays.asList(Arrays.asList(-73.958, 40.8003), Arrays.asList(-73.9498, 40.7968),
								Arrays.asList(-73.9737, 40.7648), Arrays.asList(-73.9814, 40.7681),
								Arrays.asList(-73.958, 40.8003))),
						Arrays.asList(Arrays.asList(Arrays.asList(-73.958, 40.8003), Arrays.asList(-73.9498, 40.7968),
								Arrays.asList(-73.9737, 40.7648), Arrays.asList(-73.958, 40.8003)))));
		return doc;
	}

	private Document generateGeometryCollectionLocation() {
		Document doc = new Document();
		doc.append("type", "GeometryCollection");
		doc.append("geometries", Arrays.asList(generateMultiPointLocation(), generateMultiLineStringLocation()));
		return doc;
	}

	private byte[] getBinData() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}

	private void assertEquals(byte[] bytes_0, byte[] bytes_1) {
		int length_0 = bytes_0.length;
		int length_1 = bytes_1.length;
		if (length_0 != length_1) {
			throw new RuntimeException("length_0(" + length_0 + ") not equals to length_1(" + length_1 + ") !!!");
		}
		for (int i = 0; i < length_0; ++i) {
			Assert.assertEquals(bytes_0[i], bytes_1[i]);
		}
	}

	private void testSetUpdate() {
		final int I = 50;
		Bson filter = Filters.eq("_id", ID);
		Bson update = new Document("$set", new Document("int", I));
		UpdateResult updateResult = assistant.updateOne(DATABASE_NAME, COLLECTION_NAME, filter, update);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 1);
		Bson bson = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		assertEquals(document, I);
	}

	private void testUpdatesUpdate() {
		final int I = 49;
		Bson filter = Filters.eq("_id", ID);
		Bson[] updates = new Bson[] { Updates.set("int", I) };
		Bson update = Updates.combine(updates);
		UpdateResult updateResult = assistant.updateOne(DATABASE_NAME, COLLECTION_NAME, filter, update);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 1);
		Bson bson = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		assertEquals(document, I);
	}

	private void testUpdatesCurrentDateAndTimestamp() {
		Bson filter = Filters.eq("_id", ID);
		Bson[] updates = new Bson[] { Updates.currentDate("currentDate"),
				Updates.currentTimestamp("currentTimestamp") };
		Bson update = Updates.combine(updates);
		UpdateResult updateResult = assistant.updateOne(DATABASE_NAME, COLLECTION_NAME, filter, update);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 1);
		Bson bson = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		Assert.assertNotNull(document.getDate("currentDate"));
		Assert.assertNotNull(document.get("currentTimestamp"));
	}

}
