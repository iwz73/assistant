package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneJoinColumnService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOnePkMappingService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneUnidirectionService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
	public void elementCollection() {
		ElementCollectionService service = applicationContext
				.getBean(ElementCollectionService.class);
		ElementCollectionEntity entity = generateElementCollectionEntity();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getElements().size(), 3);
	}

	@Test
	public void manyToOne() {
		ManyToOneService service = applicationContext
				.getBean(ManyToOneService.class);
		ManyToOneManyEntity entity = generateManyToOneManyEntity();
		service.save(entity);
		int id = entity.getId();
		ManyToOneManyEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity.getOne());
	}

	@Test
	public void manyToOneJoinColumn() {
		ManyToOneJoinColumnService service = applicationContext
				.getBean(ManyToOneJoinColumnService.class);
		ManyToOneJoinColumnManyEntity entity = generateManyToOneJoinColumnManyEntity();
		service.save(entity);
		int id = entity.getId();
		ManyToOneJoinColumnManyEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity.getOne());
	}

	@Test
	public void oneToOneUnidirection() {
		OneToOneUnidirectionService service = applicationContext
				.getBean(OneToOneUnidirectionService.class);
		OneToOneUnidirectionFromEntity entity = generateOneToOneUnidirectionFromEntity();
		service.save(entity);
		int id = entity.getId();
		OneToOneUnidirectionFromEntity fromEntity = service.findOne(id);
		Assert.assertNotNull(fromEntity.getTo());
	}

	@Test
	public void oneToOneBidirection() {
		OneToOneBidirectionService service = applicationContext
				.getBean(OneToOneBidirectionService.class);
		OneToOneBidirectionFromEntity entity = generateOneToOneBidirectionFromEntity();
		service.save(entity);
		int id = entity.getId();
		OneToOneBidirectionFromEntity fromEntity = service.findOne(id);
		Assert.assertNotNull(fromEntity.getTo());
		OneToOneBidirectionToEntity toEntity = fromEntity.getTo();
		Assert.assertNotNull(toEntity.getFrom());
	}

	@Test
	public void oneToOnePkMapping() {
		OneToOnePkMappingService service = applicationContext
				.getBean(OneToOnePkMappingService.class);
		OneToOnePkMappingFromEntity entity = generateOneToOnePkMappingFromEntity();
		service.save(entity);
		int id = entity.getId();
		OneToOnePkMappingFromEntity fromEntity = service.findOne(id);
		Assert.assertNotNull(fromEntity.getTo());
		OneToOnePkMappingToEntity toEntity = fromEntity.getTo();
		Assert.assertNotNull(toEntity.getFrom());
	}

	@Test
	public void oneToManyBidirection() {
		OneToManyBidirectionService service = applicationContext
				.getBean(OneToManyBidirectionService.class);
		OneToManyBidirectionOneEntity entity = generateOneToManyBidirectionOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyBidirectionOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
	}

//	@Test
//	public void ManyToManyBidirection() {
//		ManyToManyBidirectionService service = applicationContext
//				.getBean(ManyToManyBidirectionService.class);
//		ManyToManyBidirectionFromEntity entity = generateManyToManyBidirectionFromEntity();
//		service.save(entity);
//		int id = entity.getId();
//		ManyToManyBidirectionFromEntity fromEntity = service.findOne(id);
//		Assert.assertNotNull(fromEntity.getTo());
//		ManyToManyBidirectionToEntity toEntity = fromEntity.getTo();
//		Assert.assertNotNull(toEntity.getFrom());
//	}

//	private ManyToManyBidirectionFromEntity generateManyToManyBidirectionFromEntity() {
//		ManyToManyBidirectionFromEntity from = new ManyToManyBidirectionFromEntity();
//		from.setTo(generateManyToManyBidirectionToEntity(from));
//		return from;
//	}

//	private Collection<ManyToManyBidirectionToEntity> generateManyToManyBidirectionToEntities(
//			ManyToManyBidirectionFromEntity from) {
//		Collection<ManyToManyBidirectionToEntity> entities = new ArrayList<ManyToManyBidirectionToEntity>();
//		for(int i = 0; i < 3; ++i) {
//			entities.add(generateManyToManyBidirectionToEntity(from));
//		}
//		return entities;
//	}
	
//	private ManyToManyBidirectionToEntity generateManyToManyBidirectionToEntity(
//			ManyToManyBidirectionFromEntity from) {
//		ManyToManyBidirectionToEntity to = new ManyToManyBidirectionToEntity();
//		to.setFrom(from);
//		return to;
//	}

	private OneToManyBidirectionOneEntity generateOneToManyBidirectionOneEntity() {
		OneToManyBidirectionOneEntity one = new OneToManyBidirectionOneEntity();
		one.setMany(generateOneToManyBidirectionManyEntities(one));
		return one;
	}

	private Collection<OneToManyBidirectionManyEntity> generateOneToManyBidirectionManyEntities(
			OneToManyBidirectionOneEntity one) {
		Collection<OneToManyBidirectionManyEntity> entities = new ArrayList<OneToManyBidirectionManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateOneToManyBidirectionManyEntity(one));
		}
		return entities;
	}

	private OneToManyBidirectionManyEntity generateOneToManyBidirectionManyEntity(
			OneToManyBidirectionOneEntity one) {
		OneToManyBidirectionManyEntity many = new OneToManyBidirectionManyEntity();
		many.setOne(one);
		return many;
	}

	private OneToOnePkMappingFromEntity generateOneToOnePkMappingFromEntity() {
		OneToOnePkMappingFromEntity from = new OneToOnePkMappingFromEntity();
		from.setTo(generateOneToOnePkMappingToEntity(from));
		return from;
	}

	private OneToOnePkMappingToEntity generateOneToOnePkMappingToEntity(
			OneToOnePkMappingFromEntity from) {
		OneToOnePkMappingToEntity to = new OneToOnePkMappingToEntity();
		to.setFrom(from);
		return to;
	}

	private OneToOneBidirectionFromEntity generateOneToOneBidirectionFromEntity() {
		OneToOneBidirectionFromEntity from = new OneToOneBidirectionFromEntity();
		from.setTo(generateOneToOneBidirectionToEntity(from));
		return from;
	}

	private OneToOneBidirectionToEntity generateOneToOneBidirectionToEntity(
			OneToOneBidirectionFromEntity from) {
		OneToOneBidirectionToEntity to = new OneToOneBidirectionToEntity();
		to.setFrom(from);
		return to;
	}

	private OneToOneUnidirectionFromEntity generateOneToOneUnidirectionFromEntity() {
		OneToOneUnidirectionFromEntity entity = new OneToOneUnidirectionFromEntity();
		entity.setTo(generateOneToOneUnidirectionToEntity());
		return entity;
	}

	private OneToOneUnidirectionToEntity generateOneToOneUnidirectionToEntity() {
		OneToOneUnidirectionToEntity entity = new OneToOneUnidirectionToEntity();
		return entity;
	}

	private ManyToOneJoinColumnManyEntity generateManyToOneJoinColumnManyEntity() {
		ManyToOneJoinColumnManyEntity entity = new ManyToOneJoinColumnManyEntity();
		entity.setOne(generateManyToOneJoinColumnOneEntity());
		return entity;
	}

	private ManyToOneJoinColumnOneEntity generateManyToOneJoinColumnOneEntity() {
		ManyToOneJoinColumnOneEntity entity = new ManyToOneJoinColumnOneEntity();
		return entity;
	}

	private ManyToOneManyEntity generateManyToOneManyEntity() {
		ManyToOneManyEntity entity = new ManyToOneManyEntity();
		entity.setOne(generateManyToOneOneEntity());
		return entity;
	}

	private ManyToOneOneEntity generateManyToOneOneEntity() {
		ManyToOneOneEntity entity = new ManyToOneOneEntity();
		return entity;
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
