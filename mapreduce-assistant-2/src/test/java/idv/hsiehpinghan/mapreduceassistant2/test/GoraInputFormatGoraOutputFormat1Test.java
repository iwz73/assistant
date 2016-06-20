package idv.hsiehpinghan.mapreduceassistant2.test;

import java.util.HashSet;
import java.util.Set;

import org.apache.gora.util.GoraException;
import org.apache.nutch.storage.WebPage;
import org.apache.nutch.util.TableUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.mapreduceassistant2.configuration.SpringConfiguration;
import idv.hsiehpinghan.mapreduceassistant2.job.GoraInputFormatGoraOutputFormat1;
import idv.hsiehpinghan.mapreduceassistant2.repository.SeedRepository;
import idv.hsiehpinghan.mapreduceassistant2.repository.WebPageRepository;
import idv.hsiehpinghan.nutch.storage.Seed;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class GoraInputFormatGoraOutputFormat1Test extends AbstractTestNGSpringContextTests {
	private static final String KEY = "http://www.google.com";
	private static final Float SCORE = 1.1f;
	private static final Integer FETCH_INTERVAL = 100;

	private static final Set<Seed.Field> FIELDS = new HashSet<Seed.Field>();
	static {
		FIELDS.add(Seed.Field.SCORE);
		FIELDS.add(Seed.Field.FETCH_INTERVAL);
	}
	@Autowired
	private GoraInputFormatGoraOutputFormat1 goraInputFormatGoraOutputFormat1;
	@Autowired
	private SeedRepository seedRepository;
	@Autowired
	private WebPageRepository webPageRepository;

	@BeforeClass
	public void beforeClass() throws Exception {
		injectSeed();
	}

	@Test
	public void inject() throws Exception {
		String batchId = String.valueOf(System.currentTimeMillis());
		Assert.assertTrue(goraInputFormatGoraOutputFormat1.inject(batchId, FIELDS));
		WebPage webPage = webPageRepository.get(TableUtil.reverseUrl(KEY));
		Assert.assertEquals(webPage.getScore(), SCORE);
		Assert.assertEquals(webPage.getFetchInterval(), FETCH_INTERVAL);
	}

	private void injectSeed() throws GoraException {
		Seed seed = Seed.newBuilder().build();
		seed.setScore(SCORE);
		seed.setFetchInterval(FETCH_INTERVAL);
		seedRepository.put(KEY, seed);
	}
}
