package idv.hsiehpinghan.springbatchassistant.writer;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextWriter implements ItemWriter<Integer> {
	private ExecutionContext executionContext;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws Exception {
		executionContext = stepExecution.getExecutionContext();
	}

	@Override
	public void write(List<? extends Integer> datas) throws Exception {
		int index = executionContext.getInt("index");
		System.err.println("ExecutionContextWriter write : index(" + index
				+ ")");
	}

}
