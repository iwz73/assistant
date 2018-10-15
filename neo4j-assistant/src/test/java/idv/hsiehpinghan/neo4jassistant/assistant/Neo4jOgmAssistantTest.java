package idv.hsiehpinghan.neo4jassistant.assistant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.Values;
import org.neo4j.driver.v1.types.IsoDuration;
import org.neo4j.driver.v1.types.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.neo4jassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.neo4jassistant.node.Node_0_0;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class Neo4jOgmAssistantTest extends AbstractTestNGSpringContextTests {
	private static Long id = System.currentTimeMillis();
	private static String null_ = null;
	private static List<String> list = Arrays.asList("A", "B", "C");
	private static Map<String, Integer> map = generateMap();
	private static Boolean boolean_ = Boolean.TRUE;
	private static Long long_ = 1L;
	private static Double double_ = 2.2;
	private static String string = "string";
	private static byte[] bytes = "bytes".getBytes();
	private static LocalDate localDate = LocalDate.now();
	private static OffsetTime offsetTime = OffsetTime.now();
	private static LocalTime localTime = LocalTime.now();
	private static ZonedDateTime zonedDateTime = ZonedDateTime.now();
	private static LocalDateTime localDateTime = LocalDateTime.now();
	private static IsoDuration isoDuration = Values.isoDuration(1L, 2L, 3L, 4).asIsoDuration();
	private static Point point = Values.point(4979, 1.1, 2.2, 3.3).asPoint();
	private Node_0_0 node_0_0 = new Node_0_0(id, null_, list, map, boolean_, long_, double_, string, bytes, localDate, offsetTime, localTime, zonedDateTime, localDateTime, isoDuration, point);
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
		Long id = node_0_0.getId();
		Node_0_0 returnNode = assistant.load(Node_0_0.class, id, depth);
		System.err.println(returnNode);
	}
	
	private static Map<String, Integer> generateMap() {
		Map<String, Integer> map = new HashMap<>();
		for(int i = 0; i < 3; ++i) {
			String key = "key_" + i;
			Integer value = i;
			map.put(key, value);
		}
		return map;
	}
}
