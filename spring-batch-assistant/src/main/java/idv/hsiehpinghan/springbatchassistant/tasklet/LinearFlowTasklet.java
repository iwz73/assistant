package idv.hsiehpinghan.springbatchassistant.tasklet;

import java.util.Date;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class LinearFlowTasklet implements Tasklet {
	private Long longValue;
	private String stringValue;
	private Date dateValue;
	private Double doubleValue;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		System.err.println("LinearFlowTasklet execute : longValue(" + longValue + "), stringValue(" + stringValue
				+ "), dateValue(" + dateValue + "), doubleValue(" + doubleValue + ")");
		return RepeatStatus.FINISHED;
	}

	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

}
