package idv.hsiehpinghan.hibernateassistant.assistant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.service.StoreProcedureService;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class StoreProcedureTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private StoreProcedureService service;

	@Test
	public void callBasicProcedure() {
		String inputParameter = "my input parameter";
		List<Integer> result = service.callBasicProcedure(inputParameter);
		Assert.assertEquals(result.size(), 1);
		Assert.assertEquals(result.get(0).intValue(), 3);
	}

}
