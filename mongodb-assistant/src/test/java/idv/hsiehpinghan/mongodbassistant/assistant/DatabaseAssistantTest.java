package idv.hsiehpinghan.mongodbassistant.assistant;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class DatabaseAssistantTest extends AbstractTestNGSpringContextTests {
	private final String DATABASE_NAME = "Mongodb_Assistant_Database_Database";
	private final String COLLECTION_NAME = "Collection";
	private final ObjectId ID = new ObjectId();
	private final double DOUBLE = 1.1;
	private final String STRING = "string";
	@Autowired
	private DatabaseAssistant assistant;
	@Autowired
	private CollectionAssistant collectionAssistant;

	@BeforeClass
	public void beforeClass() {
		assistant.drop(DATABASE_NAME, COLLECTION_NAME);
	}

	@Test // could not test, should be fail !!!
	public void createCollection() {
		CreateCollectionOptions createCollectionOptions = generateCreateCollectionOptions();
		assistant.createCollection(DATABASE_NAME, COLLECTION_NAME, createCollectionOptions);
		Document document = generateDocument(ID, DOUBLE, STRING);
		collectionAssistant.insertOne(DATABASE_NAME, COLLECTION_NAME, document);
	}

	@Test(dependsOnMethods = { "createCollection" })
	public void listCollectionNames() {
		List<String> collectionNames = assistant.listCollectionNames(DATABASE_NAME, COLLECTION_NAME);
		Assert.assertTrue(collectionNames.contains(COLLECTION_NAME));
	}

	private CreateCollectionOptions generateCreateCollectionOptions() {
		CreateCollectionOptions createCollectionOptions = new CreateCollectionOptions();
		createCollectionOptions.capped(true).sizeInBytes(0x100000);
		ValidationOptions validationOptions = generateValidationOptions();
		createCollectionOptions.validationOptions(validationOptions);
		return createCollectionOptions;
	}

	private ValidationOptions generateValidationOptions() {
		List<Bson> filters = new ArrayList<>();
		filters.add(Filters.exists("double"));
		filters.add(Filters.exists("string"));
		filters.add(Filters.exists("not_exists"));
		Bson validator = Filters.and(filters);
		ValidationOptions validationOptions = new ValidationOptions();
		validationOptions.validator(validator);
		return validationOptions;
	}

	private Document generateDocument(ObjectId objectId, double doubleValue, String stringValue) {
		Document doc = new Document("_id", objectId);
		doc.append("double", doubleValue);
		doc.append("string", stringValue);
		return doc;
	}
}
