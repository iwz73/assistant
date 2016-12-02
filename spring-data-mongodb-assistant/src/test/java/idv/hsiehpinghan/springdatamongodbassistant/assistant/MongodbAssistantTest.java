package idv.hsiehpinghan.springdatamongodbassistant.assistant;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springdatamongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springdatamongodbassistant.entity.BasicDocument;

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

	@Autowired
	private MongoTemplateAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		assistant.dropCollection();
	}

	@Test
	public void insert() throws Exception {
		BasicDocument basicDocument = generateBasicDocument();
		assistant.insert(basicDocument);
	}

	@Test(dependsOnMethods = { "insert" })
	public void findOne() throws Exception {
		Query query = new Query(Criteria.where("_id").is(ID));
		BasicDocument basicDocument = assistant.findOne(query);
		assertEquals(basicDocument);
	}

	private void assertEquals(BasicDocument basicDocument) {
		Assert.assertEquals(basicDocument.getDoubleValue(), DOUBLE);
		Assert.assertEquals(basicDocument.getStringValue(), STRING);
		Assert.assertEquals(basicDocument.getArrayValue(), ARRAY);
		assertEquals(basicDocument.getBinDataValue(), BIN_DATA);
		Assert.assertEquals(basicDocument.getObjectIdValue(), OBJECT_ID);
		Assert.assertEquals(basicDocument.getBoolValue(), BOOL);
		Assert.assertEquals(basicDocument.getDateValue(), DATE);
		Assert.assertEquals(basicDocument.getNullValue(), NULL);
		Assert.assertEquals(basicDocument.getIntValue(), INT);
		Assert.assertEquals(basicDocument.getLongValue(), LONG);
		// assertSubDocumentEquals((Document) document.get("document"));
	}

	private BasicDocument generateBasicDocument() {
		return new BasicDocument(ID, DOUBLE, STRING, ARRAY, BIN_DATA, OBJECT_ID, BOOL, DATE, NULL, INT, LONG);
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