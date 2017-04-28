package com.fasterxml.jackson.databind;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import idv.hsiehpinghan.jacksonassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class JsonParserTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private JsonFactory jsonFactory;

	@Test
	public void parseStream() throws Exception {
		File file = resourceLoader.getResource("classpath:/file/json").getFile();
		try (JsonParser jsonParser = jsonFactory.createParser(file)) {
			JsonToken jsonToken = null;
			jsonToken = jsonParser.nextToken(); // {
			Assert.assertEquals(jsonToken, JsonToken.START_OBJECT);
			jsonParser.nextToken(); // _boolean
			Assert.assertEquals(jsonParser.getCurrentName(), "_boolean");
			jsonParser.nextToken(); // true
			Assert.assertEquals(jsonParser.getBooleanValue(), true);
			jsonParser.nextToken(); // _integer
			Assert.assertEquals(jsonParser.getCurrentName(), "_integer");
			jsonParser.nextToken(); // 1
			Assert.assertEquals(jsonParser.getIntValue(), 1);
			jsonParser.nextToken(); // _float
			Assert.assertEquals(jsonParser.getCurrentName(), "_float");
			jsonParser.nextToken(); // 2.2
			Assert.assertEquals(jsonParser.getFloatValue(), 2.2f);
			jsonParser.nextToken(); // _string
			Assert.assertEquals(jsonParser.getCurrentName(), "_string");
			jsonParser.nextToken(); // string
			Assert.assertEquals(jsonParser.getValueAsString(), "string");
			jsonParser.nextToken(); // _object
			Assert.assertEquals(jsonParser.getCurrentName(), "_object");
			jsonToken = jsonParser.nextToken(); // {
			Assert.assertEquals(jsonToken, JsonToken.START_OBJECT);
			jsonParser.nextToken(); // _integer
			Assert.assertEquals(jsonParser.getCurrentName(), "_integer");
			jsonParser.nextToken(); // 1
			Assert.assertEquals(jsonParser.getIntValue(), 1);
			jsonParser.nextToken(); // _string
			Assert.assertEquals(jsonParser.getCurrentName(), "_string");
			jsonParser.nextToken(); // string
			Assert.assertEquals(jsonParser.getValueAsString(), "string");
			jsonToken = jsonParser.nextToken(); // }
			Assert.assertEquals(jsonToken, JsonToken.END_OBJECT);
			jsonParser.nextToken(); // _array
			Assert.assertEquals(jsonParser.getCurrentName(), "_array");
			jsonToken = jsonParser.nextToken(); // [
			Assert.assertEquals(jsonToken, JsonToken.START_ARRAY);
			jsonParser.nextToken(); // array_0
			Assert.assertEquals(jsonParser.getValueAsString(), "array_0");
			jsonParser.nextToken(); // array_1
			Assert.assertEquals(jsonParser.getValueAsString(), "array_1");
			jsonParser.nextToken(); // array_2
			Assert.assertEquals(jsonParser.getValueAsString(), "array_2");
			jsonToken = jsonParser.nextToken(); // [
			Assert.assertEquals(jsonToken, JsonToken.END_ARRAY);
			jsonParser.nextToken(); // _object_array
			Assert.assertEquals(jsonParser.getCurrentName(), "_object_array");
			jsonToken = jsonParser.nextToken(); // [
			Assert.assertEquals(jsonToken, JsonToken.START_ARRAY);
			jsonToken = jsonParser.nextToken(); // {
			Assert.assertEquals(jsonToken, JsonToken.START_OBJECT);
			jsonParser.nextToken(); // _integer
			Assert.assertEquals(jsonParser.getCurrentName(), "_integer");
			jsonParser.nextToken(); // 0
			Assert.assertEquals(jsonParser.getIntValue(), 0);
			jsonParser.nextToken(); // _string
			Assert.assertEquals(jsonParser.getCurrentName(), "_string");
			jsonParser.nextToken(); // string_0
			Assert.assertEquals(jsonParser.getValueAsString(), "string_0");
			jsonToken = jsonParser.nextToken(); // }
			Assert.assertEquals(jsonToken, JsonToken.END_OBJECT);
			jsonToken = jsonParser.nextToken(); // {
			Assert.assertEquals(jsonToken, JsonToken.START_OBJECT);
			jsonParser.nextToken(); // _integer
			Assert.assertEquals(jsonParser.getCurrentName(), "_integer");
			jsonParser.nextToken(); // 1
			Assert.assertEquals(jsonParser.getIntValue(), 1);
			jsonParser.nextToken(); // _string
			Assert.assertEquals(jsonParser.getCurrentName(), "_string");
			jsonParser.nextToken(); // string_1
			Assert.assertEquals(jsonParser.getValueAsString(), "string_1");
			jsonToken = jsonParser.nextToken(); // }
			Assert.assertEquals(jsonToken, JsonToken.END_OBJECT);
			jsonToken = jsonParser.nextToken(); // {
			Assert.assertEquals(jsonToken, JsonToken.START_OBJECT);
			jsonParser.nextToken(); // _integer
			Assert.assertEquals(jsonParser.getCurrentName(), "_integer");
			jsonParser.nextToken(); // 2
			Assert.assertEquals(jsonParser.getIntValue(), 2);
			jsonParser.nextToken(); // _string
			Assert.assertEquals(jsonParser.getCurrentName(), "_string");
			jsonParser.nextToken(); // string_2
			Assert.assertEquals(jsonParser.getValueAsString(), "string_2");
			jsonToken = jsonParser.nextToken(); // }
			Assert.assertEquals(jsonToken, JsonToken.END_OBJECT);
			jsonToken = jsonParser.nextToken(); // ]
			Assert.assertEquals(jsonToken, JsonToken.END_ARRAY);
			jsonParser.nextToken(); // _null
			Assert.assertEquals(jsonParser.getCurrentName(), "_null");
			jsonParser.nextToken(); // null
			Assert.assertEquals(jsonParser.getCurrentValue(), null);
			jsonToken = jsonParser.nextToken(); // }
			Assert.assertEquals(jsonToken, JsonToken.END_OBJECT);
		}
	}
}
