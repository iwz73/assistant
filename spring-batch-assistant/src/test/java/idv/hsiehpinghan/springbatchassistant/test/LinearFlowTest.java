package idv.hsiehpinghan.springbatchassistant.test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

//@ContextConfiguration(locations = { "classpath:/batch/LinearFlow.xml" })
@ContextConfiguration(classes = { SpringConfiguration.class })
public class LinearFlowTest extends AbstractTestNGSpringContextTests {
	private final Date DATE_VALUE = Date
			.from(LocalDate.of(2016, 11, 18).atStartOfDay(ZoneId.systemDefault()).toInstant());
	private final Double DOUBLE_VALUE = Double.MAX_VALUE;
	private final Long LONG_VALUE = Long.MAX_VALUE;
	private final String STRING_VALUE = "string";
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("linearFlowJob")
	private Job job;

	@Test
	public void test() throws Exception {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addDate("dateValue", DATE_VALUE);
		jobParametersBuilder.addDouble("doubleValue", DOUBLE_VALUE);
		jobParametersBuilder.addLong("longValue", LONG_VALUE);
		jobParametersBuilder.addString("stringValue", STRING_VALUE);
		JobParameters jobParameters = jobParametersBuilder.toJobParameters();
		JobExecution jobExecution = jobLauncher.run(job, jobParameters);
		Assert.assertEquals(jobExecution.getExitStatus().getExitCode(), ExitStatus.COMPLETED.getExitCode());
	}
}
