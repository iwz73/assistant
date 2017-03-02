package idv.hsiehpinghan.elasticsearchassistant.assistant;

import java.io.IOException;
import java.math.BigDecimal;

import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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
		String source = generateSource(STRING);
		IndexResponse indexResponse = assistant.prepareIndex(INDEX, TYPE, ID, source);
		Assert.assertEquals(indexResponse.getId(), ID);
	}

	@Test(dependsOnMethods = { "prepareIndex" })
	public void prepareGet() throws Exception {
		GetResponse getResponse = assistant.prepareGet(INDEX, TYPE, ID);
		String result = getResponse.getSourceAsString();
		validateObjectNode((ObjectNode) objectMapper.readTree(result));
	}

	@Test(dependsOnMethods = { "prepareGet" })
	public void prepareUpdate() throws Exception {
		String updatedString = "updated string";
		String source = generateSource(updatedString);
		UpdateResponse updateResponse = assistant.prepareUpdate(INDEX, TYPE, ID, source);
		Assert.assertEquals(updateResponse.getId(), ID);
		GetResponse getResponse = assistant.prepareGet(INDEX, TYPE, ID);
		String result = getResponse.getSourceAsString();
		JsonNode jsonNode = objectMapper.readTree(result);
		Assert.assertEquals(jsonNode.get(STRING_NAME).textValue(), updatedString);
	}

	@Test(dependsOnMethods = { "prepareUpdate" })
	public void prepareDelete() throws Exception {
		DeleteResponse deleteResponse = assistant.prepareDelete(INDEX, TYPE, ID);
		Assert.assertTrue(deleteResponse.isFound());
	}

	@Test(dependsOnMethods = { "prepareDelete" })
	public void prepareBulk() throws Exception {
		String source = generateSource(STRING);
		BulkResponse bulkResponse = assistant.prepareBulk(INDEX, TYPE, ID, source);
		Assert.assertFalse(bulkResponse.hasFailures());
	}

	private String generateSource(String string) throws IOException {
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
		objectNode.put(STRING_NAME, string);
		objectNode.set(JSON_NODE_NAME, JSON_NODE);
		arrayNode = objectNode.putArray(ARRAY_NAME);
		appendArrayNode(arrayNode);
		return objectNode.toString();
	}

	private void validateObjectNode(ObjectNode objectNode) throws IOException {
		Assert.assertEquals(objectNode.get(BIGDECIMAL_NAME).decimalValue(), BIGDECIMAL);
		Assert.assertEquals(objectNode.get(BOOLEAN_NAME).booleanValue(), BOOLEAN.booleanValue());
		Assert.assertEquals(objectNode.get(PRIMARY_BOOLEAN_NAME).booleanValue(), PRIMARY_BOOLEAN);
		Assert.assertEquals(objectNode.get(BYTE_ARRAY_NAME).binaryValue(), BYTE_ARRAY);
		Assert.assertEquals(objectNode.get(PRIMARY_DOUBLE_NAME).doubleValue(), PRIMARY_DOUBLE);
		Assert.assertEquals(objectNode.get(DOUBLE_NAME).doubleValue(), DOUBLE);
		Assert.assertEquals(objectNode.get(FLOAT_NAME).floatValue(), FLOAT);
		Assert.assertEquals(objectNode.get(PRIMARY_FLOAT_NAME).floatValue(), PRIMARY_FLOAT);
		Assert.assertEquals(objectNode.get(PRIMARY_INT_NAME).intValue(), PRIMARY_INT);
		Assert.assertEquals(objectNode.get(INTEGER_NAME).intValue(), INTEGER.intValue());
		Assert.assertEquals(objectNode.get(PRIMARY_LONG_NAME).longValue(), PRIMARY_LONG);
		Assert.assertEquals(objectNode.get(LONG_NAME).longValue(), LONG.longValue());
		Assert.assertEquals(objectNode.get(SHORT_NAME).shortValue(), SHORT.shortValue());
		Assert.assertEquals(objectNode.get(PRIMARY_SHORT_NAME).shortValue(), PRIMARY_SHORT);
		Assert.assertEquals(objectNode.get(STRING_NAME).textValue(), STRING);
		Assert.assertEquals(objectNode.get(JSON_NODE_NAME), JSON_NODE);
		Assert.assertEquals(objectNode.get(ARRAY_NAME).toString(), arrayNode.toString());
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
