package idv.hsiehpinghan.kafkaassistant.deserializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.kafkaassistant.serializer.JsonSerializer;

public class JsonDeserializer<T> implements Deserializer<T> {
	private final static Logger LOGGER = LoggerFactory.getLogger(JsonSerializer.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	private Class<T> deserializedClass;

	public JsonDeserializer() {
	}

	public JsonDeserializer(Class<T> deserializedClass) {
		this.deserializedClass = deserializedClass;
	}

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// do nothing
	}

	@Override
	public T deserialize(String topic, byte[] data) {
		if (data == null) {
			return null;
		}
		try {
			return objectMapper.readValue(data, deserializedClass);
		} catch (Exception e) {
			LOGGER.error(String.format("deserialize data(%s) fail !!!", new String(data)), e);
			return null;
		}
	}

	@Override
	public void close() {
		// do nothing
	}
}
