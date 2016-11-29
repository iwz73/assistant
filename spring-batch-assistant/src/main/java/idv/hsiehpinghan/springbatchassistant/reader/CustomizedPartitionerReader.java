package idv.hsiehpinghan.springbatchassistant.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomizedPartitionerReader implements ItemReader<String> {
	private int start;
	private int end;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (start >= end) {
			return null;
		}
		String item = String.valueOf(start);
		++start;
		long threadId = Thread.currentThread().getId();
		System.err.println("threadId(" + threadId + ") CustomizedPartitionerReader read item(" + item + ")");
		return item;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
