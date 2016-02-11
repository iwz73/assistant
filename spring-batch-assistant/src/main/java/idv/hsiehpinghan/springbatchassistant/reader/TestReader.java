package idv.hsiehpinghan.springbatchassistant.reader;

import idv.hsiehpinghan.springbatchassistant.pojo.TestPojo;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

@Component
public class TestReader implements ItemReader<TestPojo> {
	private static int count = 0;
	
	public TestPojo read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		System.err.println("TestReader read !!!");
		++count;
		if(count > 3) {
			return null;
		}
		return new TestPojo();
	}

}
