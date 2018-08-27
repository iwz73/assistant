package idv.hsiehpinghan.kafkaassistant.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer<T> implements Serializer<T> {
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonSerializer.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// do nothing
	}

	@Override
	public byte[] serialize(String topic, T data) {
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (Exception e) {
			LOGGER.error(String.format("serialize data(%s) fail !!!", data.toString()), e);
			return null;
		}
	}

	@Override
	public void close() {
		// do nothing
	}
}
