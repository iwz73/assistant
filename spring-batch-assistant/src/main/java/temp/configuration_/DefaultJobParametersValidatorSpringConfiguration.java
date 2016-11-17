package temp.configuration_;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.DefaultJobParametersValidator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultJobParametersValidatorSpringConfiguration {
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job defaultJobParametersValidatorJob(
			@Qualifier("defaultJobParametersValidator") JobParametersValidator jobParametersValidator,
			@Qualifier("defaultJobParametersValidatorStep_0") Step defaultJobParametersValidatorStep_0) {
		return jobBuilderFactory.get("defaultJobParametersValidatorJob")
				.validator(jobParametersValidator)
				.start(defaultJobParametersValidatorStep_0).build();
	}

	@Bean
	public Step defaultJobParametersValidatorStep_0(
			@Qualifier("doNothingTasklet") Tasklet tasklet) {
		return stepBuilderFactory.get("defaultJobParametersValidatorStep_0")
				.tasklet(tasklet).build();
	}

	@Bean
	public DefaultJobParametersValidator defaultJobParametersValidator() {
		String[] requiredKeys = new String[] { "requiredKeys_0" };
		String[] optionalKeys = new String[] { "optionalKeys_0" };
		return new DefaultJobParametersValidator(requiredKeys, optionalKeys);
	}
}