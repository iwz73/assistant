package idv.hsiehpinghan.springdatajpaassistatnt.test;

import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionNoCascadeFromEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.entity.ManyToManyBidirectionNoCascadeToEntity;
import idv.hsiehpinghan.springdatajpaassistatnt.service.ManyToManyBidirectionNoCascadeFromService;
import idv.hsiehpinghan.springdatajpaassistatnt.service.ManyToManyBidirectionNoCascadeToService;
import idv.hsiehpinghan.springdatajpaassistatnt.suit.TestngSuitSetting;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ManyToManyBidirectionNoCascadeTest {
	private final String FROM_NAME = "from_name";
	private final String TO_NAME = "to_name";
	private ApplicationContext applicationContext;
	private ManyToManyBidirectionNoCascadeFromService fromService;
	private ManyToManyBidirectionNoCascadeToService toService;

	@BeforeClass
	public void beforeClass() throws Exception {
		applicationContext = TestngSuitSetting.getApplicationContext();
		fromService = applicationContext
				.getBean(ManyToManyBidirectionNoCascadeFromService.class);
		toService = applicationContext
				.getBean(ManyToManyBidirectionNoCascadeToService.class);
	}

	@Test
	public void save() {
		ManyToManyBidirectionNoCascadeFromEntity from = saveAndFindManyToManyBidirectionNoCascadeFromEntity();
		Assert.assertEquals(from.getTos().size(), 3);
	}

	@Test
	public void updateCollection() {
		ManyToManyBidirectionNoCascadeFromEntity from = saveAndFindManyToManyBidirectionNoCascadeFromEntity();
		Assert.assertEquals(from.getTos().size(), 3);

		for (ManyToManyBidirectionNoCascadeToEntity to : from.getTos()) {
			System.err.println(to.getId());
		}

		ManyToManyBidirectionNoCascadeFromEntity updatedFrom = updateAndFindManyToManyBidirectionNoCascadeFromEntity(from);
		Assert.assertEquals(updatedFrom.getTos().size(), 2);
	}

	@Test
	public void addCollection() {
		ManyToManyBidirectionNoCascadeFromEntity from = saveAndFindManyToManyBidirectionNoCascadeFromEntity();
		Assert.assertEquals(from.getTos().size(), 3);
		ManyToManyBidirectionNoCascadeFromEntity updatedFrom = addAndFindManyToManyBidirectionNoCascadeFromEntity(from);
		Assert.assertEquals(updatedFrom.getTos().size(), 5);
	}

	private ManyToManyBidirectionNoCascadeFromEntity addAndFindManyToManyBidirectionNoCascadeFromEntity(
			ManyToManyBidirectionNoCascadeFromEntity entity) {
		Collection<ManyToManyBidirectionNoCascadeToEntity> tos = generateManyToManyBidirectionNoCascadeToEntities(
				entity, 2);
		toService.save(tos);
		entity.getTos().addAll(tos);
		fromService.save(entity);
		return fromService.findOne(entity.getId());
	}

	private ManyToManyBidirectionNoCascadeFromEntity saveAndFindManyToManyBidirectionNoCascadeFromEntity() {
		ManyToManyBidirectionNoCascadeFromEntity from = generateManyToManyBidirectionNoCascadeFromEntity();
		fromService.save(from);
		return fromService.findOne(from.getId());
	}

	private ManyToManyBidirectionNoCascadeFromEntity updateAndFindManyToManyBidirectionNoCascadeFromEntity(
			ManyToManyBidirectionNoCascadeFromEntity entity) {
		Collection<ManyToManyBidirectionNoCascadeToEntity> tos = generateManyToManyBidirectionNoCascadeToEntities(
				entity, 2);
		toService.save(tos);
		entity.setTos(tos);
		fromService.save(entity);
		return fromService.findOne(entity.getId());
	}

	private ManyToManyBidirectionNoCascadeFromEntity generateManyToManyBidirectionNoCascadeFromEntity() {
		ManyToManyBidirectionNoCascadeFromEntity from = new ManyToManyBidirectionNoCascadeFromEntity();
		from.setName(FROM_NAME);
		Collection<ManyToManyBidirectionNoCascadeToEntity> tos = generateManyToManyBidirectionNoCascadeToEntities(
				from, 3);
		toService.save(tos);
		from.setTos(tos);
		return from;
	}

	private Collection<ManyToManyBidirectionNoCascadeToEntity> generateManyToManyBidirectionNoCascadeToEntities(
			ManyToManyBidirectionNoCascadeFromEntity from, int size) {
		Collection<ManyToManyBidirectionNoCascadeToEntity> tos = new HashSet<ManyToManyBidirectionNoCascadeToEntity>();
		for (int i = 0; i < size; ++i) {
			tos.add(generateManyToManyBidirectionNoCascadeToEntity(i, from));
		}
		return tos;
	}

	private ManyToManyBidirectionNoCascadeToEntity generateManyToManyBidirectionNoCascadeToEntity(
			int i, ManyToManyBidirectionNoCascadeFromEntity from) {
		ManyToManyBidirectionNoCascadeToEntity to = new ManyToManyBidirectionNoCascadeToEntity();
		to.setId(Calendar.getInstance().getTimeInMillis() + i);
		to.setName(TO_NAME);
		to.addFrom(from);
		return to;
	}

}
