package idv.hsiehpinghan.hibernateassistant.assistant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.hibernateassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionContainerEntity1;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEmbeddableEntity1;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionEnumerationMapEntity4;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionStringMapEntity3;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionTableContainerEntity2;
import idv.hsiehpinghan.hibernateassistant.entity.ElementCollectionTableEmbeddableEntity2;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedIdEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedMultipleTableContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedMultipleTableEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectContainerEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EmbeddedObjectEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.JoinedInheritance_1_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.JoinedInheritance_2_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.JoinedInheritance_2_B_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdFromIdEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyCompoundIdToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyJoinTableToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableEmbeddedEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapEmbeddableToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyMapToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyUnidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToManyUnidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinColumnOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinTableManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneJoinTableOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.ManyToOneOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.MixedInheritance_1_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.MixedInheritance_2_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.MixedInheritance_2_B_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.MultipleTableEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyBidirectionOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdManyEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdManyIdEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdOneEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToManyDerivedEmbeddedIdOneIdEntity;
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
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneDerivedSingleKeyDerivedEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneDerivedSingleKeyMainEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneMapsIdMainEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneMapsIdMapperEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOnePkMappingToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionFromEntity;
import idv.hsiehpinghan.hibernateassistant.entity.OneToOneUnidirectionToEntity;
import idv.hsiehpinghan.hibernateassistant.entity.SingleTableInheritance_1_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.SingleTableInheritance_2_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.SingleTableInheritance_2_B_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.TablePerClassInheritance_1_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.TablePerClassInheritance_2_A_Entity;
import idv.hsiehpinghan.hibernateassistant.entity.TablePerClassInheritance_2_B_Entity;
import idv.hsiehpinghan.hibernateassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService1;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService2;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService3;
import idv.hsiehpinghan.hibernateassistant.service.ElementCollectionService4;
import idv.hsiehpinghan.hibernateassistant.service.EmbeddedIdService;
import idv.hsiehpinghan.hibernateassistant.service.EmbeddedMultipleTableService;
import idv.hsiehpinghan.hibernateassistant.service.EmbeddedObjectService;
import idv.hsiehpinghan.hibernateassistant.service.JoinedInheritance_1_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.JoinedInheritance_2_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.JoinedInheritance_2_B_Service;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyCompoundIdService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyJoinTableService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyMapEmbeddableService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyMapService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToManyUnidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneJoinColumnService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneJoinTableService;
import idv.hsiehpinghan.hibernateassistant.service.ManyToOneService;
import idv.hsiehpinghan.hibernateassistant.service.MixedInheritance_1_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.MixedInheritance_2_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.MixedInheritance_2_B_Service;
import idv.hsiehpinghan.hibernateassistant.service.MultipleTableService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyDerivedEmbeddedIdService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyListService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyMapEmbeddableService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyMapKeyService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyMapService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyOrderColumnService;
import idv.hsiehpinghan.hibernateassistant.service.OneToManyUnidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneBidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneDerivedSingleKeyDerivedService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneDerivedSingleKeyMainService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneMapsIdMainService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneMapsIdMapperService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOnePkMappingService;
import idv.hsiehpinghan.hibernateassistant.service.OneToOneUnidirectionService;
import idv.hsiehpinghan.hibernateassistant.service.SingleTableInheritance_1_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.SingleTableInheritance_2_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.SingleTableInheritance_2_B_Service;
import idv.hsiehpinghan.hibernateassistant.service.TablePerClassInheritance_1_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.TablePerClassInheritance_2_A_Service;
import idv.hsiehpinghan.hibernateassistant.service.TablePerClassInheritance_2_B_Service;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class AssociationTest extends AbstractTestNGSpringContextTests {
	private final String EMBEDDABLE_STRING = "EmbeddableString";
	private final Date DATE = new Date();
	@Autowired
	private ElementCollectionService service;

	@Test
	public void elementCollection() {
		ElementCollectionEntity entity = generateElementCollectionEntity();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getElements().size(), 3);
	}

	@Test
	public void singleTableInheritance() {
		testSingleTableInheritance_1_A_Entity();
		testSingleTableInheritance_2_A_Entity();
		testSingleTableInheritance_2_B_Entity();
	}

	@Test
	public void joinedInheritance() {
		testJoinedInheritance_1_A_Entity();
		testJoinedInheritance_2_A_Entity();
		testJoinedInheritance_2_B_Entity();
	}

	@Test
	public void tablePerClassInheritance() {
		testTablePerClassInheritance_1_A_Entity();
		testTablePerClassInheritance_2_A_Entity();
		testTablePerClassInheritance_2_B_Entity();
	}

	@Test
	public void mixedInheritance() {
		testMixedInheritance_1_A_Entity();
		testMixedInheritance_2_A_Entity();
		testMixedInheritance_2_B_Entity();
	}

	@Test
	public void elementCollection1() {
		ElementCollectionService1 service = applicationContext.getBean(ElementCollectionService1.class);
		ElementCollectionContainerEntity1 entity = generateElementCollectionContainerEntity1();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionContainerEntity1 returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getElements().size(), 3);
	}

	@Test
	public void elementCollection2() {
		ElementCollectionService2 service = applicationContext.getBean(ElementCollectionService2.class);
		ElementCollectionTableContainerEntity2 entity = generateElementCollectionTableContainerEntity2();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionTableContainerEntity2 returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getElements().size(), 3);
	}

	@Test
	public void elementCollection3() {
		ElementCollectionService3 service = applicationContext.getBean(ElementCollectionService3.class);
		ElementCollectionStringMapEntity3 entity = generateElementCollectionStringMapEntity3();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionStringMapEntity3 returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getMap().size(), 3);
	}

	@Test
	public void elementCollection4() {
		ElementCollectionService4 service = applicationContext.getBean(ElementCollectionService4.class);
		ElementCollectionEnumerationMapEntity4 entity = generateElementCollectionEnumerationMapEntity4();
		service.save(entity);
		int id = entity.getId();
		ElementCollectionEnumerationMapEntity4 returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertEquals(returnEntity.getMap().size(), 3);
	}

	@Test
	public void manyToOne() {
		ManyToOneService service = applicationContext.getBean(ManyToOneService.class);
		ManyToOneManyEntity entity = generateManyToOneManyEntity();
		service.save(entity);
		int id = entity.getId();
		ManyToOneManyEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity.getOne());
	}

	@Test
	public void manyToOneJoinColumn() {
		ManyToOneJoinColumnService service = applicationContext.getBean(ManyToOneJoinColumnService.class);
		ManyToOneJoinColumnManyEntity entity = generateManyToOneJoinColumnManyEntity();
		service.save(entity);
		int id = entity.getId();
		ManyToOneJoinColumnManyEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity.getOne());
	}

	@Test
	public void manyToOneJoinTable() {
		ManyToOneJoinTableService service = applicationContext.getBean(ManyToOneJoinTableService.class);
		ManyToOneJoinTableOneEntity one = generateManyToOneJoinTableOneEntity();
		ManyToOneJoinTableManyEntity many_1 = generateManyToOneJoinTableManyEntity(one);
		service.save(many_1);
		ManyToOneJoinTableManyEntity many_2 = generateManyToOneJoinTableManyEntity(one);
		service.save(many_2);
		int id = many_2.getId();
		ManyToOneJoinTableManyEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity.getOne());
	}

	@Test
	public void oneToOneUnidirection() {
		OneToOneUnidirectionService service = applicationContext.getBean(OneToOneUnidirectionService.class);
		OneToOneUnidirectionFromEntity entity = generateOneToOneUnidirectionFromEntity();
		service.save(entity);
		int id = entity.getId();
		OneToOneUnidirectionFromEntity fromEntity = service.findOne(id);
		Assert.assertNotNull(fromEntity.getTo());
	}

	@Test
	public void oneToOneBidirection() {
		OneToOneBidirectionService service = applicationContext.getBean(OneToOneBidirectionService.class);
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
		OneToOnePkMappingService service = applicationContext.getBean(OneToOnePkMappingService.class);
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
		OneToManyBidirectionService service = applicationContext.getBean(OneToManyBidirectionService.class);
		OneToManyBidirectionOneEntity entity = generateOneToManyBidirectionOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyBidirectionOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
	}

	@Test
	public void oneToManyDerivedEmbeddedId() {
		OneToManyDerivedEmbeddedIdService service = applicationContext.getBean(OneToManyDerivedEmbeddedIdService.class);
		OneToManyDerivedEmbeddedIdOneEntity entity = generateOneToManyDerivedEmbeddedIdOneEntity();
		service.save(entity);
		OneToManyDerivedEmbeddedIdOneIdEntity id = entity.getId();
		OneToManyDerivedEmbeddedIdOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		service.delete(oneEntity);
	}

	@Test
	public void oneToManyMap() {
		OneToManyMapService service = applicationContext.getBean(OneToManyMapService.class);
		OneToManyMapOneEntity entity = generateOneToManyMapOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyMapOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
	}

	@Test
	public void oneToManyMapKey() {
		OneToManyMapKeyService service = applicationContext.getBean(OneToManyMapKeyService.class);
		OneToManyMapKeyOneEntity entity = generateOneToManyMapKeyOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyMapKeyOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		for (Entry<Integer, OneToManyMapKeyManyEntity> entry : oneEntity.getMany().entrySet()) {
			Assert.assertEquals(entry.getKey(), entry.getValue().getTargetId());
		}
	}

	@Test
	public void oneToManyMapEmbeddable() {
		OneToManyMapEmbeddableService service = applicationContext.getBean(OneToManyMapEmbeddableService.class);
		OneToManyMapEmbeddableOneEntity entity = generateOneToManyMapEmbeddableOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyMapEmbeddableOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		for (Entry<OneToManyMapEmbeddableEntity, OneToManyMapEmbeddableManyEntity> entry : oneEntity.getMany()
				.entrySet()) {
			Assert.assertNotNull(entry.getValue().getOne());
		}
	}

	@Test
	public void oneToManyOrderColumn() {
		OneToManyOrderColumnService service = applicationContext.getBean(OneToManyOrderColumnService.class);
		OneToManyOrderColumnOneEntity entity = generateOneToManyOrderColumnOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyOrderColumnOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
		for (int i = 0, size = oneEntity.getMany().size(); i < size; ++i) {
			Assert.assertEquals(oneEntity.getMany().get(i).getId(), Integer.valueOf(2 - i));
		}
	}

	@Test
	public void oneToManyList() {
		OneToManyListService service = applicationContext.getBean(OneToManyListService.class);
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
		OneToManyUnidirectionService service = applicationContext.getBean(OneToManyUnidirectionService.class);
		OneToManyUnidirectionOneEntity entity = generateOneToManyUnidirectionOneEntity();
		service.save(entity);
		int id = entity.getId();
		OneToManyUnidirectionOneEntity oneEntity = service.findOne(id);
		Assert.assertEquals(oneEntity.getMany().size(), 3);
	}

	@Test
	public void ManyToManyBidirection() {
		ManyToManyBidirectionService service = applicationContext.getBean(ManyToManyBidirectionService.class);
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
	public void ManyToManyCompoundId() {
		ManyToManyCompoundIdService service = applicationContext.getBean(ManyToManyCompoundIdService.class);
		Collection<ManyToManyCompoundIdFromEntity> entities = generateManyToManyCompoundIdFromEntities();
		for (ManyToManyCompoundIdFromEntity entity : entities) {
			service.saveOrUpdate(entity);
		}
		for (ManyToManyCompoundIdFromEntity entity : entities) {
			ManyToManyCompoundIdFromIdEntity id = entity.getId();
			ManyToManyCompoundIdFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (ManyToManyCompoundIdToEntity to : fromEntity.getTos()) {
				Assert.assertEquals(to.getFroms().size(), 3);
			}
		}
	}

	@Test
	public void ManyToManyMapEmbeddable() {
		ManyToManyMapEmbeddableService service = applicationContext.getBean(ManyToManyMapEmbeddableService.class);
		service.deleteAll();
		Collection<ManyToManyMapEmbeddableFromEntity> entities = generateManyToManyMapEmbeddableFromEntities();
		for (ManyToManyMapEmbeddableFromEntity entity : entities) {
			service.save(entity);
		}
		for (ManyToManyMapEmbeddableFromEntity entity : entities) {
			int id = entity.getId();
			ManyToManyMapEmbeddableFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (Entry<ManyToManyMapEmbeddableEntity, ManyToManyMapEmbeddableToEntity> to : fromEntity.getTos()
					.entrySet()) {
				Assert.assertEquals(to.getValue().getFroms().size(), 3);
			}
		}
	}

	@Test
	public void ManyToManyMap() {
		ManyToManyMapService service = applicationContext.getBean(ManyToManyMapService.class);
		Collection<ManyToManyMapFromEntity> entities = generateManyToManyMapFromEntities();
		for (ManyToManyMapFromEntity entity : entities) {
			service.saveOrUpdate(entity);
		}
		for (ManyToManyMapFromEntity entity : entities) {
			int id = entity.getId();
			ManyToManyMapFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
			for (Entry<String, ManyToManyMapToEntity> entry : fromEntity.getTos().entrySet()) {
				Assert.assertEquals(entry.getValue().getFroms().size(), 3);
			}
		}
	}

	@Test
	public void ManyToManyJoinTable() {
		ManyToManyJoinTableService service = applicationContext.getBean(ManyToManyJoinTableService.class);
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
	public void ManyToManyUnidirection() {
		ManyToManyUnidirectionService service = applicationContext.getBean(ManyToManyUnidirectionService.class);
		Collection<ManyToManyUnidirectionFromEntity> entities = generateManyToManyUnidirectionFromEntities();
		for (ManyToManyUnidirectionFromEntity entity : entities) {
			service.saveOrUpdate(entity);
		}
		for (ManyToManyUnidirectionFromEntity entity : entities) {
			int id = entity.getId();
			ManyToManyUnidirectionFromEntity fromEntity = service.findOne(id);
			Assert.assertEquals(fromEntity.getTos().size(), 3);
		}
	}

	@Test
	public void embeddedObject() {
		EmbeddedObjectService service = applicationContext.getBean(EmbeddedObjectService.class);
		EmbeddedObjectContainerEntity entity = generateEmbeddedObjectContainerEntity();
		service.save(entity);
		int id = entity.getId();
		EmbeddedObjectContainerEntity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getEmbedded().getEmbeddableString(), EMBEDDABLE_STRING);
	}

	@Test
	public void embeddedId() {
		EmbeddedIdService service = applicationContext.getBean(EmbeddedIdService.class);
		EmbeddedIdContainerEntity entity = generateEmbeddedIdContainerEntity();
		service.save(entity);
		EmbeddedIdEmbeddableEntity id = generateEmbeddedIdEmbeddableEntity();
		EmbeddedIdContainerEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		service.deleteAll();
	}

	@Test
	public void OneToOneDerivedSingleKey() {
		OneToOneDerivedSingleKeyMainService mainService = applicationContext
				.getBean(OneToOneDerivedSingleKeyMainService.class);
		OneToOneDerivedSingleKeyMainEntity main = generateOneToOneDerivedSingleKeyMainEntity();
		mainService.save(main);
		OneToOneDerivedSingleKeyDerivedService derivedService = applicationContext
				.getBean(OneToOneDerivedSingleKeyDerivedService.class);
		OneToOneDerivedSingleKeyDerivedEntity derived = generateOneToOneDerivedSingleKeyDerivedEntity(main);
		derivedService.save(derived);
		OneToOneDerivedSingleKeyDerivedEntity returnDerived = derivedService.findOne(main.getId());
		Assert.assertNotNull(returnDerived);
		Assert.assertNotNull(returnDerived.getMain());
	}

	@Test
	public void OneToOneMapsId() {
		OneToOneMapsIdMainService mainService = applicationContext.getBean(OneToOneMapsIdMainService.class);
		OneToOneMapsIdMainEntity main = generateOneToOneMapsIdMainEntity();
		mainService.save(main);
		OneToOneMapsIdMapperService derivedService = applicationContext.getBean(OneToOneMapsIdMapperService.class);
		OneToOneMapsIdMapperEntity derived = generateOneToOneMapsIdMapperEntity(main);
		derivedService.save(derived);
		OneToOneMapsIdMapperEntity returnMapper = derivedService.findOne(main.getId());
		Assert.assertNotNull(returnMapper);
		Assert.assertNotNull(returnMapper.getMain());
	}

	@Test
	public void multipleTable() {
		MultipleTableService multipleTableService = applicationContext.getBean(MultipleTableService.class);
		MultipleTableEntity entity = generateMultipleTableEntity();
		multipleTableService.save(entity);
		MultipleTableEntity returnEntity = multipleTableService.findOne(entity.getId());
		Assert.assertEquals(returnEntity.getName(), "name");
		Assert.assertEquals(returnEntity.getAddress(), "address");
	}

	@Test
	public void embeddedMultipleTable() {
		EmbeddedMultipleTableService EmbeddedMultipleTableService = applicationContext
				.getBean(EmbeddedMultipleTableService.class);
		EmbeddedMultipleTableContainerEntity entity = generateEmbeddedMultipleTableContainerEntity();
		EmbeddedMultipleTableService.save(entity);
		EmbeddedMultipleTableContainerEntity returnEntity = EmbeddedMultipleTableService.findOne(entity.getId());
		Assert.assertEquals(returnEntity.getName(), "name");
		Assert.assertEquals(returnEntity.getEmbeddable().getAddress(), "address");
	}

	private MultipleTableEntity generateMultipleTableEntity() {
		MultipleTableEntity entity = new MultipleTableEntity();
		entity.setName("name");
		entity.setAddress("address");
		return entity;
	}

	private EmbeddedMultipleTableContainerEntity generateEmbeddedMultipleTableContainerEntity() {
		EmbeddedMultipleTableContainerEntity entity = new EmbeddedMultipleTableContainerEntity();
		entity.setName("name");
		entity.setEmbeddable(generateEmbeddedMultipleTableEmbeddableEntity());
		return entity;
	}

	private EmbeddedMultipleTableEmbeddableEntity generateEmbeddedMultipleTableEmbeddableEntity() {
		EmbeddedMultipleTableEmbeddableEntity entity = new EmbeddedMultipleTableEmbeddableEntity();
		entity.setAddress("address");
		return entity;
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

	private OneToOneDerivedSingleKeyDerivedEntity generateOneToOneDerivedSingleKeyDerivedEntity(
			OneToOneDerivedSingleKeyMainEntity main) {
		OneToOneDerivedSingleKeyDerivedEntity entity = new OneToOneDerivedSingleKeyDerivedEntity();
		entity.setMain(main);
		return entity;
	}

	private OneToOneMapsIdMapperEntity generateOneToOneMapsIdMapperEntity(OneToOneMapsIdMainEntity main) {
		OneToOneMapsIdMapperEntity entity = new OneToOneMapsIdMapperEntity();
		entity.setId(999); // this value won't be persisted.
		entity.setMain(main);
		return entity;
	}

	private OneToOneDerivedSingleKeyMainEntity generateOneToOneDerivedSingleKeyMainEntity() {
		OneToOneDerivedSingleKeyMainEntity entity = new OneToOneDerivedSingleKeyMainEntity();
		entity.setName("name");
		return entity;
	}

	private OneToOneMapsIdMainEntity generateOneToOneMapsIdMainEntity() {
		OneToOneMapsIdMainEntity entity = new OneToOneMapsIdMainEntity();
		entity.setName("name");
		return entity;
	}

	private Collection<ManyToManyJoinTableFromEntity> generateManyToManyJoinTableFromEntities() {
		Collection<ManyToManyJoinTableFromEntity> entities = new ArrayList<ManyToManyJoinTableFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyJoinTableFromEntity(i));
		}
		return entities;
	}

	private Collection<ManyToManyUnidirectionFromEntity> generateManyToManyUnidirectionFromEntities() {
		Collection<ManyToManyUnidirectionFromEntity> entities = new ArrayList<ManyToManyUnidirectionFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyUnidirectionFromEntity(i));
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

	private Collection<ManyToManyCompoundIdFromEntity> generateManyToManyCompoundIdFromEntities() {
		Collection<ManyToManyCompoundIdFromEntity> entities = new ArrayList<ManyToManyCompoundIdFromEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyCompoundIdFromEntity(i));
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

	private ManyToManyJoinTableFromEntity generateManyToManyJoinTableFromEntity(int i) {
		ManyToManyJoinTableFromEntity from = new ManyToManyJoinTableFromEntity();
		from.setId(i);
		from.setTos(generateManyToManyJoinTableToEntities(from));
		return from;
	}

	private ManyToManyUnidirectionFromEntity generateManyToManyUnidirectionFromEntity(int i) {
		ManyToManyUnidirectionFromEntity from = new ManyToManyUnidirectionFromEntity();
		from.setId(i);
		from.setTos(generateManyToManyUnidirectionToEntities(from));
		return from;
	}

	private ManyToManyBidirectionFromEntity generateManyToManyBidirectionFromEntity(int i) {
		ManyToManyBidirectionFromEntity from = new ManyToManyBidirectionFromEntity();
		from.setId(i);
		from.setTos(generateManyToManyBidirectionToEntities(from));
		return from;
	}

	private ManyToManyCompoundIdFromEntity generateManyToManyCompoundIdFromEntity(int i) {
		ManyToManyCompoundIdFromEntity from = new ManyToManyCompoundIdFromEntity();
		from.setId(generateManyToManyCompoundIdFromIdEntity(i));
		from.setTos(generateManyToManyCompoundIdToEntities(from));
		return from;
	}

	private ManyToManyCompoundIdFromIdEntity generateManyToManyCompoundIdFromIdEntity(int i) {
		ManyToManyCompoundIdFromIdEntity id = new ManyToManyCompoundIdFromIdEntity();
		id.setFromKey1("fromKey1_" + i);
		id.setFromKey2("fromKey2_" + i);
		return id;
	}

	private ManyToManyMapEmbeddableFromEntity generateManyToManyMapEmbeddableFromEntity(int i) {
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

	private Collection<ManyToManyUnidirectionToEntity> generateManyToManyUnidirectionToEntities(
			ManyToManyUnidirectionFromEntity from) {
		Collection<ManyToManyUnidirectionToEntity> entities = new ArrayList<ManyToManyUnidirectionToEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyUnidirectionToEntity(from, i));
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

	private Collection<ManyToManyCompoundIdToEntity> generateManyToManyCompoundIdToEntities(
			ManyToManyCompoundIdFromEntity from) {
		Collection<ManyToManyCompoundIdToEntity> entities = new ArrayList<ManyToManyCompoundIdToEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateManyToManyCompoundIdToEntity(from, i));
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

	private Map<String, ManyToManyMapToEntity> generateManyToManyMapToEntityMap(ManyToManyMapFromEntity from) {
		Map<String, ManyToManyMapToEntity> entityMap = new HashMap<String, ManyToManyMapToEntity>();
		for (int i = 0; i < 3; ++i) {
			entityMap.put(String.valueOf(i), generateManyToManyMapToEntity(from, i));
		}
		return entityMap;
	}

	private ManyToManyJoinTableToEntity generateManyToManyJoinTableToEntity(ManyToManyJoinTableFromEntity from, int i) {
		ManyToManyJoinTableToEntity to = new ManyToManyJoinTableToEntity();
		to.setId(i);
		to.addFrom(from);
		return to;
	}

	private ManyToManyUnidirectionToEntity generateManyToManyUnidirectionToEntity(ManyToManyUnidirectionFromEntity from,
			int i) {
		ManyToManyUnidirectionToEntity to = new ManyToManyUnidirectionToEntity();
		to.setId(i);
		return to;
	}

	private ManyToManyBidirectionToEntity generateManyToManyBidirectionToEntity(ManyToManyBidirectionFromEntity from,
			int i) {
		ManyToManyBidirectionToEntity to = new ManyToManyBidirectionToEntity();
		to.setId(i);
		to.addFrom(from);
		return to;
	}

	private ManyToManyCompoundIdToEntity generateManyToManyCompoundIdToEntity(ManyToManyCompoundIdFromEntity from,
			int i) {
		ManyToManyCompoundIdToEntity to = new ManyToManyCompoundIdToEntity();
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

	private ManyToManyMapEmbeddableEmbeddedEntity generateManyToManyMapEmbeddableEmbeddedEntity(int i) {
		ManyToManyMapEmbeddableEmbeddedEntity embeddable = new ManyToManyMapEmbeddableEmbeddedEntity();
		embeddable.setFirstName("firstName_" + i);
		embeddable.setLastName("lastName_" + i);
		return embeddable;
	}

	private ManyToManyMapToEntity generateManyToManyMapToEntity(ManyToManyMapFromEntity from, int i) {
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

	private OneToManyDerivedEmbeddedIdOneEntity generateOneToManyDerivedEmbeddedIdOneEntity() {
		OneToManyDerivedEmbeddedIdOneEntity one = new OneToManyDerivedEmbeddedIdOneEntity();
		one.setId(generateOneToManyDerivedEmbeddedIdOneIdEntity());
		one.setMany(generateOneToManyDerivedEmbeddedIdManyEntities(one));
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

	private Collection<OneToManyDerivedEmbeddedIdManyEntity> generateOneToManyDerivedEmbeddedIdManyEntities(
			OneToManyDerivedEmbeddedIdOneEntity one) {
		Collection<OneToManyDerivedEmbeddedIdManyEntity> entities = new ArrayList<OneToManyDerivedEmbeddedIdManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entities.add(generateOneToManyDerivedEmbeddedIdManyEntity(one, i));
		}
		return entities;
	}

	private OneToManyDerivedEmbeddedIdOneIdEntity generateOneToManyDerivedEmbeddedIdOneIdEntity() {
		OneToManyDerivedEmbeddedIdOneIdEntity entity = new OneToManyDerivedEmbeddedIdOneIdEntity();
		entity.setOneKey1("oneKey1");
		entity.setOneKey2("oneKey2");
		return entity;
	}

	private Map<String, OneToManyMapManyEntity> generateOneToManyMapManyEntityMap(OneToManyMapOneEntity one) {
		Map<String, OneToManyMapManyEntity> entityMap = new HashMap<String, OneToManyMapManyEntity>();
		for (int i = 0; i < 3; ++i) {
			entityMap.put("key_" + i, generateOneToManyMapManyEntity(one));
		}
		return entityMap;
	}

	private Map<Integer, OneToManyMapKeyManyEntity> generateOneToManyMapKeyManyEntityMap(OneToManyMapKeyOneEntity one) {
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
			entityMapEmbeddable.put(embeddable, generateOneToManyMapEmbeddableManyEntity(one, i));
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

	private List<OneToManyListManyEntity> generateOneToManyListManyEntities(OneToManyListOneEntity one) {
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

	private OneToManyBidirectionManyEntity generateOneToManyBidirectionManyEntity(OneToManyBidirectionOneEntity one) {
		OneToManyBidirectionManyEntity many = new OneToManyBidirectionManyEntity();
		many.setOne(one);
		return many;
	}

	private OneToManyDerivedEmbeddedIdManyEntity generateOneToManyDerivedEmbeddedIdManyEntity(
			OneToManyDerivedEmbeddedIdOneEntity one, int i) {
		OneToManyDerivedEmbeddedIdManyEntity many = new OneToManyDerivedEmbeddedIdManyEntity();
		many.setId(generateOneToManyDerivedEmbeddedIdManyIdEntity(one, i));
		many.setOne(one);
		return many;
	}

	private OneToManyDerivedEmbeddedIdManyIdEntity generateOneToManyDerivedEmbeddedIdManyIdEntity(
			OneToManyDerivedEmbeddedIdOneEntity one, int i) {
		OneToManyDerivedEmbeddedIdManyIdEntity id = new OneToManyDerivedEmbeddedIdManyIdEntity();
		id.setManyKey("manyKey_" + i);
		id.setOneKey(one.getId());
		return id;
	}

	private OneToManyMapManyEntity generateOneToManyMapManyEntity(OneToManyMapOneEntity one) {
		OneToManyMapManyEntity many = new OneToManyMapManyEntity();
		many.setOne(one);
		return many;
	}

	private OneToManyMapKeyManyEntity generateOneToManyMapKeyManyEntity(OneToManyMapKeyOneEntity one, int i) {
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

	private OneToManyMapEmbeddableEmbeddedEntity generateOneToManyMapEmbeddableEmbeddedEntity(int i) {
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

	private OneToManyOrderColumnManyEntity generateOneToManyOrderColumnManyEntity(OneToManyOrderColumnOneEntity one,
			int i) {
		OneToManyOrderColumnManyEntity many = new OneToManyOrderColumnManyEntity();
		many.setId(2 - i);
		many.setOne(one);
		return many;
	}

	private OneToManyListManyEntity generateOneToManyListManyEntity(OneToManyListOneEntity one, int i) {
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

	private OneToOnePkMappingToEntity generateOneToOnePkMappingToEntity(OneToOnePkMappingFromEntity from) {
		OneToOnePkMappingToEntity to = new OneToOnePkMappingToEntity();
		to.setFrom(from);
		return to;
	}

	private OneToOneBidirectionFromEntity generateOneToOneBidirectionFromEntity() {
		OneToOneBidirectionFromEntity from = new OneToOneBidirectionFromEntity();
		from.setTo(generateOneToOneBidirectionToEntity(from));
		return from;
	}

	private OneToOneBidirectionToEntity generateOneToOneBidirectionToEntity(OneToOneBidirectionFromEntity from) {
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

	private ManyToOneJoinTableManyEntity generateManyToOneJoinTableManyEntity(ManyToOneJoinTableOneEntity one) {
		ManyToOneJoinTableManyEntity entity = new ManyToOneJoinTableManyEntity();
		entity.setOne(one);
		return entity;
	}

	private ManyToOneJoinColumnOneEntity generateManyToOneJoinColumnOneEntity() {
		ManyToOneJoinColumnOneEntity entity = new ManyToOneJoinColumnOneEntity();
		return entity;
	}

	private ManyToOneJoinTableOneEntity generateManyToOneJoinTableOneEntity() {
		ManyToOneJoinTableOneEntity entity = new ManyToOneJoinTableOneEntity();
		entity.setId(1);
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

	private void testSingleTableInheritance_1_A_Entity() {
		SingleTableInheritance_1_A_Service service = applicationContext
				.getBean(SingleTableInheritance_1_A_Service.class);
		SingleTableInheritance_1_A_Entity entity = generateSingleTableInheritance_1_A_Entity();
		service.save(entity);
		int id = entity.getId();
		SingleTableInheritance_1_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getA1(), "a1");
	}

	private void testSingleTableInheritance_2_A_Entity() {
		SingleTableInheritance_2_A_Service service = applicationContext
				.getBean(SingleTableInheritance_2_A_Service.class);
		SingleTableInheritance_2_A_Entity entity = generateSingleTableInheritance_2_A_Entity();
		service.save(entity);
		int id = entity.getId();
		SingleTableInheritance_2_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getA2(), "a2");
	}

	private void testSingleTableInheritance_2_B_Entity() {
		SingleTableInheritance_2_B_Service service = applicationContext
				.getBean(SingleTableInheritance_2_B_Service.class);
		SingleTableInheritance_2_B_Entity entity = generateSingleTableInheritance_2_B_Entity();
		service.save(entity);
		int id = entity.getId();
		SingleTableInheritance_2_B_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getB2(), "b2");
	}

	private SingleTableInheritance_1_A_Entity generateSingleTableInheritance_1_A_Entity() {
		SingleTableInheritance_1_A_Entity entity = new SingleTableInheritance_1_A_Entity();
		entity.setDate(DATE);
		entity.setA1("a1");
		return entity;
	}

	private SingleTableInheritance_2_A_Entity generateSingleTableInheritance_2_A_Entity() {
		SingleTableInheritance_2_A_Entity entity = new SingleTableInheritance_2_A_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setA2("a2");
		return entity;
	}

	private SingleTableInheritance_2_B_Entity generateSingleTableInheritance_2_B_Entity() {
		SingleTableInheritance_2_B_Entity entity = new SingleTableInheritance_2_B_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setB2("b2");
		return entity;
	}

	private void testJoinedInheritance_1_A_Entity() {
		JoinedInheritance_1_A_Service service = applicationContext.getBean(JoinedInheritance_1_A_Service.class);
		JoinedInheritance_1_A_Entity entity = generateJoinedInheritance_1_A_Entity();
		service.save(entity);
		int id = entity.getId();
		JoinedInheritance_1_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getA1(), "a1");
	}

	private void testJoinedInheritance_2_A_Entity() {
		JoinedInheritance_2_A_Service service = applicationContext.getBean(JoinedInheritance_2_A_Service.class);
		JoinedInheritance_2_A_Entity entity = generateJoinedInheritance_2_A_Entity();
		service.save(entity);
		int id = entity.getId();
		JoinedInheritance_2_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getA2(), "a2");
	}

	private void testJoinedInheritance_2_B_Entity() {
		JoinedInheritance_2_B_Service service = applicationContext.getBean(JoinedInheritance_2_B_Service.class);
		JoinedInheritance_2_B_Entity entity = generateJoinedInheritance_2_B_Entity();
		service.save(entity);
		int id = entity.getId();
		JoinedInheritance_2_B_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getB2(), "b2");
	}

	private JoinedInheritance_1_A_Entity generateJoinedInheritance_1_A_Entity() {
		JoinedInheritance_1_A_Entity entity = new JoinedInheritance_1_A_Entity();
		entity.setDate(DATE);
		entity.setA1("a1");
		return entity;
	}

	private JoinedInheritance_2_A_Entity generateJoinedInheritance_2_A_Entity() {
		JoinedInheritance_2_A_Entity entity = new JoinedInheritance_2_A_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setA2("a2");
		return entity;
	}

	private JoinedInheritance_2_B_Entity generateJoinedInheritance_2_B_Entity() {
		JoinedInheritance_2_B_Entity entity = new JoinedInheritance_2_B_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setB2("b2");
		return entity;
	}

	private void testTablePerClassInheritance_1_A_Entity() {
		TablePerClassInheritance_1_A_Service service = applicationContext
				.getBean(TablePerClassInheritance_1_A_Service.class);
		TablePerClassInheritance_1_A_Entity entity = generateTablePerClassInheritance_1_A_Entity();
		service.save(entity);
		int id = entity.getId();
		TablePerClassInheritance_1_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getA1(), "a1");
	}

	private void testTablePerClassInheritance_2_A_Entity() {
		TablePerClassInheritance_2_A_Service service = applicationContext
				.getBean(TablePerClassInheritance_2_A_Service.class);
		TablePerClassInheritance_2_A_Entity entity = generateTablePerClassInheritance_2_A_Entity();
		service.save(entity);
		int id = entity.getId();
		TablePerClassInheritance_2_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getA2(), "a2");
	}

	private void testTablePerClassInheritance_2_B_Entity() {
		TablePerClassInheritance_2_B_Service service = applicationContext
				.getBean(TablePerClassInheritance_2_B_Service.class);
		TablePerClassInheritance_2_B_Entity entity = generateTablePerClassInheritance_2_B_Entity();
		service.save(entity);
		int id = entity.getId();
		TablePerClassInheritance_2_B_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getB2(), "b2");
	}

	private TablePerClassInheritance_1_A_Entity generateTablePerClassInheritance_1_A_Entity() {
		TablePerClassInheritance_1_A_Entity entity = new TablePerClassInheritance_1_A_Entity();
		entity.setDate(DATE);
		entity.setA1("a1");
		return entity;
	}

	private TablePerClassInheritance_2_A_Entity generateTablePerClassInheritance_2_A_Entity() {
		TablePerClassInheritance_2_A_Entity entity = new TablePerClassInheritance_2_A_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setA2("a2");
		return entity;
	}

	private TablePerClassInheritance_2_B_Entity generateTablePerClassInheritance_2_B_Entity() {
		TablePerClassInheritance_2_B_Entity entity = new TablePerClassInheritance_2_B_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setB2("b2");
		return entity;
	}

	private void testMixedInheritance_1_A_Entity() {
		MixedInheritance_1_A_Service service = applicationContext.getBean(MixedInheritance_1_A_Service.class);
		MixedInheritance_1_A_Entity entity = generateMixedInheritance_1_A_Entity();
		service.save(entity);
		int id = entity.getId();
		MixedInheritance_1_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getA1(), "a1");
	}

	private void testMixedInheritance_2_A_Entity() {
		MixedInheritance_2_A_Service service = applicationContext.getBean(MixedInheritance_2_A_Service.class);
		MixedInheritance_2_A_Entity entity = generateMixedInheritance_2_A_Entity();
		service.save(entity);
		int id = entity.getId();
		MixedInheritance_2_A_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getA2(), "a2");
	}

	private void testMixedInheritance_2_B_Entity() {
		MixedInheritance_2_B_Service service = applicationContext.getBean(MixedInheritance_2_B_Service.class);
		MixedInheritance_2_B_Entity entity = generateMixedInheritance_2_B_Entity();
		service.save(entity);
		int id = entity.getId();
		MixedInheritance_2_B_Entity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getDate().getTime(), DATE.getTime());
		Assert.assertEquals(returnEntity.getB1(), "b1");
		Assert.assertEquals(returnEntity.getB2(), "b2");
	}

	private MixedInheritance_1_A_Entity generateMixedInheritance_1_A_Entity() {
		MixedInheritance_1_A_Entity entity = new MixedInheritance_1_A_Entity();
		entity.setDate(DATE);
		entity.setA1("a1");
		return entity;
	}

	private MixedInheritance_2_A_Entity generateMixedInheritance_2_A_Entity() {
		MixedInheritance_2_A_Entity entity = new MixedInheritance_2_A_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setA2("a2");
		return entity;
	}

	private MixedInheritance_2_B_Entity generateMixedInheritance_2_B_Entity() {
		MixedInheritance_2_B_Entity entity = new MixedInheritance_2_B_Entity();
		entity.setDate(DATE);
		entity.setB1("b1");
		entity.setB2("b2");
		return entity;
	}
}
