package temp.test_;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import temp.configuration_.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ItemWriteListenerTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	@Qualifier("itemWriteListenerJob")
	private Job itemWriteListenerJob;

	@Test
	public void test() throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		jobLauncher.run(itemWriteListenerJob, builder.toJobParameters());
	}

}
