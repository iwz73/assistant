package idv.hsiehpinghan.springbatchassistant.processor;

import idv.hsiehpinghan.springbatchassistant.pojo.TestPojo;

import org.springframework.batch.item.ItemProcessor;

public class TestProcessor implements ItemProcessor<TestPojo, TestPojo> {

	public TestPojo process(TestPojo arg0) throws Exception {
		System.err.println("TestProcessor process !!!");
		return null;
	}

}