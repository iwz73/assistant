package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AssociationTest {
	ApplicationContext applicationContext;

	@BeforeClass
	public void beforeClass() throws IOException {
		applicationContext = TestngSuitSetting.getApplicationContext();
	}

	@Test
	public void oneToOne() {
		ElementCollectionService service = applicationContext
				.getBean(ElementCollectionService.class);
		ElementCollectionEntity entity = generateElementCollectionEntity();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getElements().size(), 3);
	}

	private ElementCollectionEntity generateElementCollectionEntity() {
		ElementCollectionEntity entity = new ElementCollectionEntity();
		Set<String> elements = generateStringElements();
		entity.setElements(elements);
		return entity;
	}

	private Set<String> generateStringElements() {
		Set<String> elements = new HashSet<String>(3);
		elements.add("item 1");
		elements.add("item 2");
		elements.add("item 3");
		return elements;
	}
}
