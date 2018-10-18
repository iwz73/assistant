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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.neo4jassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.neo4jassistant.enumeration.Enumeration;
import idv.hsiehpinghan.neo4jassistant.node.Node_0_0;

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
	private Node_0_0 node_0_0 = new Node_0_0(primativeBoolean, wrappedBoolean, primativeByte, wrappedByte, byteString, primativeChar, wrappedChar, primativeDouble, wrappedDouble, doubleString, primativeFloat, wrappedFloat, floatString, primativeInt, wrappedInt, integerString, primativeLong, wrappedLong, longString, primativeShort, wrappedShort, uuid, string, bigDecimal, bigDecimalString, bigInteger, bigIntegerString, enum_, enumString, byteArray, stringArray, dateList, enumList, map, date, dateLong, dateString, instant, instantLong, instantString, localDate, localDateString, localDateTime, localDateTimeString, offsetDateTime, offsetDateTimeString);
	@Autowired
	private Neo4jOgmAssistant assistant;

	@BeforeClass
	public void beforeClass() {
		assistant.purgeDatabase();
	}

	@Test
	public void saveAndLoad() {
		int depth = 1;
		assistant.save(node_0_0, depth);
		Long id = node_0_0.getNativeGraphId();
		Node_0_0 returnNode = assistant.load(Node_0_0.class, id, depth);
		System.err.println(returnNode);

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
