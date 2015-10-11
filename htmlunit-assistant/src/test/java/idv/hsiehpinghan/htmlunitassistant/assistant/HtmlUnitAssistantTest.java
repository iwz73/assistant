package idv.hsiehpinghan.htmlunitassistant.assistant;

import idv.hsiehpinghan.htmlunitassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class HtmlUnitAssistantTest {
	private HtmlUnitAssistant assistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		assistant = applicationContext.getBean(HtmlUnitAssistant.class);
	}

	// @Test
	public void post() throws Exception {
		List<NameValuePair> requestParameters = new ArrayList<NameValuePair>();
		requestParameters.add(new NameValuePair("encodeURIComponent", "1"));
		requestParameters.add(new NameValuePair("step", "1"));
		requestParameters.add(new NameValuePair("firstin", "1"));
		requestParameters.add(new NameValuePair("TYPEK", "sii"));
		requestParameters.add(new NameValuePair("code", "01"));
		HtmlPage page = assistant.post(
				"http://mops.twse.com.tw/mops/web/ajax_t51sb01",
				requestParameters);
		Assert.assertNotNull(page);
	}

	@Test
	public void getWebRequest() throws Exception {
		assistant.getWebRequest();
	}
}
