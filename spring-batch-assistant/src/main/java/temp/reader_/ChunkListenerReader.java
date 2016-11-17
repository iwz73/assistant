package temp.reader_;

import java.io.IOException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class ChunkListenerReader implements ItemReader<String> {
	private final static String[] STRINGS = new String[] { "0", "1", "2", "3",
			"4", "5", "6", "7", "8", "9" };
	private static int index = 0;

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		if (index >= STRINGS.length) {
			return null;
		}
		if (index == 5) {
			String msg = "ChunkListenerReader exception test !!!";
			System.err.println(msg);
			throw new IOException(msg);
		}
		return STRINGS[index++];
	}
}
