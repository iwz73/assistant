package idv.hsiehpinghan.mongodbassistant.assistant;

import idv.hsiehpinghan.mongodbassistant.entity.Person;
import idv.hsiehpinghan.mongodbassistant.suit.TestngSuitSetting;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MongodbAssistantTest {
	private MongodbAssistant assistant;
	private String databaseName = "testDatabase";
	private String name = "name";
	private int age = 38;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(MongodbAssistant.class);
	}

	@Test
	public void getMongoOperations() throws Exception {
		MongoOperations operations = assistant.getMongoOperations(databaseName);
		testInsertByConstructor(operations);
	}

	private void testInsertByConstructor(MongoOperations operations) {
		operations.dropCollection(Person.class.getSimpleName());
		operations.insert(new Person(name, age));
		Assert.assertNotNull(operations.findOne(new Query(Criteria
				.where("name").is(name)), Person.class));
	}
}
