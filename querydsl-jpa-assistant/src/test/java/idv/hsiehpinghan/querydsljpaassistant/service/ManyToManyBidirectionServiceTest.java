package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.ManyToManyBidirectionToEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionFromEntity;
import idv.hsiehpinghan.querydsljpaassistant.entity.QManyToManyBidirectionToEntity;
import idv.hsiehpinghan.querydsljpaassistant.suit.TestngSuitSetting;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

public class ManyToManyBidirectionServiceTest {
	private final String FROM_NAME = "from_name";
	private final String TO_NAME = "to_name";
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
		service.save(entity);
		id = entity.getId();
		Assert.assertTrue(service.exists(id));
	}

	@Test(dependsOnMethods = { "save" })
	public void where() {
		QManyToManyBidirectionFromEntity qFrom = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
		Expression<?>[] expressions = new Expression<?>[] { qFrom.id,
				qFrom.name };
		List<Tuple> tuples = service.where(id, expressions);
		Assert.assertTrue(tuples.size() > 0);
		for (Tuple tuple : tuples) {
			Assert.assertEquals(tuple.get(expressions[0]), id);
			Assert.assertEquals(tuple.get(expressions[1]), FROM_NAME);
		}
	}

	@Test(dependsOnMethods = { "save" })
	public void leftJoin() {
		QManyToManyBidirectionFromEntity qFrom = QManyToManyBidirectionFromEntity.manyToManyBidirectionFromEntity;
		QManyToManyBidirectionToEntity qTo = QManyToManyBidirectionToEntity.manyToManyBidirectionToEntity;
		Expression<?>[] expressions = new Expression<?>[] { qFrom.id,
				qFrom.name, qTo.name };
		List<Tuple> tuples = service.leftJoin(id, expressions);
		Assert.assertEquals(tuples.size(), 3);
		for (Tuple tuple : tuples) {
			Assert.assertEquals(tuple.get(expressions[0]), id);
			Assert.assertEquals(tuple.get(expressions[1]), FROM_NAME);
			Assert.assertEquals(tuple.get(expressions[2]), TO_NAME);
		}
	}

	private ManyToManyBidirectionFromEntity generateManyToManyBidirectionFromEntity() {
		ManyToManyBidirectionFromEntity from = new ManyToManyBidirectionFromEntity();
		from.setName(FROM_NAME);
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
		to.setId(Calendar.getInstance().getTimeInMillis() + i);
		to.setName(TO_NAME);
		to.addFrom(from);
		return to;
	}
}
