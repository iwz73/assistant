package idv.hsiehpinghan.springbatchassistant.reader;

import idv.hsiehpinghan.springbatchassistant.pojo.TestPojo;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class TestReader implements ItemReader<TestPojo> {

	public TestPojo read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		System.err.println("TestReader read !!!");
		return null;
	}

}
