package idv.hsiehpinghan.springbatchassistant.test;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

//@ContextConfiguration(classes = { SpringConfiguration.class })
@ContextConfiguration(locations = { "classpath:/batch/LinearFlow.xml" })
public class LinearFlowTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("LinearFlowJob")
	private Job job;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addString("targetDirectory", "222kkjjjj");
		builder.addString("targetFile", "targetFilefffffj");
		JobParameters jobParameters = builder.toJobParameters();

		JobExecution jobExecution = jobLauncher.run(job,jobParameters);
//		Assert.assertEquals(jobExecution.getStepExecutions().size(), 1);
//		jobExecution = jobLauncher.run(job,
//				builder.toJobParameters());
//		Assert.assertEquals(jobExecution.getStepExecutions().size(), 1);
		
		
//		jobLauncher.run(job, new JobParametersBuilder()
//				.addString("inputResource", "classpath:/input/products.zip")
//				.addString("targetDirectory", "./target/importproductsbatch/")
//				.addString("targetFile","products.txt")
//				.addLong("timestamp", System.currentTimeMillis())
//				.toJobParameters()
//			);
	}
}
