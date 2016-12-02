package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import idv.hsiehpinghan.springbatchassistant.constant.SpringBatchAssistantConstant;

public class RestartFromFailReader implements ItemReader<String>, ItemStream {
	private final static String[] ITEMS = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static int index = 0;
	private static boolean isRestart = false;

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		System.err.println("RestartFromFailReader open index(" + index + ")");
		index = executionContext.getInt(SpringBatchAssistantConstant.INDEX_KEY, 0);
		if (index == 3) {
			isRestart = true;
		}
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (index >= ITEMS.length) {
			return null;
		}
		String item = ITEMS[index];
		if (item.equals("3") && (isRestart == false)) {
			System.err.println("RestartFromFailReader RuntimeException item(" + item + ")");
			throw new RuntimeException();
		}
		++index;
		System.err.println("RestartFromFailReader read item(" + item + ")");
		return item;
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		System.err.println("RestartFromFailReader update index(" + index + ")");
		executionContext.putInt(SpringBatchAssistantConstant.INDEX_KEY, index);
	}

	@Override
	public void close() throws ItemStreamException {
		System.err.println("RestartFromFailReader close index(" + index + ")");
	}

}
