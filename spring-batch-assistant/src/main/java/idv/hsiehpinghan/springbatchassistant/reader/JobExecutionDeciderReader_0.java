package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionDeciderReader_0 implements ItemReader<String> {
	private final static String[] STRINGS = new String[] { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9" };
	private static int index = 0;
	private ExecutionContext stepExecutionContext;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) throws Exception {
		stepExecutionContext = stepExecution.getExecutionContext();
	}

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if (index >= STRINGS.length) {
			return null;
		}
		if (index == 5) {
			stepExecutionContext.putString("JobExecutionDecider_test",
					"JobExecutionDecider_test");
		}
		String result = STRINGS[index++];
		System.err.println("JobExecutionDeciderReader_0 read result(" + result
				+ ").");
		return result;
	}

}
