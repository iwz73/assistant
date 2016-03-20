package org.jsoup;

import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;
import java.util.Iterator;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JsoupTest {
	private Document doc;

	@BeforeClass
	public void beforeClass() throws Exception {
		File html = SystemResourceUtility.getFileResource("html/index.html");
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

		String divInner = doc.select("div.divOutter > div.divInner").get(0)
				.text();
		Assert.assertEquals("div inner", divInner);
	}

	@Test
	public void table() throws Exception {
		Element table = doc.getElementById("tableId");
		Iterator<Element> trIter = table.select("tbody>tr").iterator();
		int row = 0;
		while (trIter.hasNext()) {
			Element tr = trIter.next();
			Iterator<Element> tdIter = tr.select("td").iterator();
			int column = 0;
			while (tdIter.hasNext()) {
				Assert.assertEquals("tbody/tr/td/" + row + "-" + column, tdIter
						.next().text());
				++column;
			}
			++row;
		}
	}

	@Test
	public void getElementById() {
		String divText = doc.getElementById("divId").text();
		Assert.assertEquals("div content", divText);
	}

	@Test
	public void getElementsByTag() {
		Elements elements = doc.getElementById("formId").getElementsByTag(
				"input");
		for (int i = 0, size = elements.size(); i < size; ++i) {
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

	@Test
	public void post() throws Exception {
		Document doc = Jsoup
				.connect("http://mops.twse.com.tw/mops/web/ajax_t51sb01")
				.data("code", "01").data("step", "1").data("firstin", "1")
				.data("encodeURIComponent", "1").data("TYPEK", "sii")
				.userAgent("Mozilla").post();
		Assert.assertEquals("公開資訊觀測站", doc.title());
	}

	@Test
	public void customizedAttribute() throws Exception {
		Elements elements = doc.getElementsByAttributeValue(
				"customizedAttribute", "test");
		Assert.assertEquals(elements.size(), 1);
		Assert.assertEquals(elements.get(0).text(), "customizedAttribute");
	}

	@Test
	public void subElement() throws Exception {
		Element parent = doc.getElementsByAttributeValue("parent", "parent")
				.get(0);
		Elements children = parent
				.getElementsByAttributeValue("child", "child");
		Assert.assertEquals(children.get(0).text(), "child 0");
		Assert.assertEquals(children.get(1).text(), "child 1");
	}
}
