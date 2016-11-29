package idv.hsiehpinghan.mongodbassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.mongodbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.mongodbassistant.entity.Person;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MongodbAssistantTest extends AbstractTestNGSpringContextTests {
	private String databaseName = "testDatabase";
	private String name = "name";
	private int age = 38;
	@Autowired
	private MongodbAssistant assistant;

	@Test
	public void getMongoOperations() throws Exception {
		MongoOperations operations = assistant.getMongoOperations(databaseName);
		testInsertByConstructor(operations);
	}

	private void testInsertByConstructor(MongoOperations operations) {
		operations.dropCollection(Person.class.getSimpleName());
		operations.insert(new Person(name, age));
		Assert.assertNotNull(operations.findOne(new Query(Criteria.where("name").is(name)), Person.class));
	}
}
