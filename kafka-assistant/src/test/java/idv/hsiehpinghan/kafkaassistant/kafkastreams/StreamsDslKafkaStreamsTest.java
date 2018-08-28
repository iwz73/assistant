package idv.hsiehpinghan.kafkaassistant.kafkastreams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.kafkaassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.kafkaassistant.consumer.BasicConsumer;
import idv.hsiehpinghan.kafkaassistant.producer.BasicProducer;
import idv.hsiehpinghan.kafkaassistant.vo.JsonVo;
import idv.hsiehpinghan.kafkaassistant.vo.JsonVo._Object;
import idv.hsiehpinghan.kafkaassistant.vo.UpperCaseJsonVo;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class StreamsDslKafkaStreamsTest extends AbstractTestNGSpringContextTests {
	private static final int SIZE = 3;
	private static final Date NOW = new Date();
	private static final String STRING = "string_" + NOW.getTime();
	private static final String INPUT_TOPIC = "jsonTopicStreamTopicInputTopic";
	private static final String OUTPUT_TOPIC = "jsonTopicStreamTopicOutputTopic";
	private final long SLEEP_MILLISECONDS = 5 * 1000;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private BasicProducer basicProducer;
	@Autowired
	private BasicConsumer basicConsumer;
	@Autowired
	private StreamsDslKafkaStreams streamsDslKafkaStreams;

	@Test
	public void startJsonTopicStreamTopic() throws Exception {
		JsonVo jsonVo = generateJsonVo();
		String jsonStr = objectMapper.writeValueAsString(jsonVo);
		basicProducer.send(INPUT_TOPIC, jsonStr);
		streamsDslKafkaStreams.startJsonTopicStreamTopic(INPUT_TOPIC, OUTPUT_TOPIC);
		Thread.sleep(SLEEP_MILLISECONDS);
		ConsumerRecords<Long, String> consumerRecords = basicConsumer.poll(OUTPUT_TOPIC);
		Assert.assertTrue(isExist(consumerRecords, STRING));
	}

	private boolean isExist(ConsumerRecords<Long, String> consumerRecords, String _string)
			throws JsonParseException, JsonMappingException, IOException {
		for (ConsumerRecord<Long, String> consumerRecord : consumerRecords) {
			String actual = consumerRecord.value();
			UpperCaseJsonVo upperCaseJsonVo = objectMapper.readValue(actual, UpperCaseJsonVo.class);
			if (_string.toUpperCase().equals(upperCaseJsonVo.get_string()) == true) {
				return true;
			}
		}
		return false;
	}

	private JsonVo generateJsonVo() {
		Boolean _boolean = true;
		Integer _integer = 0;
		Float _float = 1.1f;
		String _string = STRING;
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
