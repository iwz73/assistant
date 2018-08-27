package idv.hsiehpinghan.kafkaassistant.kafkastreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.kafkaassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.kafkaassistant.vo.JsonVo;
import idv.hsiehpinghan.kafkaassistant.vo.JsonVo._Object;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class StreamsDslKafkaStreamsTest extends AbstractTestNGSpringContextTests {
	private static final int SIZE = 3;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private StreamsDslKafkaStreams streamsDslKafkaStreams;

	@Test
	public void startJsonTopicStreamTopic() throws Exception {
		JsonVo jsonVo = generateJsonVo();
		String jsonStr = objectMapper.writeValueAsString(jsonVo);
		

		String inputTopic = "jsonTopicStreamTopicInputTopic";
		String outputTopic = "jsonTopicStreamTopicOutputTopic";
		streamsDslKafkaStreams.startJsonTopicStreamTopic(inputTopic, outputTopic);
	}

	private JsonVo generateJsonVo() {
		Boolean _boolean = true;
		Integer _integer = 0;
		Float _float = 1.1f;
		String _string = "string";
		_Object _object = generateJsonVoObject(0);
		List<String> _array = Arrays.asList("string_0", "string_1", "string_2");
		List<JsonVo._Object> _object_array = generateJsonVoObjects();
		String _null = null;
		JsonVo jsonVo = new JsonVo(_boolean, _integer, _float, _string, _object, _array, _object_array, _null);
		return jsonVo;
	}

	private List<JsonVo._Object> generateJsonVoObjects() {
		List<JsonVo._Object> objects = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; ++i) {
			JsonVo._Object object = generateJsonVoObject(i);
			objects.add(object);
		}
		return objects;
	}

	private JsonVo._Object generateJsonVoObject(int i) {
		Integer _integer = i;
		String _string = "string_" + i;
		JsonVo._Object object = new JsonVo._Object(_integer, _string);
		return object;
	}
}
