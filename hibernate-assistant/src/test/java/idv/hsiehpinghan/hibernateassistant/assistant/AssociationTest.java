package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionContainerEntity1;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEmbeddableEntity1;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEnumerationMapEntity4;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionStringMapEntity3;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionTableContainerEntity2;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionTableEmbeddableEntity2;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableEmbeddedEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyListManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyListOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapEmbeddableEmbeddedEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapEmbeddableManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapEmbeddableOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapKeyManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapKeyOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyMapOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyOrderColumnManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyOrderColumnOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyUnidirectionManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyUnidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneBidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService1;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService2;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService3;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService4;
import idv.hsiehpinghan.hibernateassistant.service.EmbeddedIdService;
import idv.hsiehpinghan.hibernateassistant.service.EmbeddedObjectService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyJoinTableService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyMapEmbeddableService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyMapService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneJoinColumnService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyListService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyMapEmbeddableService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyMapKeyService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyMapService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyOrderColumnService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyUnidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOnePkMappingService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneUnidirectionService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	public void elementCollection1() {
		ElementCollectionService1 service = applicationContext
				.getBean(ElementCollectionService1.class);
		ElementCollectionContainerEntity1 entity = generateElementCollectionContainerEntity1();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionContainerEntity1 returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getElements().size(), 3);
	}

	@Test
	public void elementCollection2() {
		ElementCollectionService2 service = applicationContext
				.getBean(ElementCollectionService2.class);
		ElementCollectionTableContainerEntity2 entity = generateElementCollectionTableContainerEntity2();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionTableContainerEntity2 returnEntity = service
				.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getElements().size(), 3);
	}

	@Test
	public void elementCollection3() {
		ElementCollectionService3 service = applicationContext
				.getBean(ElementCollectionService3.class);
		ElementCollectionStringMapEntity3 entity = generateElementCollectionStringMapEntity3();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionStringMapEntity3 returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getMap().size(), 3);
	}

	@Test
	public void elementCollection4() {
		ElementCollectionService4 service = applicationContext
				.getBean(ElementCollectionService4.class);
		ElementCollectionEnumerationMapEntity4 entity = generateElementCollectionEnumerationMapEntity4();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionEnumerationMapEntity4 returnEntity = service
				.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getMap().size(), 3);
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
	public void oneToManyMap() {
		OneToManyMapService service = applicationContext
				.getBean(OneToManyMapService.class);
		OneToManyMapOneEntity entity = generateOneToManyMapOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyMapOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
	}

	@Test
	public void oneToManyMapKey() {
		OneToManyMapKeyService service = applicationContext
				.getBean(OneToManyMapKeyService.class);
		OneToManyMapKeyOneEntity entity = generateOneToManyMapKeyOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyMapKeyOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		for (Entry<Integer, OneToManyMapKeyManyEntity> entry : oneEntity
				.getMany().entrySet()) {
			Assert.assertEquals(entry.getKey(), entry.getValue().getTargetId());
		}
	}

	// @Test
	public void oneToManyMapEmbeddable() {
		OneToManyMapEmbeddableService service = applicationContext
				.getBean(OneToManyMapEmbeddableService.class);
		OneToManyMapEmbeddableOneEntity entity = generateOneToManyMapEmbeddableOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyMapEmbeddableOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		for (Entry<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity> entry : oneEntity
				.getMany().entrySet()) {
			Assert.assertNotNull(entry.getValue().getOne());
		}
	}

	@Test
	public void oneToManyOrderColumn() {
		OneToManyOrderColumnService service = applicationContext
				.getBean(OneToManyOrderColumnService.class);
		OneToManyOrderColumnOneEntity entity = generateOneToManyOrderColumnOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyOrderColumnOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		for (int i = 0, size = oneEntity.getMany().size(); i < size; ++i) {
			Assert.assertEquals(oneEntity.getMany().get(i).getId(),
					Integer.valueOf(2 - i));
		}
	}

	@Test
	public void oneToManyList() {
		OneToManyListService service = applicationContext
				.getBean(OneToManyListService.class);
		OneToManyListOneEntity entity = generateOneToManyListOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyListOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		for (int i = 0, size = oneEntity.getMany().size(); i < size; ++i) {
			OneToManyListManyEntity many = oneEntity.getMany().get(i);
			Assert.assertEquals(many.getName(), "name_" + (2 - i));
		}
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
	public void ManyToManyMapEmbeddable() {
		ManyToManyMapEmbeddableService service = applicationContext
				.getBean(ManyToManyMapEmbeddableService.class);
		service.deleteAll();
		Collection<ManyToManyMapEmbeddableFromEntity> entities = generateManyToManyMapEmbeddableFromEntities();
		for (ManyToManyMapEmbeddableFromEntity entity : entities) {
			service.save(entity);
		}
		for (ManyToManyMapEmbeddableFromEntity entity : entities) {
			int id = entity.getId();
			ManyToManyMapEmbeddableFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (Entry<ManyToManyMapEmbeddableEntity, ManyToManyMapEmbeddableToEntity> to : fromEntity
					.getTos().entrySet()) {
				Assert.assertEquals(to.getValue().getFroms().size(), 3);
			}
		}
	}

	@Test
	public void ManyToManyMap() {
		ManyToManyMapService service = applicationContext
				.getBean(ManyToManyMapService.class);
		Collection<ManyToManyMapFromEntity> entities = generateManyToManyMapFromEntities();
		for (ManyToManyMapFromEntity entity : entities) {
			service.saveOrUpdate(entity);
		}
		for (ManyToManyMapFromEntity entity : entities) {
			int id = entity.getId();
			ManyToManyMapFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (Entry<String, ManyToManyMapToEntity> entry : fromEntity
					.getTos().entrySet()) {
				Assert.assertEquals(entry.getValue().getFroms().size(), 3);
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

	@Test
	public void embeddedId() {
		EmbeddedIdService service = applicationContext
				.getBean(EmbeddedIdService.class);
		EmbeddedIdContainerEntity entity = generateEmbeddedIdContainerEntity();
		service.save(entity);
		EmbeddedIdEmbeddableEntity id = generateEmbeddedIdEmbeddableEntity();
		EmbeddedIdContainerEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		service.deleteAll();
	}

	private EmbeddedObjectContainerEntity generateEmbeddedObjectContainerEntity() {
		EmbeddedObjectContainerEntity entity = new EmbeddedObjectContainerEntity();
		entity.setEmbedded(generateEmbeddedObjectEmbeddableEntity());
		return entity;
	}

	private EmbeddedIdContainerEntity generateEmbeddedIdContainerEntity() {
		EmbeddedIdContainerEntity entity = new EmbeddedIdContainerEntity();
		entity.setId(generateEmbeddedIdEmbeddableEntity());
		return entity;
	}

	private EmbeddedObjectEmbeddableEntity generateEmbeddedObjectEmbeddableEntity() {
		EmbeddedObjectEmbeddableEntity entity = new EmbeddedObjectEmbeddableEntity();
		entity.setEmbeddableString(EMBEDDABLE_STRING);
		return entity;
	}

	private EmbeddedIdEmbeddableEntity generateEmbeddedIdEmbeddableEntity() {
		EmbeddedIdEmbeddableEntity entity = new EmbeddedIdEmbeddableEntity();
		entity.setFirstName("firstName");
		entity.setLastName("lastName");
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

	private Collection<ManyToManyMapEmbeddableFromEntity> generateManyToManyMapEmbeddableFromEntities() {
		Collection<ManyToManyMapEmbeddableFromEntity> entities = new ArrayList<ManyToManyMapEmbeddableFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyMapEmbeddableFromEntity(i));
		}
		return entities;
	}

	private Collection<ManyToManyMapFromEntity> generateManyToManyMapFromEntities() {
		Collection<ManyToManyMapFromEntity> entities = new ArrayList<ManyToManyMapFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyMapFromEntity(i));
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

	private ManyToManyMapEmbeddableFromEntity generateManyToManyMapEmbeddableFromEntity(
			int i) {
		ManyToManyMapEmbeddableFromEntity from = new ManyToManyMapEmbeddableFromEntity();
		from.setId(i);
		from.setTos(generateManyToManyMapEmbeddableToEntityMap(from));
		return from;
	}

	private ManyToManyMapFromEntity generateManyToManyMapFromEntity(int i) {
		ManyToManyMapFromEntity from = new ManyToManyMapFromEntity();
		from.setId(i);
		from.setTos(generateManyToManyMapToEntityMap(from));
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

	private Map<ManyToManyMapEmbeddableEntity, ManyToManyMapEmbeddableToEntity> generateManyToManyMapEmbeddableToEntityMap(
			ManyToManyMapEmbeddableFromEntity from) {
		Map<ManyToManyMapEmbeddableEntity, ManyToManyMapEmbeddableToEntity> map = new HashMap<ManyToManyMapEmbeddableEntity, ManyToManyMapEmbeddableToEntity>();
		for (int i = 0; i < 3; ++i) {
			ManyToManyMapEmbeddableEntity key = new ManyToManyMapEmbeddableEntity();
			key.setAge(i);
			key.setName("name_" + i);
			map.put(key, generateManyToManyMapEmbeddableToEntity(from, i));
		}
		return map;
	}

	private Map<String, ManyToManyMapToEntity> generateManyToManyMapToEntityMap(
			ManyToManyMapFromEntity from) {
		Map<String, ManyToManyMapToEntity> entityMap = new HashMap<String, ManyToManyMapToEntity>();
		for (int i = 0; i < 3; ++i) {
			entityMap.put(String.valueOf(i),
					generateManyToManyMapToEntity(from, i));
		}
		return entityMap;
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

	private ManyToManyMapEmbeddableToEntity generateManyToManyMapEmbeddableToEntity(
			ManyToManyMapEmbeddableFromEntity from, int i) {
		ManyToManyMapEmbeddableToEntity to = new ManyToManyMapEmbeddableToEntity();
		to.setId(i);
		to.setEmbeddable(generateManyToManyMapEmbeddableEmbeddedEntity(i));
		to.addFrom(from);
		return to;
	}

	private ManyToManyMapEmbeddableEmbeddedEntity generateManyToManyMapEmbeddableEmbeddedEntity(
			int i) {
		ManyToManyMapEmbeddableEmbeddedEntity embeddable = new ManyToManyMapEmbeddableEmbeddedEntity();
		embeddable.setFirstName("firstName_" + i);
		embeddable.setLastName("lastName_" + i);
		return embeddable;
	}

	private ManyToManyMapToEntity generateManyToManyMapToEntity(
			ManyToManyMapFromEntity from, int i) {
		ManyToManyMapToEntity to = new ManyToManyMapToEntity();
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

	private OneToManyMapOneEntity generateOneToManyMapOneEntity() {
		OneToManyMapOneEntity one = new OneToManyMapOneEntity();
		one.setMany(generateOneToManyMapManyEntityMap(one));
		return one;
	}

	private OneToManyMapKeyOneEntity generateOneToManyMapKeyOneEntity() {
		OneToManyMapKeyOneEntity one = new OneToManyMapKeyOneEntity();
		one.setMany(generateOneToManyMapKeyManyEntityMap(one));
		return one;
	}

	private OneToManyMapEmbeddableOneEntity generateOneToManyMapEmbeddableOneEntity() {
		OneToManyMapEmbeddableOneEntity one = new OneToManyMapEmbeddableOneEntity();
		one.setMany(generateOneToManyMapEmbeddableManyEntityMap(one));
		return one;
	}

	private OneToManyOrderColumnOneEntity generateOneToManyOrderColumnOneEntity() {
		OneToManyOrderColumnOneEntity one = new OneToManyOrderColumnOneEntity();
		one.setMany(generateOneToManyOrderColumnManyEntities(one));
		return one;
	}

	private OneToManyListOneEntity generateOneToManyListOneEntity() {
		OneToManyListOneEntity one = new OneToManyListOneEntity();
		one.setMany(generateOneToManyListManyEntities(one));
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

	private Map<String, OneToManyMapManyEntity> generateOneToManyMapManyEntityMap(
			OneToManyMapOneEntity one) {
		Map<String, OneToManyMapManyEntity> entityMap = new HashMap<String, OneToManyMapManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entityMap.put("key_" + i, generateOneToManyMapManyEntity(one));
		}
		return entityMap;
	}

	private Map<Integer, OneToManyMapKeyManyEntity> generateOneToManyMapKeyManyEntityMap(
			OneToManyMapKeyOneEntity one) {
		Map<Integer, OneToManyMapKeyManyEntity> entityMapKey = new HashMap<Integer, OneToManyMapKeyManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entityMapKey.put(i, generateOneToManyMapKeyManyEntity(one, i)); // the
																			// key
																			// of
																			// map
																			// will
																			// not
																			// persisted.
		}
		return entityMapKey;
	}

	private Map<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity> generateOneToManyMapEmbeddableManyEntityMap(
			OneToManyMapEmbeddableOneEntity one) {
		Map<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity> entityMapEmbeddable = new HashMap<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity>();
		for (int i = 0; i < 3; ++i) {
			OneToManyMapEmbeddableEntity embeddable = new OneToManyMapEmbeddableEntity();
			embeddable.setAge(i);
			embeddable.setName("name_" + i);
			entityMapEmbeddable.put(embeddable,
					generateOneToManyMapEmbeddableManyEntity(one, i));
		}
		return entityMapEmbeddable;
	}

	private List<OneToManyOrderColumnManyEntity> generateOneToManyOrderColumnManyEntities(
			OneToManyOrderColumnOneEntity one) {
		List<OneToManyOrderColumnManyEntity> entities = new ArrayList<OneToManyOrderColumnManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateOneToManyOrderColumnManyEntity(one, i));
		}
		return entities;
	}

	private List<OneToManyListManyEntity> generateOneToManyListManyEntities(
			OneToManyListOneEntity one) {
		List<OneToManyListManyEntity> entities = new ArrayList<OneToManyListManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateOneToManyListManyEntity(one, i));
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

	private OneToManyMapManyEntity generateOneToManyMapManyEntity(
			OneToManyMapOneEntity one) {
		OneToManyMapManyEntity many = new OneToManyMapManyEntity();
		many.setOne(one);
		return many;
	}

	private OneToManyMapKeyManyEntity generateOneToManyMapKeyManyEntity(
			OneToManyMapKeyOneEntity one, int i) {
		OneToManyMapKeyManyEntity many = new OneToManyMapKeyManyEntity();
		many.setTargetId(i + 100);
		many.setOne(one);
		return many;
	}

	private OneToManyMapEmbeddableManyEntity generateOneToManyMapEmbeddableManyEntity(
			OneToManyMapEmbeddableOneEntity one, int i) {
		OneToManyMapEmbeddableManyEntity many = new OneToManyMapEmbeddableManyEntity();
		many.setEmbeddable(generateOneToManyMapEmbeddableEmbeddedEntity(i));
		many.setOne(one);
		return many;
	}

	private OneToManyMapEmbeddableEmbeddedEntity generateOneToManyMapEmbeddableEmbeddedEntity(
			int i) {
		OneToManyMapEmbeddableEmbeddedEntity embedded = new OneToManyMapEmbeddableEmbeddedEntity();
		embedded.setFirstName("notPersisted_" + i); // the
		// value
		// will
		// not
		// persisted.
		embedded.setLastName("notPersisted_" + i); // the
		// value
		// will
		// not
		// persisted.
		return embedded;
	}

	private OneToManyOrderColumnManyEntity generateOneToManyOrderColumnManyEntity(
			OneToManyOrderColumnOneEntity one, int i) {
		OneToManyOrderColumnManyEntity many = new OneToManyOrderColumnManyEntity();
		many.setId(2 - i);
		many.setOne(one);
		return many;
	}

	private OneToManyListManyEntity generateOneToManyListManyEntity(
			OneToManyListOneEntity one, int i) {
		OneToManyListManyEntity many = new OneToManyListManyEntity();
		many.setName("name_" + i);
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

	private ElementCollectionContainerEntity1 generateElementCollectionContainerEntity1() {
		ElementCollectionContainerEntity1 entity = new ElementCollectionContainerEntity1();
		Set<ElementCollectionEmbeddableEntity1> elements = generateElementCollectionEmbeddableEntity1s();
		entity.setElements(elements);
		return entity;
	}

	private ElementCollectionTableContainerEntity2 generateElementCollectionTableContainerEntity2() {
		ElementCollectionTableContainerEntity2 entity = new ElementCollectionTableContainerEntity2();
		Set<ElementCollectionTableEmbeddableEntity2> elements = generateElementCollectionTableEmbeddableEntity2s();
		entity.setElements(elements);
		return entity;
	}

	private ElementCollectionEntity generateElementCollectionEntity() {
		ElementCollectionEntity entity = new ElementCollectionEntity();
		Set<String> elements = generateStringElements();
		entity.setElements(elements);
		return entity;
	}

	private ElementCollectionStringMapEntity3 generateElementCollectionStringMapEntity3() {
		ElementCollectionStringMapEntity3 entity = new ElementCollectionStringMapEntity3();
		Map<String, String> map = new HashMap<String, String>();
		map.put("key_1", "value_1");
		map.put("key_2", "value_2");
		map.put("key_3", "value_3");
		entity.setMap(map);
		return entity;
	}

	private ElementCollectionEnumerationMapEntity4 generateElementCollectionEnumerationMapEntity4() {
		ElementCollectionEnumerationMapEntity4 entity = new ElementCollectionEnumerationMapEntity4();
		Map<Enumeration, String> map = new HashMap<Enumeration, String>();
		map.put(Enumeration.ENUM_1, "value_1");
		map.put(Enumeration.ENUM_2, "value_2");
		map.put(Enumeration.ENUM_3, "value_3");
		entity.setMap(map);
		return entity;
	}

	private Set<String> generateStringElements() {
		Set<String> elements = new HashSet<String>(3);
		elements.add("item 1");
		elements.add("item 2");
		elements.add("item 3");
		return elements;
	}

	private Set<ElementCollectionEmbeddableEntity1> generateElementCollectionEmbeddableEntity1s() {
		Set<ElementCollectionEmbeddableEntity1> elements = new HashSet<ElementCollectionEmbeddableEntity1>();
		for (int i = 0; i < 3; ++i) {
			ElementCollectionEmbeddableEntity1 element = new ElementCollectionEmbeddableEntity1();
			element.setName("name " + i);
			elements.add(element);
		}
		return elements;
	}

	private Set<ElementCollectionTableEmbeddableEntity2> generateElementCollectionTableEmbeddableEntity2s() {
		Set<ElementCollectionTableEmbeddableEntity2> elements = new HashSet<ElementCollectionTableEmbeddableEntity2>();
		for (int i = 0; i < 3; ++i) {
			ElementCollectionTableEmbeddableEntity2 element = new ElementCollectionTableEmbeddableEntity2();
			element.setName("name " + i);
			elements.add(element);
		}
		return elements;
	}
}
