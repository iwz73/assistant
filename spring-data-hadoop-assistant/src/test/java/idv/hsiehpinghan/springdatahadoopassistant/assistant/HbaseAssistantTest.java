package idv.hsiehpinghan.springdatahadoopassistant.assistant;

import java.util.TreeSet;

import idv.hsiehpinghan.springdatahadoopassistant.entity.HbaseEntity;
import idv.hsiehpinghan.springdatahadoopassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HbaseAssistantTest {
	private static final String TABLE_NAME = "webpage";
	private HbaseAssistant assistant;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(HbaseAssistant.class);
	}

	@Test
	public void find() {
		TreeSet<HbaseEntity> entities = assistant.scan(TABLE_NAME);
		
	}
}
