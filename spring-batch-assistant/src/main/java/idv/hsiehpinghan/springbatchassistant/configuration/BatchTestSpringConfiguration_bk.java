package idv.hsiehpinghan.springbatchassistant.configuration;

import idv.hsiehpinghan.springbatchassistant.pojo.TestPojo;
import idv.hsiehpinghan.springbatchassistant.processor.TestProcessor;
import idv.hsiehpinghan.springbatchassistant.reader.TestReader;
import idv.hsiehpinghan.springbatchassistant.writer.TestWriter;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchTestSpringConfiguration_bk extends DefaultBatchConfigurer {
	@Bean
	public ItemReader<TestPojo> testReader() {
		TestReader testReader = new TestReader();
		return testReader;
	}

	@Bean
	public ItemProcessor<TestPojo, TestPojo> testProcessor() {
		TestProcessor testProcessor = new TestProcessor();
		return testProcessor;
	}

	@Bean
	public ItemWriter<TestPojo> testWriter() {
		TestWriter testWriter = new TestWriter();
		return testWriter;
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,
			ItemReader<TestPojo> reader, ItemWriter<TestPojo> writer,
			ItemProcessor<TestPojo, TestPojo> processor) {
		return stepBuilderFactory.get("step1").<TestPojo, TestPojo> chunk(10)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer())
				.flow(s1).end().build();
	}

}