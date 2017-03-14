package idv.hsiehpinghan.hibernatesearchormassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfigurationTest;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.EmbeddedIdContainerEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.EmbeddedIdEmbeddableEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.service.EmbeddedIdService;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class EmbeddedIdTest extends AbstractTestNGSpringContextTests {
	private final String FIRST_NAME = "firstName" + System.currentTimeMillis();
	private final String LAST_NAME = "lastName" + System.currentTimeMillis();

	@Autowired
	private EmbeddedIdService service;

	@Test
	public void save() throws Exception {
		EmbeddedIdContainerEntity entity = generateEmbeddedIdContainerEntity();
		service.save(entity);
		EmbeddedIdEmbeddableEntity id = generateEmbeddedIdEmbeddableEntity();
		EmbeddedIdContainerEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
	}

	private EmbeddedIdContainerEntity generateEmbeddedIdContainerEntity() {
		EmbeddedIdContainerEntity entity = new EmbeddedIdContainerEntity();
		entity.setId(generateEmbeddedIdEmbeddableEntity());
		return entity;
	}

	private EmbeddedIdEmbeddableEntity generateEmbeddedIdEmbeddableEntity() {
		EmbeddedIdEmbeddableEntity entity = new EmbeddedIdEmbeddableEntity();
		entity.setFirstName(FIRST_NAME);
		entity.setLastName(LAST_NAME);
		return entity;
	}
}
