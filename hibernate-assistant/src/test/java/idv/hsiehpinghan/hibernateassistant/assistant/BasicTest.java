package idv.hsiehpinghan.hibernateassistant.assistant;

import idv.hsiehpinghan.hibernateassistant.entity.AttributeConverterEntity;
import idv.hsiehpinghan.hibernateassistant.entity.EnumerationEntity;
import idv.hsiehpinghan.hibernateassistant.entity.LobEntity;
import idv.hsiehpinghan.hibernateassistant.entity.SequenceGeneratorEntity;
import idv.hsiehpinghan.hibernateassistant.entity.TableGeneratorEntity;
import idv.hsiehpinghan.hibernateassistant.entity.TemporalEntity;
import idv.hsiehpinghan.hibernateassistant.enumeration.Enumeration;
import idv.hsiehpinghan.hibernateassistant.service.AttributeConverterService;
import idv.hsiehpinghan.hibernateassistant.service.EnumerationService;
import idv.hsiehpinghan.hibernateassistant.service.LobService;
import idv.hsiehpinghan.hibernateassistant.service.SequenceGeneratorService;
import idv.hsiehpinghan.hibernateassistant.service.TableGeneratorService;
import idv.hsiehpinghan.hibernateassistant.service.TemporalService;
import idv.hsiehpinghan.hibernateassistant.suit.TestngSuitSetting;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BasicTest {
	private static final String YYYYMMDD = "yyyyMMdd";
	private ApplicationContext applicationContext;
	private Date date = new Date();
	private Calendar calendar = Calendar.getInstance();

	@BeforeClass
	public void beforeClass() throws IOException {
		applicationContext = TestngSuitSetting.getApplicationContext();
	}

	// @Test
	public void lob() {
		LobService service = applicationContext.getBean(LobService.class);
		LobEntity entity = generateLobEntity();
		service.save(entity);
		int id = entity.getId();
		LobEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity);
		Assert.assertNotNull(returnEntity.getLob());
		Assert.assertNotNull(returnEntity.getLob().length > 0);
	}

	// @Test
	public void enumeration() {
		EnumerationService service = applicationContext
				.getBean(EnumerationService.class);
		EnumerationEntity entity = generateEnumerationEntity();
		service.save(entity);
		int id = entity.getId();
		EnumerationEntity returnEntity = service.findOne(id);
		Assert.assertNotNull(returnEntity.getEnumeration().equals(
				Enumeration.ENUM_3));
	}

	// @Test
	public void temporal() {
		TemporalService service = applicationContext
				.getBean(TemporalService.class);
		TemporalEntity entity = generateTemporalEntity();
		service.save(entity);
		int id = entity.getId();
		TemporalEntity returnEntity = service.findOne(id);
		Assert.assertEquals(getYyyyMmDd(returnEntity.getDate()),
				getYyyyMmDd(date));
		Assert.assertEquals(getYyyyMmDd(returnEntity.getCalendar()),
				getYyyyMmDd(calendar));
	}

	// @Test
	public void tableGenerator() {
		TableGeneratorService service = applicationContext
				.getBean(TableGeneratorService.class);
		TableGeneratorEntity entity = generateTableGeneratorEntity();
		service.save(entity);
		int id = entity.getId();
		TableGeneratorEntity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getId().intValue(), 1);
		service.dropTable();
	}

	// @Test
	public void sequenceGenerator() {
		SequenceGeneratorService service = applicationContext
				.getBean(SequenceGeneratorService.class);
		SequenceGeneratorEntity entity = generateSequenceGeneratorEntity();
		service.save(entity);
		int id = entity.getId();
		SequenceGeneratorEntity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getId().intValue(), 1);
		service.dropTable();
	}

	// @Test
	public void attributeConverter() {
		final String name = "name";
		AttributeConverterService service = applicationContext
				.getBean(AttributeConverterService.class);
		AttributeConverterEntity entity = generateAttributeConverterEntity(name);
		service.save(entity);
		int id = entity.getId();
		AttributeConverterEntity returnEntity = service.findOne(id);
		Assert.assertEquals(returnEntity.getName(), "name".toUpperCase());
	}

	private SequenceGeneratorEntity generateSequenceGeneratorEntity() {
		SequenceGeneratorEntity entity = new SequenceGeneratorEntity();
		return entity;
	}

	private AttributeConverterEntity generateAttributeConverterEntity(
			String name) {
		AttributeConverterEntity entity = new AttributeConverterEntity();
		entity.setName(name);
		return entity;
	}

	private TableGeneratorEntity generateTableGeneratorEntity() {
		TableGeneratorEntity entity = new TableGeneratorEntity();
		return entity;
	}

	private String getYyyyMmDd(Date date) {
		return DateFormatUtils.format(date, YYYYMMDD);
	}

	private String getYyyyMmDd(Calendar calendar) {
		return DateFormatUtils.format(calendar, YYYYMMDD);
	}

	private TemporalEntity generateTemporalEntity() {
		TemporalEntity entity = new TemporalEntity();
		entity.setDate(date);
		entity.setCalendar(calendar);
		return entity;
	}

	private EnumerationEntity generateEnumerationEntity() {
		EnumerationEntity entity = new EnumerationEntity();
		entity.setEnumeration(Enumeration.ENUM_3);
		return entity;
	}

	private LobEntity generateLobEntity() {
		LobEntity entity = new LobEntity();
		byte[] lob = new byte[] { (byte) 1, (byte) 2, (byte) 3 };
		entity.setLob(lob);
		return entity;
	}

}
