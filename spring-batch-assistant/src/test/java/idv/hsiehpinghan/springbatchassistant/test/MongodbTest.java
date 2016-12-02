package idv.hsiehpinghan.springbatchassistant.test;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.springbatchassistant.entity.MongodbDocument;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MongodbTest extends AbstractTestNGSpringContextTests {
	private final int SIZE = 10;
	private final String COLLECTION_NAME = "Collection";
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("mongodbJob")
	private Job job;
	@Autowired
	private MongoTemplate mongoTemplate;

	@BeforeClass
	public void beforeClass() throws Exception {
		mongoTemplate.dropCollection(COLLECTION_NAME);
		List<MongodbDocument> mongodbDocuments = generateMongodbDocuments();
		mongoTemplate.insert(mongodbDocuments, COLLECTION_NAME);
	}

	@Test
	public void test() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString("collection", COLLECTION_NAME);
		jobParametersBuilder.addString("query", "{ longValue : { $lt : 5 } }");
		jobParametersBuilder.addLong("pageSize", 3L);
		jobParametersBuilder.addString("targetType", MongodbDocument.class.getName());
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(), ExitStatus.COMPLETED.getExitCode());
	}

	private List<MongodbDocument> generateMongodbDocuments() {
		List<MongodbDocument> result = new ArrayList<>();
		for (int i = 0; i < SIZE; ++i) {
			ObjectId id = new ObjectId();
			Long longValue = Long.valueOf(i);
			String stringValue = "string_" + i;
			MongodbDocument doc = new MongodbDocument(id, longValue, stringValue);
			result.add(doc);
		}
		return result;
	}
}
