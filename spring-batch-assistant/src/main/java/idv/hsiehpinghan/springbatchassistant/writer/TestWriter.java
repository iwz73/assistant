package idv.hsiehpinghan.springbatchassistant.writer;

import idv.hsiehpinghan.springbatchassistant.pojo.TestPojo;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class TestWriter implements ItemWriter<TestPojo> {

	public void write(List<? extends TestPojo> arg0) throws Exception {
		System.err.println("TestWriter write !!!");
	}

}
