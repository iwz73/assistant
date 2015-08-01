package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.querydsljpaassistant.suit.TestngSuitSetting;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mysema.query.Tuple;

public class ManyToManyBidirectionServiceTest {
	private final Integer FROM_ID = 0;
	private Integer id;
	private ManyToManyBidirectionService service;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext
				.getBean(ManyToManyBidirectionService.class);
	}

	@Test
	public void save() {
		ManyToManyBidirectionFromEntity entity = generateManyToManyBidirectionFromEntity();
		ManyToManyBidirectionFromEntity returnEntity = service.save(entity);
		id = returnEntity.getId();
		
		
		System.err.println(id);
		
		Assert.assertTrue(service.exists(id));
	}

	@Test(dependsOnMethods = { "save" })
//	@Test
	public void where() throws Exception {
		
		id = 1;
		
		List<Tuple> tuples = service.where(id);
		System.err.println(tuples);
	}

	private ManyToManyBidirectionFromEntity generateManyToManyBidirectionFromEntity() {
		ManyToManyBidirectionFromEntity from = new ManyToManyBidirectionFromEntity();
		from.setId(FROM_ID);
		from.setName("from_name");
		from.setTos(generateManyToManyBidirectionToEntities(from));
		return from;
	}

	private Collection<ManyToManyBidirectionToEntity> generateManyToManyBidirectionToEntities(
			ManyToManyBidirectionFromEntity from) {
		Collection<ManyToManyBidirectionToEntity> tos = new HashSet<ManyToManyBidirectionToEntity>();
		for (int i = 0; i < 3; ++i) {
			tos.add(generateManyToManyBidirectionToEntity(i, from));
		}
		return tos;
	}

	private ManyToManyBidirectionToEntity generateManyToManyBidirectionToEntity(
			int i, ManyToManyBidirectionFromEntity from) {
		ManyToManyBidirectionToEntity to = new ManyToManyBidirectionToEntity();
		to.setId(i);
		to.setName("to_name");
		to.addFrom(from);
		return to;
	}
}
