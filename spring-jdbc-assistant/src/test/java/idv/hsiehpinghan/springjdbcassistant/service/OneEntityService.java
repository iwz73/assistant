package idv.hsiehpinghan.springjdbcassistant.service;


import idv.hsiehpinghan.springjdbcassistant.suit.TestngSuitSetting;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OneEntityService {
	private ApplicationContext applicationContext;

	@BeforeClass
	public void beforeClass() throws IOException {
		applicationContext = TestngSuitSetting.getApplicationContext();
	}
	
	@Test
	public void insert() {
		
	}
}
