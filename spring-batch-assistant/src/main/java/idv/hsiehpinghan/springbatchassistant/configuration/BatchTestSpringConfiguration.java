package idv.hsiehpinghan.springbatchassistant.configuration;

import idv.hsiehpinghan.springbatchassistant.pojo.TestPojo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchTestSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job1(
			@Qualifier("testJobExecutionListener") JobExecutionListener listener,
			@Qualifier("step1") Step step1, @Qualifier("step2") Step step2) {
		JobParametersValidator jobParametersValidator = new DefaultJobParametersValidator();
		return jobBuilderFactory.get("job1").validator(jobParametersValidator)
				.listener(listener).start(step1).next(step2).build();
	}

	@Bean
	protected Step step1(
			@Qualifier("testReader") ItemReader<TestPojo> reader,
			@Qualifier("testProcessor") ItemProcessor<TestPojo, TestPojo> processor,
			@Qualifier("testWriter") ItemWriter<TestPojo> writer) {
		return stepBuilderFactory.get("step1").<TestPojo, TestPojo> chunk(1)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean
	protected Step step2(@Qualifier("testTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("step2").tasklet(tasklet).build();
	}

}