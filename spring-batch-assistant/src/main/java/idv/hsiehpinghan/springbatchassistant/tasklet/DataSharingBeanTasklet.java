package idv.hsiehpinghan.springbatchassistant.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class DataSharingBeanTasklet implements Tasklet {
	private String stringValue;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.err.println("DataSharingBeanTasklet stringValue(" + stringValue + ") !!!");
		System.err.println("ContextTasklet !!!");
		return RepeatStatus.FINISHED;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

}
