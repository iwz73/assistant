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
		getContentByPostTest();
	}

	private void getContentTest() throws Exception {
		Map<String, String> headerMap = generateGetHeaderMap();
		String content = socketAssistant.getContent(Protocol.HTTP,
				"mops.twse.com.tw", 80, "/mops/web/t51sb01", headerMap);
		Assert.assertEquals(content.substring(0, 16), "HTTP/1.1 200 O.K");
	}

	private void getContentByPostTest() throws Exception {
		Map<String, String> headerMap = generatePostHeaderMap();
		Map<String, String> criteriaMap = generateCriteriaMap();
		String content = socketAssistant.getContent(Protocol.HTTP,
				"mops.twse.com.tw", 80, "/mops/web/ajax_t51sb01", headerMap,
				criteriaMap);
		Assert.assertEquals(content.substring(0, 16), "HTTP/1.1 200 O.K");
	}

	private void getContentByUrlTest() throws Exception {
		Map<String, String> headerMap = generateGetHeaderMap();
		String content = socketAssistant.getContent(
				"http://mops.twse.com.tw/mops/web/t51sb01", headerMap);
		Assert.assertEquals(content.substring(0, 16), "HTTP/1.1 200 O.K");
	}

	private Map<String, String> generateGetHeaderMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Accept-Encoding", "x-gzip, gzip");
		map.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		map.put("User-Agent", "My Nutch Spider/Nutch-2.3");
		map.put("If-Modified-Since", "Thu, 01 Jan 1970 00:00:00 GMT");
		return map;
	}

	private Map<String, String> generatePostHeaderMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Host", "mops.twse.com.tw");
		map.put("User-Agent",
				"Mozilla/5.0 (X11; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0");
		map.put("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		map.put("Accept-Language", "en-US,en;q=0.5");
		map.put("Accept-Encoding", "gzip, deflate");
		map.put("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		map.put("Referer", "http://mops.twse.com.tw/mops/web/t51sb01");
		map.put("Content-Length", "55");
		map.put("Connection", "keep-alive");
		map.put("Pragma", "no-cache");
		map.put("Cache-Control", "no-cache");
		return map;
	}

	private Map<String, String> generateCriteriaMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("encodeURIComponent", "1");
		map.put("step", "1");
		map.put("firstin", "1");
		map.put("TYPEK", "pub");
		map.put("code", "98");
		return map;
	}
}
