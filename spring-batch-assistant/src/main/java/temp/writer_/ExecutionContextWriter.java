package temp.writer_;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextWriter implements ItemWriter<Integer> {
	private static int processedAmount = 0;
	private static boolean isRestart = false;
	private ExecutionContext stepExecutionContext;
	private ExecutionContext jobExecutionContext;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws Exception {
		jobExecutionContext = stepExecution.getJobExecution()
				.getExecutionContext();
		stepExecutionContext = stepExecution.getExecutionContext();
		processedAmount = stepExecutionContext.getInt(
				"stepExecutionContextParam", 0);
		if (processedAmount == 0) {
			isRestart = false;
		} else {
			isRestart = true;
		}
	}

	@Override
	public void write(List<? extends Integer> datas) throws Exception {
		processedAmount += datas.size();
		if (isRestart == false && processedAmount >= 5) {
			String msg = "ExecutionContex exception Test !!!";
			System.err.println(msg);
			throw new Exception(msg);
		}
		System.err.println("ExecutionContextWriter write : processedAmount("
				+ processedAmount + ")");
		stepExecutionContext.putInt("stepExecutionContextParam",
				processedAmount);
		jobExecutionContext.put("jobExecutionContextParam",
				"jobExecutionContextParam");
	}

}
