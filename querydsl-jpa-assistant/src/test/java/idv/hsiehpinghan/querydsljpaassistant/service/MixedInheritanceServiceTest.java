package idv.hsiehpinghan.querydsljpaassistant.service;

import idv.hsiehpinghan.querydsljpaassistant.entity.MixedInheritance_1_A_Entity;
import idv.hsiehpinghan.querydsljpaassistant.entity.MixedInheritance_2_A_Entity;
import idv.hsiehpinghan.querydsljpaassistant.entity.MixedInheritance_2_B_Entity;
import idv.hsiehpinghan.querydsljpaassistant.suit.TestngSuitSetting;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MixedInheritanceServiceTest {
	private final String A1 = "a1";
	private final String A2 = "a2";
	private final String B2 = "b2";
	private MixedInheritanceService service;
	private MixedInheritance_1_A_Entity mixedInheritance_1_A_Entity;
	private MixedInheritance_2_A_Entity mixedInheritance_2_A_Entity;
	private MixedInheritance_2_B_Entity mixedInheritance_2_B_Entity;

	@BeforeClass
	public void beforeClass() {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		service = applicationContext.getBean(MixedInheritanceService.class);
		mixedInheritance_1_A_Entity = generateMixedInheritance_1_A_Entity();
		mixedInheritance_2_A_Entity = generateMixedInheritance_2_A_Entity();
		mixedInheritance_2_B_Entity = generateMixedInheritance_2_B_Entity();
	}

	@Test
	public void save() {
		Collection<Object> entities = new HashSet<Object>();
		entities.add(mixedInheritance_1_A_Entity);
		entities.add(mixedInheritance_2_A_Entity);
		entities.add(mixedInheritance_2_B_Entity);
		service.save(entities);
	}

	@Test(dependsOnMethods = { "save" })
	public void findOne() {
		MixedInheritance_1_A_Entity entity_1_A = service.findOne(
				mixedInheritance_1_A_Entity.getId(),
				MixedInheritance_1_A_Entity.class);
		Assert.assertEquals(entity_1_A.getA1(), A1);
		MixedInheritance_2_A_Entity entity_2_A = service.findOne(
				mixedInheritance_2_A_Entity.getId(),
				MixedInheritance_2_A_Entity.class);
		Assert.assertEquals(entity_2_A.getA2(), A2);
		MixedInheritance_2_B_Entity entity_2_B = service.findOne(
				mixedInheritance_2_B_Entity.getId(),
				MixedInheritance_2_B_Entity.class);
		Assert.assertEquals(entity_2_B.getB2(), B2);
	}

	@Test(dependsOnMethods = { "save" })
	public void findA1ById() {
		String a1 = service.findA1ById(mixedInheritance_1_A_Entity.getId());
		Assert.assertEquals(a1, A1);
	}

	@Test(dependsOnMethods = { "save" })
	public void findA2ById() {
		String a2 = service.findA2ById(mixedInheritance_2_A_Entity.getId());
		Assert.assertEquals(a2, A2);
	}

	@Test(dependsOnMethods = { "save" })
	public void findB2ById() {
		String b2 = service.findB2ById(mixedInheritance_2_B_Entity.getId());
		Assert.assertEquals(b2, B2);
	}

	private MixedInheritance_1_A_Entity generateMixedInheritance_1_A_Entity() {
		MixedInheritance_1_A_Entity entity = new MixedInheritance_1_A_Entity();
		entity.setA1(A1);
		return entity;
	}

	private MixedInheritance_2_A_Entity generateMixedInheritance_2_A_Entity() {
		MixedInheritance_2_A_Entity entity = new MixedInheritance_2_A_Entity();
		entity.setA2(A2);
		return entity;
	}

	private MixedInheritance_2_B_Entity generateMixedInheritance_2_B_Entity() {
		MixedInheritance_2_B_Entity entity = new MixedInheritance_2_B_Entity();
		entity.setB2(B2);
		return entity;
	}

}
