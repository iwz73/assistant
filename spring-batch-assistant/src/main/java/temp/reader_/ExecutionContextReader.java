package temp.reader_;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextReader implements ItemReader<String> {
	private final static String[] STRINGS = new String[] { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9" };
	private static int index;
	private ExecutionContext stepExecutionContext;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws Exception {
		stepExecutionContext = stepExecution.getExecutionContext();
		index = stepExecutionContext.getInt("stepExecutionContextParam", 0);
	}

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if (index >= STRINGS.length) {
			return null;
		}
		System.err
				.println("ExecutionContextReader read : index(" + index + ")");
		return STRINGS[index++];
	}

}
