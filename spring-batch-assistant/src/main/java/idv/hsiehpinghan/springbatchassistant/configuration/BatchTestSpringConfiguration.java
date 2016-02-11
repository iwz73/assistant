package idv.hsiehpinghan.springbatchassistant.configuration;

import idv.hsiehpinghan.springbatchassistant.pojo.TestPojo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
	public Job job1(@Qualifier("step1") Step step1) {
//		return jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer())
//				.flow(step1).end().build();
		
		return jobBuilderFactory.get("job1").start(step1).build();
	}

	@Bean
	protected Step step1(ItemReader<TestPojo> reader, ItemWriter<TestPojo> writer,
			ItemProcessor<TestPojo, TestPojo> processor) {
		return stepBuilderFactory.get("step1").<TestPojo, TestPojo> chunk(1).reader(reader)
				.processor(processor).writer(writer).build();
	}


}