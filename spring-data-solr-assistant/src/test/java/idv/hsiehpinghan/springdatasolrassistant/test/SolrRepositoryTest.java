package idv.hsiehpinghan.springdatasolrassistant.test;

import idv.hsiehpinghan.springdatasolrassistant.entity.SolrEntity;
import idv.hsiehpinghan.springdatasolrassistant.repository.implement.SolrRepository;
import idv.hsiehpinghan.springdatasolrassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.result.SolrResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SolrRepositoryTest {
	private SolrRepository repository;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		repository = applicationContext.getBean(SolrRepository.class);
	}

	@Test
	public void query() throws Exception {
		SolrResultPage<SolrEntity> entities = repository.query("iPod", null,
				null, null, null, new PageRequest(0, 10));
		Assert.assertEquals(3, entities.getTotalElements());
	}
}
