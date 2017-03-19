package idv.hsiehpinghan.hibernatesearchormassistant.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernatesearchormassistant.configuration.SpringConfigurationTest;
import idv.hsiehpinghan.hibernatesearchormassistant.entity.BridgeEntity;
import idv.hsiehpinghan.hibernatesearchormassistant.service.BridgeEntityService;

@ContextConfiguration(classes = { SpringConfiguration.class, SpringConfigurationTest.class })
public class BridgeEntityTest extends AbstractTestNGSpringContextTests {
	private final String FIRST_NAME = "firstName" + System.currentTimeMillis();
	private final String LAST_NAME = "lastName" + System.currentTimeMillis();

	@Autowired
	private BridgeEntityService service;

	@Test
	public void save() throws Exception {
		BridgeEntity entity = generateBridgeEntity();
		service.save(entity);
		BridgeEntity.BridgeEntityId id = generateBridgeEntityId();
		BridgeEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
	}

	private BridgeEntity generateBridgeEntity() {
		BridgeEntity entity = new BridgeEntity();
		entity.setId(generateBridgeEntityId());
		return entity;
	}

	private BridgeEntity.BridgeEntityId generateBridgeEntityId() {
		BridgeEntity.BridgeEntityId entity = new BridgeEntity.BridgeEntityId();
		entity.setFirstName(FIRST_NAME);
		entity.setLastName(LAST_NAME);
		return entity;
	}
}
