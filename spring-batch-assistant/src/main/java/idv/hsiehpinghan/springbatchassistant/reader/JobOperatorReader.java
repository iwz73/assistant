package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class JobOperatorReader implements ItemReader<String> {
	private final static String[] ITEMS = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private static int index = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (index >= ITEMS.length) {
			return null;
		}
		Thread.sleep(1000);
		String item = ITEMS[index++];
		System.err.println("JobOperatorReader read item(" + item + ")");
		return item;
	}
}
