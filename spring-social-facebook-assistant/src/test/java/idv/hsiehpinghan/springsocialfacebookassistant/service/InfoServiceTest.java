package idv.hsiehpinghan.springsocialfacebookassistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.springsocialfacebookassistant.configuration.SpringConfiguration;

@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(classes = { SpringConfiguration.class })
public class InfoServiceTest extends AbstractTestNGSpringContextTests {
	private final String pageId = "162608724089621";
	@Autowired
	private InfoService service;

	@Test
	public void getFacebookType() throws Exception {
		String facebookType = service.getFacebookType(pageId);
		Assert.assertEquals(facebookType, "{\"metadata\":{\"type\":\"page\"},\"id\":\"162608724089621\"}");
	}
}
