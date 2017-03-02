package idv.hsiehpinghan.elasticsearchassistant.assistant;

import java.io.IOException;
import java.math.BigDecimal;

import org.elasticsearch.action.index.IndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import idv.hsiehpinghan.elasticsearchassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ElasticsearchAssistantTest extends AbstractTestNGSpringContextTests {
	private final String INDEX = "elasticsearch_index";
	private final String TYPE = "elasticsearch_type";
	private final String ID = "elasticsearch_id";
	private final String BIGDECIMAL_NAME = "BIGDECIMAL_NAME";
	private final BigDecimal BIGDECIMAL = BigDecimal.ONE;
	private final String BOOLEAN_NAME = "BOOLEAN_NAME";
	private final Boolean BOOLEAN = Boolean.TRUE;
	private final String PRIMARY_BOOLEAN_NAME = "PRIMARY_BOOLEAN_NAME";
	private final boolean PRIMARY_BOOLEAN = true;
	private final String BYTE_ARRAY_NAME = "BYTE_ARRAY_NAME";
	private final byte[] BYTE_ARRAY = generateByteArray();
	private final String PRIMARY_DOUBLE_NAME = "PRIMARY_DOUBLE_NAME";
	private final double PRIMARY_DOUBLE = 1.1;
	private final String DOUBLE_NAME = "DOUBLE_NAME";
	private final Double DOUBLE = 2.2;
	private final String FLOAT_NAME = "FLOAT_NAME";
	private final Float FLOAT = 3.3f;
	private final String PRIMARY_FLOAT_NAME = "PRIMARY_FLOAT_NAME";
	private final float PRIMARY_FLOAT = 4.4f;
	private final String PRIMARY_INT_NAME = "PRIMARY_INT_NAME";
	private final int PRIMARY_INT = 5;
	private final String INTEGER_NAME = "INTEGER_NAME";
	private final Integer INTEGER = 6;
	private final String JSON_NODE_NAME = "JSON_NODE_NAME";
	private final JsonNode JSON_NODE = generateJsonNode();
	private final String PRIMARY_LONG_NAME = "PRIMARY_LONG_NAME";
	private final long PRIMARY_LONG = 7;
	private final String LONG_NAME = "LONG_NAME";
	private final Long LONG = 8L;
	private final String SHORT_NAME = "SHORT_NAME";
	private final Short SHORT = 9;
	private final String PRIMARY_SHORT_NAME = "PRIMARY_SHORT_NAME";
	private final short PRIMARY_SHORT = 10;
	private final String STRING_NAME = "STRING_NAME";
	private final String STRING = "string";
	private final String ARRAY_NAME = "ARRAY_NAME";
	private ArrayNode arrayNode = null;

	@Autowired
	private ElasticsearchAssistant assistant;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void prepareIndex() throws Exception {
		String source = generateSource();
		IndexResponse indexResponse = assistant.prepareIndex(INDEX, TYPE, ID, source);
		
		System.err.println(indexResponse.getId());
		
	}

	private String generateSource() throws IOException {
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.put(BIGDECIMAL_NAME, BIGDECIMAL);
		objectNode.put(BOOLEAN_NAME, BOOLEAN);
		objectNode.put(PRIMARY_BOOLEAN_NAME, PRIMARY_BOOLEAN);
		objectNode.put(BYTE_ARRAY_NAME, BYTE_ARRAY);
		objectNode.put(PRIMARY_DOUBLE_NAME, PRIMARY_DOUBLE);
		objectNode.put(DOUBLE_NAME, DOUBLE);
		objectNode.put(FLOAT_NAME, FLOAT);
		objectNode.put(PRIMARY_FLOAT_NAME, PRIMARY_FLOAT);
		objectNode.put(PRIMARY_INT_NAME, PRIMARY_INT);
		objectNode.put(INTEGER_NAME, INTEGER);
		objectNode.put(PRIMARY_LONG_NAME, PRIMARY_LONG);
		objectNode.put(LONG_NAME, LONG);
		objectNode.put(SHORT_NAME, SHORT);
		objectNode.put(PRIMARY_SHORT_NAME, PRIMARY_SHORT);
		objectNode.put(STRING_NAME, STRING);
		objectNode.set(JSON_NODE_NAME, JSON_NODE);
		arrayNode = objectNode.putArray(ARRAY_NAME);
		appendArrayNode(arrayNode);
		return objectNode.toString();
	}
	
	private void appendArrayNode(ArrayNode arrayNode) {
		for (int i = 0; i < 3; ++i) {
			arrayNode.add(i);
		}
	}

	private JsonNode generateJsonNode() {
		final String TEXT = "text";
		final String TEXT_VALUE = "text value";
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode objectNode = objectMapper.createObjectNode();
		objectNode.put(TEXT, TEXT_VALUE);
		return (JsonNode) objectNode;
	}

	private byte[] generateByteArray() {
		return new byte[] { 0x1, 0x2, 0x3 };
	}
}
