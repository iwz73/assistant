package idv.hsiehpinghan.springdatasolrassistant.test;

import idv.hsiehpinghan.springdatasolrassistant.entity.SolrEntity;
import idv.hsiehpinghan.springdatasolrassistant.repository.ISolrEntityRepository;
import idv.hsiehpinghan.springdatasolrassistant.suit.TestngSuitSetting;

import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ISolrEntityRepositoryTest {
	private String criteria = "iPod";
	private ISolrEntityRepository repository;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		repository = applicationContext.getBean(ISolrEntityRepository.class);
	}

	@Test
	public void query() {
		Page<SolrEntity> entities = repository.query(criteria, new PageRequest(
				0, 10));
		Assert.assertEquals(3, entities.getTotalElements());

	}

	@Test
	public void findAllFacetOnName() {
		FacetPage<SolrEntity> entities = repository.findAllFacetOnName(
				criteria, new PageRequest(0, 10));

		for (Page<? extends FacetEntry> facet : entities.getAllFacets()) {
			for (FacetEntry entry : facet.getContent()) {
				String category = entry.getValue();
				System.err.println(category + " / " + entry.getValueCount());
			}
			System.err.println("-----------");
		}

	}
}
