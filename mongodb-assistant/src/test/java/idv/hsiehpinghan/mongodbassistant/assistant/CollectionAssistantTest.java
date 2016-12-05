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

import com.mongodb.bulk.BulkWriteResult;
import com.mongodb.bulk.BulkWriteUpsert;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.DeleteManyModel;
import com.mongodb.client.model.DeleteOneModel;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.ReplaceOneModel;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOneModel;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.WriteModel;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.mongodbassistant.enumeration.Enumeration;

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
	private final Enumeration ENUMERATION = Enumeration.ENUM_2;

	@Autowired
	private CollectionAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		assistant.drop(DATABASE_NAME, COLLECTION_NAME);
	}

	@Test
	public void insertOne() throws Exception {
		Document document = generateDocument(ID, INT, STRING);
		assistant.insertOne(DATABASE_NAME, COLLECTION_NAME, document);
	}

	@Test(dependsOnMethods = { "insertOne" })
	public void findFirst() {
		Bson filter = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, filter);
		assertEquals(document, INT);
	}

	@Test(dependsOnMethods = { "findFirst" })
	public void insertMany() {
		List<Document> documents = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			ObjectId objectId = new ObjectId();
			Document document = generateDocument(objectId, i, STRING);
			documents.add(document);
		}
		assistant.insertMany(DATABASE_NAME, COLLECTION_NAME, documents);
	}

	@Test(dependsOnMethods = { "insertMany" })
	public void count() {
		long amount = assistant.count(DATABASE_NAME, COLLECTION_NAME);
		Assert.assertEquals(amount, SIZE + 1);
	}

	@Test(dependsOnMethods = { "count" })
	public void aggregate() {
		testAccumulatorsSum();
		testAccumulatorsMax();
	}

	private void testAccumulatorsSum() {
		List<? extends Bson> pipeline = generateAccumulatorsSumPipeline();
		List<Document> documents = assistant.aggregate(DATABASE_NAME, COLLECTION_NAME, pipeline);
		for (Document document : documents) {
			Assert.assertEquals(document.get("_id"), "string");
			Assert.assertEquals(document.getInteger("count"), (Integer) 5);
		}
	}

	private void testAccumulatorsMax() {
		List<? extends Bson> pipeline = generateAccumulatorsMaxPipeline();
		List<Document> documents = assistant.aggregate(DATABASE_NAME, COLLECTION_NAME, pipeline);
		for (Document document : documents) {
			Assert.assertEquals(document.get("_id"), "string");
			Assert.assertEquals(document.getInteger("maxInt"), (Integer) 5);
		}
	}

	@Test(dependsOnMethods = { "aggregate" })
	public void find() {
		testGt();
		testLte();
		testAnd();
		testLimit();
		testProjection();
		testSort();
	}

	@Test(dependsOnMethods = { "find" })
	public void updateOne() {
		testSetUpdate();
		testUpdatesUpdate();
		testUpdatesCurrentDateAndTimestamp();
		testUpsertUpdate();
		testUpsertInsert();
	}

	@Test(dependsOnMethods = { "updateOne" })
	public void updateMany() {
		final int I = 100;
		Bson filter = Filters.lte("int", 5);
		Bson update = new Document("$set", new Document("int", I));
		UpdateResult updateResult = assistant.updateMany(DATABASE_NAME, COLLECTION_NAME, filter, update);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 6);
		Bson fltr = Filters.eq("int", I);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, fltr).size();
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
		Bson fltr = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, fltr);
		Assert.assertEquals(document.getDouble("double"), DOUBLE_VALUE);
		Assert.assertEquals(document.getString("string"), STRING_VALUE);
	}

	@Test(dependsOnMethods = { "replaceOne" })
	public void deleteOne() {
		Bson filter = Filters.eq("_id", ID);
		DeleteResult deleteResult = assistant.deleteOne(DATABASE_NAME, COLLECTION_NAME, filter);
		long deletedCount = deleteResult.getDeletedCount();
		Assert.assertEquals(deletedCount, 1);
		Bson fltr = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, fltr);
		Assert.assertNull(document);
	}

	@Test(dependsOnMethods = { "deleteOne" })
	public void deleteMany() {
		final int LTE_INT_TO_DELETE = 8;
		Bson filter = Filters.lte("int", LTE_INT_TO_DELETE);
		DeleteResult deleteResult = assistant.deleteMany(DATABASE_NAME, COLLECTION_NAME, filter);
		long deletedCount = deleteResult.getDeletedCount();
		Assert.assertEquals(deletedCount, 3);
		Bson fltr = Filters.lte("int", LTE_INT_TO_DELETE);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, fltr).size();
		Assert.assertEquals(amount, 0);
	}

	@Test(dependsOnMethods = { "deleteMany" })
	public void bulkWrite() {
		List<? extends WriteModel<? extends Document>> requests = generateBulkWriteRequests();
		BulkWriteResult bulkWriteResult = assistant.bulkWrite(DATABASE_NAME, COLLECTION_NAME, requests);
		int insertedCount = bulkWriteResult.getInsertedCount();
		Assert.assertEquals(insertedCount, 1);
		int matchedCount = bulkWriteResult.getMatchedCount();
		Assert.assertEquals(matchedCount, 5);
		int modifiedCount = bulkWriteResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 5);
		int deletedCount = bulkWriteResult.getDeletedCount();
		Assert.assertEquals(deletedCount, 3);
		List<BulkWriteUpsert> bulkWriteUpserts = bulkWriteResult.getUpserts();
		int bulkWriteUpsertsSize = bulkWriteUpserts.size();
		Assert.assertEquals(bulkWriteUpsertsSize, 1);
	}

	@Test(dependsOnMethods = { "bulkWrite" })
	public void createIndex() {
		boolean isAscending = true;
		String[] fieldNames = new String[] { "string", "int" };
		String indexName = assistant.createIndex(DATABASE_NAME, COLLECTION_NAME, isAscending, fieldNames);
		Assert.assertEquals(indexName, "string_1_int_1");
	}

	@Test(dependsOnMethods = { "createIndex" })
	public void createCompoundIndex() {
		Bson keys = Indexes.compoundIndex(Indexes.descending("double"), Indexes.ascending("long"));
		String indexName = assistant.createCompoundIndex(DATABASE_NAME, COLLECTION_NAME, keys);
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

	private List<WriteModel<Document>> generateBulkWriteRequests() {
		Bson projection = generateBulkWriteProjection();
		List<Document> documents = assistant.findWithProjection(DATABASE_NAME, COLLECTION_NAME, projection);
		List<WriteModel<Document>> requests = new ArrayList<>();
		InsertOneModel<Document> insertOneModel = generateInsertOneModel();
		requests.add(insertOneModel);
		ReplaceOneModel<Document> replaceOneModel = generateReplaceOneModel(documents.get(0));
		requests.add(replaceOneModel);
		UpdateOneModel<Document> updateOneModel = generateUpdateOneModel(documents.get(1));
		requests.add(updateOneModel);
		UpdateManyModel<Document> updateManyModel = generateUpdateManyModel(documents.subList(2, 4));
		requests.add(updateManyModel);
		DeleteOneModel<Document> deleteOneModel = generateDeleteOneModel(documents.get(4));
		requests.add(deleteOneModel);
		DeleteManyModel<Document> deleteManyModel = generateDeleteManyModel(documents.subList(5, 7));
		requests.add(deleteManyModel);
		UpdateOneModel<Document> updateOneModelForUpsertUpdate = generateUpdateOneModelForUpsertUpdate(
				documents.get(7));
		requests.add(updateOneModelForUpsertUpdate);
		UpdateOneModel<Document> updateOneModelForUpsertInsert = generateUpdateOneModelForUpsertInsert();
		requests.add(updateOneModelForUpsertInsert);
		return requests;
	}

	private InsertOneModel<Document> generateInsertOneModel() {
		final String STRING_VALUE = "InsertOneModel";
		ObjectId objectId = new ObjectId();
		Document document = generateDocument(objectId, INT, STRING_VALUE);
		InsertOneModel<Document> insertOneModel = new InsertOneModel<>(document);
		return insertOneModel;
	}

	private ReplaceOneModel<Document> generateReplaceOneModel(Document document) {
		final Double DOUBLE_VALUE = 11.11;
		final String STRING_VALUE = "ReplaceOneModel";
		ObjectId objectId = (ObjectId) document.get("_id");
		Bson filter = Filters.eq("_id", objectId);
		Document replacement = generateReplacement(DOUBLE_VALUE, STRING_VALUE);
		ReplaceOneModel<Document> replaceOneModel = new ReplaceOneModel<>(filter, replacement);
		return replaceOneModel;
	}

	private UpdateOneModel<Document> generateUpdateOneModel(Document document) {
		final Double DOUBLE_VALUE = 22.22;
		final String STRING_VALUE = "UpdateOneModel";
		ObjectId objectId = (ObjectId) document.get("_id");
		Bson filter = Filters.eq("_id", objectId);
		Document update = new Document("$set", generateUpdate(DOUBLE_VALUE, STRING_VALUE));
		UpdateOneModel<Document> updateOneModel = new UpdateOneModel<>(filter, update);
		return updateOneModel;
	}

	private UpdateManyModel<Document> generateUpdateManyModel(List<Document> documents) {
		final int SIZE = documents.size();
		final Double DOUBLE_VALUE = 33.33;
		final String STRING_VALUE = "UpdateManyModel";
		List<Bson> filters = new ArrayList<>(SIZE);
		for (Document document : documents) {
			ObjectId objectId = (ObjectId) document.get("_id");
			Bson filter = Filters.eq("_id", objectId);
			filters.add(filter);
		}
		Bson fltr = Filters.or(filters);
		Document update = new Document("$set", generateUpdate(DOUBLE_VALUE, STRING_VALUE));
		UpdateManyModel<Document> updateManyModel = new UpdateManyModel<>(fltr, update);
		return updateManyModel;
	}

	private DeleteOneModel<Document> generateDeleteOneModel(Document document) {
		ObjectId objectId = (ObjectId) document.get("_id");
		Bson filter = Filters.eq("_id", objectId);
		DeleteOneModel<Document> deleteOneModel = new DeleteOneModel<>(filter);
		System.err.println("add objectId(" + objectId + ") to DeleteOneModel.");
		return deleteOneModel;
	}

	private DeleteManyModel<Document> generateDeleteManyModel(List<Document> documents) {
		final int SIZE = documents.size();
		List<Bson> filters = new ArrayList<>(SIZE);
		for (Document document : documents) {
			ObjectId objectId = (ObjectId) document.get("_id");
			Bson filter = Filters.eq("_id", objectId);
			filters.add(filter);
			System.err.println("add objectId(" + objectId + ") to DeleteManyModel.");
		}
		Bson fltr = Filters.or(filters);
		DeleteManyModel<Document> deleteManyModel = new DeleteManyModel<>(fltr);
		return deleteManyModel;
	}

	private UpdateOneModel<Document> generateUpdateOneModelForUpsertUpdate(Document document) {
		final Double DOUBLE_VALUE = 44.44;
		final String STRING_VALUE = "UpsertUpdate";
		ObjectId objectId = (ObjectId) document.get("_id");
		Bson filter = Filters.eq("_id", objectId);
		Document update = new Document("$set", generateUpdate(DOUBLE_VALUE, STRING_VALUE));
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		UpdateOneModel<Document> updateOneModel = new UpdateOneModel<>(filter, update, updateOptions);
		return updateOneModel;
	}

	private UpdateOneModel<Document> generateUpdateOneModelForUpsertInsert() {
		final Double DOUBLE_VALUE = 55.55;
		final String STRING_VALUE = "UpsertInsert";
		ObjectId objectId = new ObjectId();
		Bson filter = Filters.eq("_id", objectId);
		Document update = new Document("$set", generateUpdate(DOUBLE_VALUE, STRING_VALUE));
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		UpdateOneModel<Document> updateOneModel = new UpdateOneModel<>(filter, update, updateOptions);
		return updateOneModel;
	}

	private void testGt() {
		Bson filter = Filters.gt("int", 3);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, filter).size();
		Assert.assertEquals(amount, 7);
	}

	private void testLte() {
		Bson filter = Filters.lte("int", 5);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, filter).size();
		Assert.assertEquals(amount, 6);
	}

	private void testAnd() {
		List<Bson> filters = new ArrayList<>();
		filters.add(Filters.gt("int", 3));
		filters.add(Filters.lte("int", 5));
		Bson filter = Filters.and(filters);
		long amount = assistant.find(DATABASE_NAME, COLLECTION_NAME, filter).size();
		Assert.assertEquals(amount, 2);
	}

	private void testLimit() {
		final Integer LIMIT = 1;
		List<Bson> filters = new ArrayList<>();
		filters.add(Filters.gt("int", 3));
		filters.add(Filters.lte("int", 5));
		Bson filter = Filters.and(filters);
		long amount = assistant.findWithLimit(DATABASE_NAME, COLLECTION_NAME, filter, LIMIT).size();
		Assert.assertEquals(amount, 1);
	}

	private void testProjection() {
		Bson filter = Filters.eq("_id", ID);
		Bson projection = generateProjection();
		List<Document> documents = assistant.findWithProjection(DATABASE_NAME, COLLECTION_NAME, filter, projection);
		for (Document document : documents) {
			assertProjectionEquals(document);
		}
	}

	private void testSort() {
		List<Bson> filters = new ArrayList<>();
		filters.add(Filters.gt("int", 3));
		filters.add(Filters.lte("int", 5));
		Bson filter = Filters.and(filters);
		Bson sort = generateSort();
		List<Document> documents = assistant.findWithSort(DATABASE_NAME, COLLECTION_NAME, filter, sort);
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

	private Bson generateBulkWriteProjection() {
		Document document = new Document();
		document.append("_id", 1);
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
		Assert.assertEquals(Enumeration.valueOf(document.get("enumeration").toString()), ENUMERATION);
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

	private Document generateDocument(ObjectId objectId, int i, String s) {
		Document doc = new Document("_id", objectId);
		doc.append("double", DOUBLE);
		doc.append("string", s);
		doc.append("array", ARRAY);
		doc.append("binData", BIN_DATA);
		doc.append("objectId", OBJECT_ID);
		doc.append("bool", BOOL);
		doc.append("date", DATE);
		doc.append("null", NULL);
		doc.append("int", i);
		doc.append("long", LONG);
		doc.append("enumeration", ENUMERATION.toString());
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

	private Document generateUpdate(Double doubleValue, String stringValue) {
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

	private List<? extends Bson> generateAccumulatorsSumPipeline() {
		return Arrays.asList(Aggregates.match(Filters.lt("int", 5)),
				Aggregates.group("$string", Accumulators.sum("count", 1)));
	}

	private List<? extends Bson> generateAccumulatorsMaxPipeline() {
		return Arrays.asList(Aggregates.match(Filters.lte("int", 5)),
				Aggregates.group("$string", Accumulators.max("maxInt", "$int")));
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
		Bson fltr = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, fltr);
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
		Bson fltr = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, fltr);
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
		Bson fltr = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, fltr);
		Assert.assertNotNull(document.getDate("currentDate"));
		Assert.assertNotNull(document.get("currentTimestamp"));
	}

	private void testUpsertUpdate() {
		final int I = 88;
		Bson filter = Filters.eq("_id", ID);
		Bson update = new Document("$set", new Document("int", I));
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		UpdateResult updateResult = assistant.updateOne(DATABASE_NAME, COLLECTION_NAME, filter, update, updateOptions);
		long matchedCount = updateResult.getMatchedCount();
		Assert.assertEquals(matchedCount, 1);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 1);
		Bson fltr = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, fltr);
		assertEquals(document, I);
	}

	private void testUpsertInsert() {
		final ObjectId ID = new ObjectId();
		final int I = 99;
		Bson filter = Filters.eq("_id", ID);
		Bson update = new Document("$set", new Document("int", I));
		UpdateOptions updateOptions = new UpdateOptions();
		updateOptions.upsert(true);
		UpdateResult updateResult = assistant.updateOne(DATABASE_NAME, COLLECTION_NAME, filter, update, updateOptions);
		long matchedCount = updateResult.getMatchedCount();
		Assert.assertEquals(matchedCount, 0);
		long modifiedCount = updateResult.getModifiedCount();
		Assert.assertEquals(modifiedCount, 0);
		Bson fltr = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, fltr);
		System.err.println("testUpsertInsert document(" + document + ")");
	}
}
