package com.fasterxml.jackson.databind;

import idv.hsiehpinghan.jacksonassistant.suit.TestngSuitSetting;
import idv.hsiehpinghan.jacksonassistant.vo.AnnotationJsonVo;
import idv.hsiehpinghan.jacksonassistant.vo.ConstructorJsonVo;
import idv.hsiehpinghan.jacksonassistant.vo.FactoryJsonVo;
import idv.hsiehpinghan.jacksonassistant.vo.JsonVo;
import idv.hsiehpinghan.testutility.utility.SystemResourceUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class ObjectMapperTest {
	private ObjectMapper objectMapper;
	private File json;
	private JsonVo jsonVo;

	@BeforeClass
	public void beforeClass() throws IOException {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		objectMapper = applicationContext.getBean(ObjectMapper.class);
		json = SystemResourceUtility.getFileResource("file/json");
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
		final String TEXT = "text";
		final String TEXT_VALUE = "text value";
		ObjectNode objectNode = (ObjectNode) objectMapper.readTree(json);
		objectNode.put(TEXT, TEXT_VALUE);
		String result = objectNode.get(TEXT).asText();
		Assert.assertEquals(result, TEXT_VALUE);
	}

	@Test
	public void annotation() throws Exception {
		File annotationJson = SystemResourceUtility
				.getFileResource("file/annotation_json");
		AnnotationJsonVo jsonVo = objectMapper.readValue(annotationJson,
				AnnotationJsonVo.class);
		assertAnnotationJsonVo(jsonVo);
	}

	@Test
	public void constructor() throws Exception {
		File constructorJson = SystemResourceUtility
				.getFileResource("file/constructor_json");
		ConstructorJsonVo jsonVo = objectMapper.readValue(constructorJson,
				ConstructorJsonVo.class);
		assertConstructorJsonVo(jsonVo);
	}

	@Test
	public void factory() throws Exception {
		File factoryJson = SystemResourceUtility
				.getFileResource("file/factory_json");
		FactoryJsonVo jsonVo = objectMapper.readValue(factoryJson,
				FactoryJsonVo.class);
		assertFactoryJsonVo(jsonVo);
	}
	
	private void assertJsonVo(JsonVo jsonVo) {
		Assert.assertEquals(jsonVo.get_boolean().booleanValue(), true);
		Assert.assertEquals(jsonVo.get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_float().floatValue(),
				new Float("2.2").floatValue());
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
		Assert.assertEquals(jsonVo.get_float().floatValue(),
				new Float("2.2").floatValue());
		Assert.assertEquals(jsonVo.get_string(), "string");
		Assert.assertEquals(jsonVo.get_object().get_integer().intValue(), 1);
		Assert.assertEquals(jsonVo.get_object().get_string(), "string");
		List<String> _array = jsonVo.get_array();
		for (int i = 0, size = _array.size(); i < size; ++i) {
			Assert.assertEquals(_array.get(i), "array_" + i);
		}
		List<AnnotationJsonVo._Object> _object_array = jsonVo
				.get_object_array();
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
}
