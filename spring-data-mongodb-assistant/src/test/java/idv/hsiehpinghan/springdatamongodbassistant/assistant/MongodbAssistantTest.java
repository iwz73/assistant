package idv.hsiehpinghan.springdatamongodbassistant.assistant;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.WriteResult;

import idv.hsiehpinghan.springdatamongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springdatamongodbassistant.entity.BasicDocument;
import idv.hsiehpinghan.springdatamongodbassistant.entity.BasicDocument.SubDocument;
import idv.hsiehpinghan.springdatamongodbassistant.enumeration.Enumeration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MongodbAssistantTest extends AbstractTestNGSpringContextTests {
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
	private final String STRING_0 = "string_0";
	private final String STRING_1 = "string_1";
	private final String STRING_2 = "string_2";
	private BasicDocument basicDocument;

	@Autowired
	private MongoTemplateAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		assistant.dropCollection();
	}

	@Test
	public void insert() throws Exception {
		basicDocument = generateBasicDocument();
		assistant.insert(basicDocument);
	}

	@Test(dependsOnMethods = { "insert" })
	public void findOne() throws Exception {
		testCriteriaQuery();
		testBasicQuery();
	}

	private void testCriteriaQuery() {
		Query query = new Query(Criteria.where("_id").is(ID));
		BasicDocument basicDocument = assistant.findOne(query);
		assertEquals(basicDocument, INT);
	}

	private void testBasicQuery() {
		Query query = new BasicQuery("{ intValue : { $eq : " + INT + " } }");
		BasicDocument basicDocument = assistant.findOne(query);
		assertEquals(basicDocument, INT);
	}

	@Test(dependsOnMethods = { "findOne" })
	public void updateFirst() throws Exception {
		final int NEW_INT = 100;
		Query query = new Query(Criteria.where("_id").is(ID));
		Update update = Update.update("intValue", Integer.valueOf(NEW_INT));
		WriteResult writeResult = assistant.updateFirst(query, update);
		int modifiedCount = writeResult.getN();
		Assert.assertEquals(modifiedCount, 1);
		BasicDocument basicDocument = assistant.findOne(query);
		assertEquals(basicDocument, NEW_INT);
	}

	@Test(dependsOnMethods = { "updateFirst" })
	public void remove() throws Exception {
		WriteResult writeResult = assistant.remove(basicDocument);
		int modifiedCount = writeResult.getN();
		Assert.assertEquals(modifiedCount, 1);
		Query query = new Query(Criteria.where("_id").is(ID));
		BasicDocument returnBasicDocument = assistant.findOne(query);
		Assert.assertNull(returnBasicDocument);
	}

	@Test(dependsOnMethods = { "remove" })
	public void insertOrUpdate() throws Exception {
		basicDocument = generateBasicDocument();
		assistant.insertOrUpdate(basicDocument);
		Query query = new Query(Criteria.where("_id").is(ID));
		BasicDocument basicDocument = assistant.findOne(query);
		assertEquals(basicDocument, INT);
	}

	private void assertEquals(BasicDocument basicDocument, int i) {
		Assert.assertEquals(basicDocument.getDoubleValue(), DOUBLE);
		Assert.assertEquals(basicDocument.getStringValue(), STRING);
		Assert.assertEquals(basicDocument.getArrayValue(), ARRAY);
		assertEquals(basicDocument.getBinDataValue(), BIN_DATA);
		Assert.assertEquals(basicDocument.getObjectIdValue(), OBJECT_ID);
		Assert.assertEquals(basicDocument.isBoolValue(), BOOL);
		Assert.assertEquals(basicDocument.getDateValue(), DATE);
		Assert.assertEquals(basicDocument.getNullValue(), NULL);
		Assert.assertEquals(basicDocument.getIntValue(), i);
		Assert.assertEquals(basicDocument.getLongValue(), LONG);
		Assert.assertEquals(basicDocument.getEnumerationValue(), ENUMERATION);
		assertSubDocumentEquals(basicDocument.getSubDocument());
	}

	private void assertSubDocumentEquals(SubDocument subDocument) {
		Assert.assertEquals(subDocument.getString_0(), STRING_0);
		Assert.assertEquals(subDocument.getString_1(), STRING_1);
		Assert.assertEquals(subDocument.getString_2(), STRING_2);
	}

	private BasicDocument generateBasicDocument() {
		return new BasicDocument(ID, DOUBLE, STRING, ARRAY, BIN_DATA, OBJECT_ID, BOOL, DATE, NULL, INT, LONG,
				ENUMERATION, STRING_0, STRING_1, STRING_2);
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