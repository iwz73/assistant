package idv.hsiehpinghan.springbatchassistant.test_;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import temp.configuration_.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class DefaultJobParametersValidatorTest extends
		AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("defaultJobParametersValidatorJob")
	private Job defaultJobParametersValidatorJob;

	@Test(expectedExceptions = { JobParametersInvalidException.class })
	public void withoutRequiredKeys() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		jobLauncher.run(defaultJobParametersValidatorJob,
				builder.toJobParameters());
	}

	@Test
	public void withRequiredKeys() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("requiredKeys_0", new Date());
		jobLauncher.run(defaultJobParametersValidatorJob,
				builder.toJobParameters());
	}
}
