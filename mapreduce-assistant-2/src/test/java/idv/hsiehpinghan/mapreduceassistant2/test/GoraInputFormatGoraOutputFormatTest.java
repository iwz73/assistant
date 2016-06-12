package idv.hsiehpinghan.mapreduceassistant2.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.avro.util.Utf8;
import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.crawl.DbUpdaterJob;
import org.apache.nutch.crawl.GeneratorJob;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.mapreduceassistant2.configuration.SpringConfiguration;
import idv.hsiehpinghan.mapreduceassistant2.job.GoraInputFormatGoraOutputFormat;
import idv.hsiehpinghan.mapreduceassistant2.repository.WebPageRepository;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class GoraInputFormatGoraOutputFormatTest extends AbstractTestNGSpringContextTests {
	private static final Set<WebPage.Field> FIELDS = new HashSet<WebPage.Field>();
	static {
		FIELDS.add(WebPage.Field.FETCH_TIME);
		FIELDS.add(WebPage.Field.SCORE);
		FIELDS.add(WebPage.Field.STATUS);
		FIELDS.add(WebPage.Field.MARKERS);
	}
	private Configuration conf;
	private long curTime = System.currentTimeMillis();
	private float score = 1.1f;
	private int status = 1;
	private Utf8 distance = new Utf8(String.valueOf(1));
	@Autowired
	private GoraInputFormatGoraOutputFormat goraInputFormatGoraOutputFormat;
	@Autowired
	private WebPageRepository webPageRepository;

	@BeforeClass
	public void beforeClass() throws Exception {
		injectWebPage("http://www.google.com");
		conf = new Configuration();
	}

	@Test
	public void test() throws Exception {
		String batchId = String.valueOf(System.currentTimeMillis());
		Assert.assertTrue(goraInputFormatGoraOutputFormat.test(batchId, FIELDS));
	}

	private void injectWebPage(String url) throws IOException, Exception {
		String reversedUrl = getReverseUrl(url);
		WebPage webPage = generateWebPage(url);
		webPageRepository.put(reversedUrl, webPage);
	}

	private String getReverseUrl(String url) throws MalformedURLException {
		return TableUtil.reverseUrl(url);
	}

	private WebPage generateWebPage(String url) throws MalformedURLException {
		WebPage row = WebPage.newBuilder().build();
		row.setFetchTime(curTime);
		row.setScore(score);
		row.setStatus(status);
		row.getMarkers().put(DbUpdaterJob.DISTANCE, distance);
		return row;
	}
}
