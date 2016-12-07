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
	private InfoService infoService;
	@Autowired
	private TokenService tokenService;

	@Test
	public void getNodeType() throws Exception {
		String appAccessToken = tokenService.getAppAccessToken();
		String nodeType = infoService.getNodeMetadata(pageId, appAccessToken);
		Assert.assertEquals(nodeType, "{\"metadata\":{\"type\":\"page\"},\"id\":\"162608724089621\"}");
	}
}
