package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MultithreadedJobReader_0 implements ItemReader<String> {
	private final static String[] ITEMS = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static int index = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (index >= ITEMS.length) {
			return null;
		}
		long threadId = Thread.currentThread().getId();
		String item = ITEMS[index++];
		System.err.println("threadId(" + threadId + ") MultithreadedJobReader_0 read item(" + item + ")");
		return item;
	}
}
