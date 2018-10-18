package idv.hsiehpinghan.neo4jassistant.assistant;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.neo4jassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.neo4jassistant.enumeration.Enumeration;
import idv.hsiehpinghan.neo4jassistant.node.BasicNode;
import idv.hsiehpinghan.neo4jassistant.node.BasicNode;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class Neo4jOgmAssistantTest extends AbstractTestNGSpringContextTests {
	private boolean primativeBoolean = true;
	private Boolean wrappedBoolean = true;
	private byte primativeByte = 0x1;
	private Byte wrappedByte = 0x1;
	private Byte byteString = 0x1;
	private char primativeChar = 'a';
	private Character wrappedChar = 'a';
	private double primativeDouble = 1.1;
	private Double wrappedDouble = 1.1;
	private Double doubleString = 1.1;
	private float primativeFloat = 1.1f;
	private Float wrappedFloat = 1.1f;
	private Float floatString = 1.1f;
	private int primativeInt = 1;
	private Integer wrappedInt = 1;
	private Integer integerString = 1;
	private long primativeLong = 1L;
	private Long wrappedLong = 1L;
	private Long longString = 1L;
	private short primativeShort = 1;
	private Short wrappedShort = 1;
	private UUID uuid = UUID.randomUUID();
	private String string = "string";
	private BigDecimal bigDecimal = BigDecimal.TEN;
	private BigDecimal bigDecimalString = BigDecimal.TEN;
	private BigInteger bigInteger = BigInteger.TEN;
	private BigInteger bigIntegerString = BigInteger.TEN;
	private Enumeration enum_ = Enumeration.ENUM_2;
	private Enumeration enumString = Enumeration.ENUM_2;
	private byte[] byteArray = new byte[] { 0x1, 0x2, 0x3 };
	private String[] stringArray = new String[] { "A", "B", "C" };
	private List<Date> dateList = Arrays.asList(new Date(), new Date(), new Date());
	private List<Enumeration> enumList = Arrays.asList(Enumeration.ENUM_1, Enumeration.ENUM_2, Enumeration.ENUM_3);
	private Map<String, Integer> map = generateMap();
	private Date date = new Date();
	private Date dateLong = new Date();
	private Date dateString = new Date();
	private Instant instant = Instant.now();
	private Instant instantLong = Instant.now();
	private Instant instantString = Instant.now();
	private LocalDate localDate = LocalDate.now();
	private LocalDate localDateString = LocalDate.now();
	private LocalDateTime localDateTime = LocalDateTime.now();
	private LocalDateTime localDateTimeString = LocalDateTime.now();
	private OffsetDateTime offsetDateTime = OffsetDateTime.now();
	private OffsetDateTime offsetDateTimeString = OffsetDateTime.now();
	private BasicNode basicNode = null;
	@Autowired
	private Neo4jOgmAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		assistant.purgeDatabase();
		BasicNode outcomeNode_0 = new BasicNode(primativeBoolean, wrappedBoolean, primativeByte, wrappedByte, byteString, primativeChar, wrappedChar, primativeDouble, wrappedDouble, doubleString, primativeFloat, wrappedFloat, floatString, primativeInt, wrappedInt, integerString, primativeLong, wrappedLong, longString, primativeShort, wrappedShort, uuid, string, bigDecimal, bigDecimalString, bigInteger, bigIntegerString, enum_, enumString, byteArray, stringArray, dateList, enumList, map, date, dateLong, dateString, instant, instantLong, instantString, localDate, localDateString, localDateTime, localDateTimeString, offsetDateTime, offsetDateTimeString, null, null);		
		basicNode = new BasicNode(primativeBoolean, wrappedBoolean, primativeByte, wrappedByte, byteString, primativeChar, wrappedChar, primativeDouble, wrappedDouble, doubleString, primativeFloat, wrappedFloat, floatString, primativeInt, wrappedInt, integerString, primativeLong, wrappedLong, longString, primativeShort, wrappedShort, uuid, string, bigDecimal, bigDecimalString, bigInteger, bigIntegerString, enum_, enumString, byteArray, stringArray, dateList, enumList, map, date, dateLong, dateString, instant, instantLong, instantString, localDate, localDateString, localDateTime, localDateTimeString, offsetDateTime, offsetDateTimeString, outcomeNode_0, null);	
	}

	@Test
	public void saveAndLoad() {
		int depth = 1;
		assistant.save(basicNode, depth);
		Long id = basicNode.getNativeGraphId();
		BasicNode returnNode = assistant.load(BasicNode.class, id, depth);
		assertNode(returnNode);
	}

	private void assertNode(BasicNode returnNode) {
		Assert.assertEquals(returnNode.isPrimativeBoolean(), primativeBoolean);
		Assert.assertEquals(returnNode.getWrappedBoolean(), wrappedBoolean);
		Assert.assertEquals(returnNode.getPrimativeByte(), primativeByte);
		Assert.assertEquals(returnNode.getWrappedByte(), wrappedByte);
		Assert.assertEquals(returnNode.getByteString(), byteString);
		Assert.assertEquals(returnNode.getPrimativeChar(), primativeChar);
		Assert.assertEquals(returnNode.getWrappedChar(), wrappedChar);
		Assert.assertEquals(returnNode.getPrimativeDouble(), primativeDouble);
		Assert.assertEquals(returnNode.getWrappedDouble(), wrappedDouble);
		Assert.assertEquals(returnNode.getDoubleString(), doubleString);
		Assert.assertEquals(returnNode.getPrimativeFloat(), primativeFloat);
		Assert.assertEquals(returnNode.getWrappedFloat(), wrappedFloat);
		Assert.assertEquals(returnNode.getFloatString(), floatString);
		Assert.assertEquals(returnNode.getPrimativeInt(), primativeInt);
		Assert.assertEquals(returnNode.getWrappedInt(), wrappedInt);
		Assert.assertEquals(returnNode.getIntegerString(), integerString);
		Assert.assertEquals(returnNode.getPrimativeLong(), primativeLong);
		Assert.assertEquals(returnNode.getWrappedLong(), wrappedLong);
		Assert.assertEquals(returnNode.getLongString(), longString);
		Assert.assertEquals(returnNode.getPrimativeShort(), primativeShort);
		Assert.assertEquals(returnNode.getWrappedShort(), wrappedShort);
		Assert.assertEquals(returnNode.getUuid(), uuid);
		Assert.assertEquals(returnNode.getString(), string);
		Assert.assertEquals(returnNode.getBigDecimal(), bigDecimal);
		Assert.assertEquals(returnNode.getBigDecimalString(), bigDecimalString);
		Assert.assertEquals(returnNode.getBigInteger(), bigInteger);
		Assert.assertEquals(returnNode.getBigIntegerString(), bigIntegerString);
		Assert.assertEquals(returnNode.getEnum_(), enum_);
		Assert.assertEquals(returnNode.getEnumString(), enumString);
		Assert.assertEquals(returnNode.getByteArray(), byteArray);
		Assert.assertEquals(returnNode.getStringArray(), stringArray);
		Assert.assertEquals(returnNode.getDateList(), dateList);
		Assert.assertEquals(returnNode.getEnumList(), enumList);
		Assert.assertEquals(returnNode.getMap(), map);
		Assert.assertEquals(returnNode.getDate(), date);
		Assert.assertEquals(returnNode.getDateLong(), dateLong);
		Assert.assertEquals(returnNode.getDateString(), dateString);
		Assert.assertEquals(returnNode.getInstant(), instant);
		Assert.assertEquals(returnNode.getInstantLong(), instantLong);
		Assert.assertEquals(returnNode.getInstantString(), instantString);
		Assert.assertEquals(returnNode.getLocalDate(), localDate);
		Assert.assertEquals(returnNode.getLocalDateString(), localDateString);
		Assert.assertEquals(returnNode.getLocalDateTime(), localDateTime);
		Assert.assertEquals(returnNode.getLocalDateTimeString(), localDateTimeString);
		Assert.assertEquals(returnNode.getOffsetDateTime(), offsetDateTime);
		Assert.assertEquals(returnNode.getOffsetDateTimeString(), offsetDateTimeString);
		BasicNode outcomeNode_0 = returnNode.getOutcomeNode_0();
		if(outcomeNode_0 != null) {
			assertNode(outcomeNode_0);
		}
	}
	
	private Map<String, Integer> generateMap() {
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < 3; ++i) {
			String key = "key_" + i;
			Integer value = i;
			map.put(key, value);
		}
		return map;
	}
}