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
import org.testng.annotations.Test;

@ContextConfiguration(locations = { "classpath:/batch/LinearFlow.xml" })
public class LinearFlowTest extends AbstractTestNGSpringContextTests {
	private final String FILE_RESOURCE = "fileResource";
	private final String TARGET_DIRECTORY = "targetDirectory";
	private final String TARGET_FILE = "targetFile";

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("LinearFlowJob")
	private Job job;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addString("fileResource", FILE_RESOURCE + "tttjj");
		builder.addString("targetDirectory", TARGET_DIRECTORY);
		builder.addString("targetFile", TARGET_FILE);
		JobParameters jobParameters = builder.toJobParameters();

		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		// Assert.assertEquals(jobExecution.getStepExecutions().size(), 1);
		// jobExecution = jobLauncher.run(job,
		// builder.toJobParameters());
		// Assert.assertEquals(jobExecution.getStepExecutions().size(), 1);

		// jobLauncher.run(job, new JobParametersBuilder()
		// .addString("inputResource", "classpath:/input/products.zip")
		// .addString("targetDirectory", "./target/importproductsbatch/")
		// .addString("targetFile","products.txt")
		// .addLong("timestamp", System.currentTimeMillis())
		// .toJobParameters()
		// );
	}
}
