package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyUnidirectionManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyUnidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService;
import idv.hsiehpinghan.hibernateassistant.service.EmbeddedObjectService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyJoinTableService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneJoinColumnService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyUnidirectionService;
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
	private static final String EMBEDDABLE_STRING = "EmbeddableString";
	private ApplicationContext applicationContext;

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

	@Test
	public void oneToManyUnidirection() {
		OneToManyUnidirectionService service = applicationContext
				.getBean(OneToManyUnidirectionService.class);
		OneToManyUnidirectionOneEntity entity = generateOneToManyUnidirectionOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyUnidirectionOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
	}

	@Test
	public void ManyToManyBidirection() {
		ManyToManyBidirectionService service = applicationContext
				.getBean(ManyToManyBidirectionService.class);
		Collection<ManyToManyBidirectionFromEntity> entities = generateManyToManyBidirectionFromEntities();
		for (ManyToManyBidirectionFromEntity entity : entities) {
			service.saveOrUpdate(entity);
		}
		for (ManyToManyBidirectionFromEntity entity : entities) {
			int id = entity.getId();
			ManyToManyBidirectionFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (ManyToManyBidirectionToEntity to : fromEntity.getTos()) {
				Assert.assertEquals(to.getFroms().size(), 3);
			}
		}
	}

	@Test
	public void ManyToManyJoinTable() {
		ManyToManyJoinTableService service = applicationContext
				.getBean(ManyToManyJoinTableService.class);
		Collection<ManyToManyJoinTableFromEntity> entities = generateManyToManyJoinTableFromEntities();
		for (ManyToManyJoinTableFromEntity entity : entities) {
			service.saveOrUpdate(entity);
		}
		for (ManyToManyJoinTableFromEntity entity : entities) {
			int id = entity.getId();
			ManyToManyJoinTableFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (ManyToManyJoinTableToEntity to : fromEntity.getTos()) {
				Assert.assertEquals(to.getFroms().size(), 3);
			}
		}
	}

	@Test
	public void embeddedObject() {
		EmbeddedObjectService service = applicationContext
				.getBean(EmbeddedObjectService.class);
		EmbeddedObjectContainerEntity entity = generateEmbeddedObjectContainerEntity();
		service.save(entity);
		int id = entity.getId();
		EmbeddedObjectContainerEntity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getEmbedded().getEmbeddableString(),
				EMBEDDABLE_STRING);
	}

	private EmbeddedObjectContainerEntity generateEmbeddedObjectContainerEntity() {
		EmbeddedObjectContainerEntity entity = new EmbeddedObjectContainerEntity();
		entity.setEmbedded(generateEmbeddedObjectEmbeddableEntity());
		return entity;
	}

	private EmbeddedObjectEmbeddableEntity generateEmbeddedObjectEmbeddableEntity() {
		EmbeddedObjectEmbeddableEntity entity = new EmbeddedObjectEmbeddableEntity();
		entity.setEmbeddableString(EMBEDDABLE_STRING);
		return entity;
	}

	private Collection<ManyToManyJoinTableFromEntity> generateManyToManyJoinTableFromEntities() {
		Collection<ManyToManyJoinTableFromEntity> entities = new ArrayList<ManyToManyJoinTableFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyJoinTableFromEntity(i));
		}
		return entities;
	}

	private Collection<ManyToManyBidirectionFromEntity> generateManyToManyBidirectionFromEntities() {
		Collection<ManyToManyBidirectionFromEntity> entities = new ArrayList<ManyToManyBidirectionFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyBidirectionFromEntity(i));
		}
		return entities;
	}

	private ManyToManyJoinTableFromEntity generateManyToManyJoinTableFromEntity(
			int i) {
		ManyToManyJoinTableFromEntity from = new ManyToManyJoinTableFromEntity();
		from.setId(i);
		from.setTos(generateManyToManyJoinTableToEntities(from));
		return from;
	}

	private ManyToManyBidirectionFromEntity generateManyToManyBidirectionFromEntity(
			int i) {
		ManyToManyBidirectionFromEntity from = new ManyToManyBidirectionFromEntity();
		from.setId(i);
		from.setTos(generateManyToManyBidirectionToEntities(from));
		return from;
	}

	private Collection<ManyToManyJoinTableToEntity> generateManyToManyJoinTableToEntities(
			ManyToManyJoinTableFromEntity from) {
		Collection<ManyToManyJoinTableToEntity> entities = new ArrayList<ManyToManyJoinTableToEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyJoinTableToEntity(from, i));
		}
		return entities;
	}

	private Collection<ManyToManyBidirectionToEntity> generateManyToManyBidirectionToEntities(
			ManyToManyBidirectionFromEntity from) {
		Collection<ManyToManyBidirectionToEntity> entities = new ArrayList<ManyToManyBidirectionToEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyBidirectionToEntity(from, i));
		}
		return entities;
	}

	private ManyToManyJoinTableToEntity generateManyToManyJoinTableToEntity(
			ManyToManyJoinTableFromEntity from, int i) {
		ManyToManyJoinTableToEntity to = new ManyToManyJoinTableToEntity();
		to.setId(i);
		to.addFrom(from);
		return to;
	}

	private ManyToManyBidirectionToEntity generateManyToManyBidirectionToEntity(
			ManyToManyBidirectionFromEntity from, int i) {
		ManyToManyBidirectionToEntity to = new ManyToManyBidirectionToEntity();
		to.setId(i);
		to.addFrom(from);
		return to;
	}

	private OneToManyUnidirectionOneEntity generateOneToManyUnidirectionOneEntity() {
		OneToManyUnidirectionOneEntity one = new OneToManyUnidirectionOneEntity();
		one.setMany(generateOneToManyUnidirectionManyEntities(one));
		return one;
	}

	private OneToManyBidirectionOneEntity generateOneToManyBidirectionOneEntity() {
		OneToManyBidirectionOneEntity one = new OneToManyBidirectionOneEntity();
		one.setMany(generateOneToManyBidirectionManyEntities(one));
		return one;
	}

	private Collection<OneToManyUnidirectionManyEntity> generateOneToManyUnidirectionManyEntities(
			OneToManyUnidirectionOneEntity one) {
		Collection<OneToManyUnidirectionManyEntity> entities = new ArrayList<OneToManyUnidirectionManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateOneToManyUnidirectionManyEntity(one));
		}
		return entities;
	}

	private Collection<OneToManyBidirectionManyEntity> generateOneToManyBidirectionManyEntities(
			OneToManyBidirectionOneEntity one) {
		Collection<OneToManyBidirectionManyEntity> entities = new ArrayList<OneToManyBidirectionManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateOneToManyBidirectionManyEntity(one));
		}
		return entities;
	}

	private OneToManyUnidirectionManyEntity generateOneToManyUnidirectionManyEntity(
			OneToManyUnidirectionOneEntity one) {
		OneToManyUnidirectionManyEntity many = new OneToManyUnidirectionManyEntity();
		return many;
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
