package idv.hsiehpinghan.springdatajpaassistatnt.test;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.service.ManyToManyBidirectionFromService;
import idv.hsiehpinghan.springdatajpaassistatnt.service.ManyToManyBidirectionToService;
import idv.hsiehpinghan.springdatajpaassistatnt.suit.TestngSuitSetting;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManyToManyBidirectionTest {
	private final String FROM_NAME = "from_name";
	private final String TO_NAME = "to_name";
	private ApplicationContext applicationContext;
	private ManyToManyBidirectionFromService fromService;
	private ManyToManyBidirectionToService toService;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		fromService = applicationContext
				.getBean(ManyToManyBidirectionFromService.class);
		toService = applicationContext
				.getBean(ManyToManyBidirectionToService.class);
	}

	@Test
	public void updateCollection() throws InterruptedException {
		ManyToManyBidirectionFromEntity from = saveAndFindManyToManyBidirectionFromEntity();
		Assert.assertEquals(from.getTos().size(), 3);
		ManyToManyBidirectionFromEntity updatedFrom = updateAndFindManyToManyBidirectionFromEntity(from);
		Assert.assertEquals(updatedFrom.getTos().size(), 2);
	}

	@Test
	public void addCollection() throws InterruptedException {
		ManyToManyBidirectionFromEntity from = saveAndFindManyToManyBidirectionFromEntity();
		Assert.assertEquals(from.getTos().size(), 3);
		ManyToManyBidirectionFromEntity updatedFrom = addAndFindManyToManyBidirectionFromEntity(from);
		Assert.assertEquals(updatedFrom.getTos().size(), 5);
	}

	@Test
	public void toEntity() throws InterruptedException {
		ManyToManyBidirectionFromEntity from = saveAndFindManyToManyBidirectionFromEntity();
		for (ManyToManyBidirectionToEntity to : from.getTos()) {
			ManyToManyBidirectionToEntity returnTo = toService.findOne(to
					.getId());
			Assert.assertTrue(returnTo.getFroms().contains(from));
		}
	}
	
	@Test
	public void findOneByQueryDsl() throws InterruptedException {
		ManyToManyBidirectionFromEntity from = generateManyToManyBidirectionFromEntity();
		fromService.save(from);
		ManyToManyBidirectionFromEntity returnFrom = fromService.findOneByQueryDsl(from.getId());
		Assert.assertEquals(returnFrom.getTos().size(), 3);
	}

	private ManyToManyBidirectionFromEntity addAndFindManyToManyBidirectionFromEntity(
			ManyToManyBidirectionFromEntity entity) throws InterruptedException {
		entity.getTos().addAll(
				generateManyToManyBidirectionToEntities(entity, 2));
		fromService.save(entity);
		return fromService.findOne(entity.getId());
	}

	private ManyToManyBidirectionFromEntity saveAndFindManyToManyBidirectionFromEntity()
			throws InterruptedException {
		ManyToManyBidirectionFromEntity from = generateManyToManyBidirectionFromEntity();
		fromService.save(from);
		return fromService.findOne(from.getId());
	}

	private ManyToManyBidirectionFromEntity updateAndFindManyToManyBidirectionFromEntity(
			ManyToManyBidirectionFromEntity entity) throws InterruptedException {
		entity.setTos(generateManyToManyBidirectionToEntities(entity, 2));
		fromService.save(entity);
		return fromService.findOne(entity.getId());
	}

	private ManyToManyBidirectionFromEntity generateManyToManyBidirectionFromEntity()
			throws InterruptedException {
		ManyToManyBidirectionFromEntity from = new ManyToManyBidirectionFromEntity();
		from.setName(FROM_NAME);
		from.setTos(generateManyToManyBidirectionToEntities(from, 3));
		return from;
	}

	private Collection<ManyToManyBidirectionToEntity> generateManyToManyBidirectionToEntities(
			ManyToManyBidirectionFromEntity from, int size)
			throws InterruptedException {
		Collection<ManyToManyBidirectionToEntity> tos = new HashSet<ManyToManyBidirectionToEntity>();
		for (int i = 0; i < size; ++i) {
			tos.add(generateManyToManyBidirectionToEntity(i, from));
		}
		return tos;
	}

	private ManyToManyBidirectionToEntity generateManyToManyBidirectionToEntity(
			int i, ManyToManyBidirectionFromEntity from) {
		ManyToManyBidirectionToEntity to = new ManyToManyBidirectionToEntity();
		to.setId(Calendar.getInstance().getTimeInMillis() + i);
		to.setName(TO_NAME);
		to.addFrom(from);
		return to;
	}

}
