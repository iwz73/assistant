package idv.hsiehpinghan.springbatchassistant.test;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springbatchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class FlatFileTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("flatFileItemReaderJob")
	private Job job;

	@Test
	public void test() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addString("encoding", "utf-8");
		jobParametersBuilder.addLong("linesToSkip", 1L);
		jobParametersBuilder.addString("strict", "true");
		jobParametersBuilder.addString("inputResource",
				"/home/thank/git/assistant/spring-batch-assistant/src/test/data/flatFile");
		jobParametersBuilder.addString("comments", "!,#");
		jobParametersBuilder.addString("appendAllowed", "false");
		jobParametersBuilder.addString("lineSeparator", System.lineSeparator());
		jobParametersBuilder.addString("saveState", "true");
		jobParametersBuilder.addString("shouldDeleteIfEmpty", "false");
		jobParametersBuilder.addString("shouldDeleteIfExists", "true");
		jobParametersBuilder.addString("transactional", "true");
		jobParametersBuilder.addString("outputResource", "/tmp/flatFile");
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(), ExitStatus.COMPLETED.getExitCode());
	}
}
