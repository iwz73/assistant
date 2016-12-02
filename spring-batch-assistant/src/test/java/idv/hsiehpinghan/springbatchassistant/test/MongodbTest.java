package idv.hsiehpinghan.springbatchassistant.test;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mongodb.MongoClient;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MongodbTest extends AbstractTestNGSpringContextTests {
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
//		Collection<? extends Object> batchToSave
//		mongoTemplate.insert(batchToSave, COLLECTION_NAME);
	}
	
	@Test
	public void test() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString("collection", COLLECTION_NAME);
		jobParametersBuilder.addString("query", "");

		
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(), ExitStatus.COMPLETED.getExitCode());
	}
}
