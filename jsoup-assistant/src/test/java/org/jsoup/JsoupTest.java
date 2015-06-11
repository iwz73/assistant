package org.jsoup;

import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JsoupTest {
	private Document doc;

	@BeforeClass
	public void beforeClass() throws Exception  {
		File html = SystemResourceUtility
				.getFileResource("html/index.html");
		doc = Jsoup.parse(html, "utf-8");
	}
	
	@Test
	public void title() throws Exception {
		String title = doc.title();
		Assert.assertEquals("Title", title);
	}
	
	@Test
	public void select() throws Exception {
		String lang = doc.select("html").get(0).attr("lang");
		Assert.assertEquals("en", lang);
		
		String charset = doc.select("meta").get(0).attr("charset");
		Assert.assertEquals("UTF-8", charset);
		
		String divClass = doc.select("div.divClass").get(0).text();
		Assert.assertEquals("div class", divClass);
		
		String divInner = doc.select("div.divOutter > div.divInner").get(0).text();
		Assert.assertEquals("div inner", divInner);
	}
	
	@Test
	public void getElementById() {
		String divText = doc.getElementById("divId").text();
		Assert.assertEquals("div content", divText);
	}
	
	@Test
	public void getElementsByTag() {
		Elements elements = doc.getElementById("formId").getElementsByTag("input");
		for(int i = 0, size = elements.size(); i < size; ++i) {
			String value = elements.get(i).val();
			Assert.assertEquals("text_" + i, value);
		}
	}
	
	@Test
	public void parseBodyFragment() {
		String fragment = "<div><p>fragment</p>";
		Document doc = Jsoup.parseBodyFragment(fragment);
		String expected = "<html><head></head><body><div><p>fragment</p></div></body></html>";
		String actual = doc.toString().replaceAll("\\s+", "");
		Assert.assertEquals(expected, actual);
	}
}
