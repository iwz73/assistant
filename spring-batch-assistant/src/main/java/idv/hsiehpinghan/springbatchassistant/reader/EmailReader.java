package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class EmailReader implements ItemReader<String> {
	private final static String[] EMAILS = new String[] { "thank.hsiehpinghan@gmail.com" };
	private static int index = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (index >= EMAILS.length) {
			return null;
		}
		++index;
		return EMAILS[index];
	}
}
