package idv.hsiehpinghan.mongodbassistant.assistant;

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
import org.testng.annotations.Test;

import com.mongodb.client.model.Filters;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MongodbAssistantTest extends AbstractTestNGSpringContextTests {
	private final String DATABASE_NAME = "Mongodb_Assistant_Database";
	private final String COLLECTION_NAME = "Mongodb_Assistant_Collection";
	private final ObjectId ID = new ObjectId();
	private final double DOUBLE = 1.1; // double
	private final String STRING = "string"; // string
	// Object 3 “object”
	private final List<String> ARRAY = Arrays.asList("array_0", "array_1", "array_2"); // array
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
	private MongodbAssistant assistant;

	@Test
	public void insertOne() throws Exception {
		Document document = generateDocument();
		assistant.insertOne(DATABASE_NAME, COLLECTION_NAME, document);
	}

	@Test(dependsOnMethods = { "insertOne" })
	public void findFirst() {
		Bson bson = Filters.eq("_id", ID);
		Document document = assistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		assertEquals(document);
	}

	private void assertEquals(Document document) {
		Assert.assertEquals(document.getDouble("double"), DOUBLE);
		Assert.assertEquals(document.getString("string"), STRING);
		Assert.assertEquals(document.get("array"), ARRAY);
		assertEquals(((Binary) document.get("binData")).getData(), BIN_DATA);
		Assert.assertEquals(document.getObjectId("objectId"), OBJECT_ID);
		Assert.assertEquals((Boolean) document.getBoolean("bool"), (Boolean) BOOL);
		Assert.assertEquals(document.getDate("date"), DATE);
		Assert.assertEquals(document.get("null"), NULL);
		Assert.assertEquals((Integer) document.getInteger("int"), (Integer) INT);
		Assert.assertEquals(document.getLong("long"), LONG);
	}

	private Document generateDocument() {
		Document doc = new Document("_id", ID);
		doc.append("double", DOUBLE);
		doc.append("string", STRING);
		doc.append("array", ARRAY);
		doc.append("binData", BIN_DATA);
		doc.append("objectId", OBJECT_ID);
		doc.append("bool", BOOL);
		doc.append("date", DATE);
		doc.append("null", NULL);
		doc.append("int", INT);
		doc.append("long", LONG);
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
}
