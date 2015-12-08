package idv.hsiehpinghan.socketassistant.assistant;

import idv.hsiehpinghan.socketassistant.enumeration.Protocol;
import idv.hsiehpinghan.socketassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SocketAssistantTest {
	private SocketAssistant socketAssistant;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		socketAssistant = applicationContext.getBean(SocketAssistant.class);
	}

	@Test
	public void getContent() throws Exception {
		getContentTest();
		getContentByUrlTest();
	}

	private void getContentTest() throws Exception {
		Map<String, String> headerMap = generateHeaderMap();
		String content = socketAssistant.getContent(Protocol.HTTP,
				"mops.twse.com.tw", 80, "/mops/web/t51sb01", headerMap);
		Assert.assertEquals(content.substring(0, 16), "HTTP/1.1 200 O.K");
	}

	private void getContentByUrlTest() throws Exception {
		Map<String, String> headerMap = generateHeaderMap();
		String content = socketAssistant.getContent(
				"http://mops.twse.com.tw/mops/web/t51sb01", headerMap);
		Assert.assertEquals(content.substring(0, 16), "HTTP/1.1 200 O.K");
	}

	private Map<String, String> generateHeaderMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Accept-Encoding", "x-gzip, gzip");
		map.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		map.put("User-Agent", "My Nutch Spider/Nutch-2.3");
		map.put("If-Modified-Since", "Thu, 01 Jan 1970 00:00:00 GMT");
		return map;
	}
}
