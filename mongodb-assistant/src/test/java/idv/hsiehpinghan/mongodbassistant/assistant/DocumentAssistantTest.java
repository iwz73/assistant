package idv.hsiehpinghan.mongodbassistant.assistant;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.client.model.Filters;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class DocumentAssistantTest extends AbstractTestNGSpringContextTests {
	private final String DATABASE_NAME = "Mongodb_Assistant_Document_Database";
	private final String COLLECTION_NAME = "Collection";
	private final ObjectId ID = new ObjectId("583d4c919828c43b8e80ab29");
	private final double DOUBLE = 1.1;
	private final String STRING = "string";
	private final List<String> ARRAY = Arrays.asList("array_0", "array_1", "array_2");
	private final ObjectId OBJECT_ID = new ObjectId("583d4c909828c43b8e80ab17");
	private final boolean BOOL = true;
	private final Date DATE = generateDate();
	private final Object NULL = null;
	private final int INT = 100;
	private final Long LONG = 18L;
	private final String DATE_STRING = "2016-11-29T09:38:24.292Z";
	private String json;
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private DocumentAssistant assistant;
	@Autowired
	private CollectionAssistant collectionAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		collectionAssistant.drop(DATABASE_NAME, COLLECTION_NAME);
		json = generateJson();
	}

	@Test
	public void insertOne() throws Exception {
		Document document = assistant.parse(json);
		assertEquals(document);
		collectionAssistant.insertOne(DATABASE_NAME, COLLECTION_NAME, document);
		Bson bson = Filters.eq("_id", ID);
		Document ReturnDocument = collectionAssistant.findFirst(DATABASE_NAME, COLLECTION_NAME, bson);
		assertEquals(ReturnDocument);
	}

	private void assertEquals(Document document) {
		Assert.assertEquals(document.get("_id"), ID);
		Assert.assertEquals(document.getDouble("double"), DOUBLE);
		Assert.assertEquals(document.getString("string"), STRING);
		Assert.assertEquals(document.get("array"), ARRAY);
		Assert.assertEquals(document.getObjectId("objectId"), OBJECT_ID);
		Assert.assertEquals((Boolean) document.getBoolean("bool"), (Boolean) BOOL);
		Assert.assertEquals(document.getDate("date"), DATE);
		Assert.assertEquals(document.get("null"), NULL);
		Assert.assertEquals((Integer) document.getInteger("int"), (Integer) INT);
		Assert.assertEquals(document.getLong("long"), LONG);
		assertSubDocumentEquals((Document) document.get("document"));
	}

	private void assertSubDocumentEquals(Document document) {
		for (int i = 0; i < 3; ++i) {
			Assert.assertEquals(document.get("key_" + i), "value_" + i);
		}
	}

	private String generateJson() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:/data/json");
		File file = resource.getFile();
		try (Scanner scanner = new Scanner(file)) {
			StringBuilder sb = new StringBuilder();
			while (scanner.hasNext()) {
				sb.append(scanner.next());
			}
			return sb.toString();
		}
	}

	private Date generateDate() {
		Instant instant = Instant.parse(DATE_STRING);
		return Date.from(instant);
	}
}
