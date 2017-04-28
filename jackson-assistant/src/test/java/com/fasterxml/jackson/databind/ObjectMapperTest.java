package com.fasterxml.jackson.databind;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import idv.hsiehpinghan.jacksonassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.jacksonassistant.vo.AnnotationJsonVo;
import idv.hsiehpinghan.jacksonassistant.vo.ConstructorJsonVo;
import idv.hsiehpinghan.jacksonassistant.vo.FactoryJsonVo;
import idv.hsiehpinghan.jacksonassistant.vo.JsonVo;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ObjectMapperTest extends AbstractTestNGSpringContextTests {
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
	private File json;
	private JsonVo jsonVo;

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() throws IOException {
		json = resourceLoader.getResource("classpath:/file/json").getFile();
	}

	@Test
	public void readValue() throws Exception {
		jsonVo = objectMapper.readValue(json, JsonVo.class);
		assertJsonVo(jsonVo);
	}

	@Test(dependsOnMethods = "readValue")
	public void writeValue() throws Exception {
		File tempJson = new File("/tmp/tempJson");
		objectMapper.writeValue(tempJson, jsonVo);
		JsonVo tempJsonVo = objectMapper.readValue(tempJson, JsonVo.class);
		assertJsonVo(tempJsonVo);
	}

	@Test
	public void put() throws Exception {
		put_0();
		put_1();
	}

	@Test
	public void annotation() throws Exception {
		File annotationJson = resourceLoader.getResource("classpath:/file/annotation_json").getFile();
		AnnotationJsonVo jsonVo = objectMapper.readValue(annotationJson, AnnotationJsonVo.class);
		assertAnnotationJsonVo(jsonVo);
	}

	@Test
	public void constructor() throws Exception {
		File constructorJson = resourceLoader.getResource("classpath:/file/constructor_json").getFile();
		ConstructorJsonVo jsonVo = objectMapper.readValue(constructorJson, ConstructorJsonVo.class);
		assertConstructorJsonVo(jsonVo);
	}

	@Test
	public void factory() throws Exception {
		File factoryJson = resourceLoader.getResource("classpath:/file/factory_json").getFile();
		FactoryJsonVo jsonVo = objectMapper.readValue(factoryJson, FactoryJsonVo.class);
		assertFactoryJsonVo(jsonVo);
	}

	private void assertJsonVo(JsonVo jsonVo) {
		Assert.assertEquals(jsonVo.get_boolean().booleanValue(), true);
		Assert.assertEquals(jsonVo.get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_float().floatValue(), new Float("2.2").floatValue());
		Assert.assertEquals(jsonVo.get_string(), "string");
		Assert.assertEquals(jsonVo.get_object().get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_object().get_string(), "string");
		List<String> _array = jsonVo.get_array();
		for (int i = 0, size = _array.size(); i < size; ++i) {
			Assert.assertEquals(_array.get(i), "array_" + i);
		}
		List<JsonVo._Object> _object_array = jsonVo.get_object_array();
		for (int i = 0, size = _object_array.size(); i < size; ++i) {
			JsonVo._Object _object = _object_array.get(i);
			Assert.assertEquals(_object.get_integer().intValue(), i);
			Assert.assertEquals(_object.get_string(), "string_" + i);

		}
		Assert.assertNull(jsonVo.get_null());
	}

	private void assertAnnotationJsonVo(AnnotationJsonVo jsonVo) {
		Assert.assertEquals(jsonVo.get_boolean().booleanValue(), true);
		Assert.assertEquals(jsonVo.get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_float().floatValue(), new Float("2.2").floatValue());
		Assert.assertEquals(jsonVo.get_string(), "string");
		Assert.assertEquals(jsonVo.get_object().get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_object().get_string(), "string");
		List<String> _array = jsonVo.get_array();
		for (int i = 0, size = _array.size(); i < size; ++i) {
			Assert.assertEquals(_array.get(i), "array_" + i);
		}
		List<AnnotationJsonVo._Object> _object_array = jsonVo.get_object_array();
		for (int i = 0, size = _object_array.size(); i < size; ++i) {
			AnnotationJsonVo._Object _object = _object_array.get(i);
			Assert.assertEquals(_object.get_integer().intValue(), i);
			Assert.assertEquals(_object.get_string(), "string_" + i);

		}
		Assert.assertNull(jsonVo.get_null());
		Assert.assertNull(jsonVo.getJson_ignore_properties());
		Assert.assertNull(jsonVo.getJson_ignore());
	}

	private void assertConstructorJsonVo(ConstructorJsonVo jsonVo) {
		Assert.assertEquals(jsonVo.get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_string(), "string");
	}

	private void assertFactoryJsonVo(FactoryJsonVo jsonVo) {
		Assert.assertEquals(jsonVo.get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_string(), "string");
	}

	private void put_0() throws JsonProcessingException, IOException {
		final String TEXT = "text";
		final String TEXT_VALUE = "text value";
		ObjectNode objectNode = (ObjectNode) objectMapper.readTree(json);
		objectNode.put(TEXT, TEXT_VALUE);
		String result = objectNode.get(TEXT).asText();
		Assert.assertEquals(result, TEXT_VALUE);
	}

	private void put_1() throws IOException {
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
		validateObjectNode(objectNode);
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
