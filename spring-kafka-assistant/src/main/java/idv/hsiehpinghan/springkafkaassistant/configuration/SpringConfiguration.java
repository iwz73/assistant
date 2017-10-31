package idv.hsiehpinghan.springkafkaassistant.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import idv.hsiehpinghan.springkafkaassistant.model.JsonModel;

@EnableKafka
@Configuration("springKafkaAssistantSpringConfiguration")
@PropertySource("classpath:/spring_kafka_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.springkafkaassistant" })
public class SpringConfiguration implements InitializingBean {
	private String bootstrapServers;
	private String groupId;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		bootstrapServers = environment.getRequiredProperty("bootstrap_servers");
		groupId = environment.getRequiredProperty("group_id");
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		ProducerFactory<String, String> producerFactory = generateProducerFactory();
		return new KafkaTemplate<>(producerFactory);
	}

	@Bean
	public KafkaTemplate<String, JsonModel> jsonKafkaTemplate() {
		ProducerFactory<String, JsonModel> producerFactory = generateJsonProducerFactory();
		return new KafkaTemplate<>(producerFactory);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(generateConsumerFactory());
		return concurrentKafkaListenerContainerFactory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactoryWithRecordFilterStrategy() {
		ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(generateConsumerFactory());
		concurrentKafkaListenerContainerFactory.setRecordFilterStrategy(generateRecordFilterStrategy());
		return concurrentKafkaListenerContainerFactory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, JsonModel> jsonKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, JsonModel> concurrentKafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
		concurrentKafkaListenerContainerFactory.setConsumerFactory(generateJsonConsumerFactory());
		return concurrentKafkaListenerContainerFactory;
	}

	private RecordFilterStrategy<String, String> generateRecordFilterStrategy() {
		RecordFilterStrategy<String, String> recordFilterStrategy = new RecordFilterStrategy<String, String>() {
			@Override
			public boolean filter(ConsumerRecord<String, String> consumerRecord) {
				Integer value = Integer.valueOf(consumerRecord.value());
				if (value % 2 == 0) {
					return true;
				} else {
					return false;
				}
			}
		};
		return recordFilterStrategy;
	}

	private ConsumerFactory<String, JsonModel> generateJsonConsumerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		Deserializer<String> keyDeserializer = new StringDeserializer();
		Deserializer<JsonModel> valueDeserializer = new JsonDeserializer<>(JsonModel.class);
		return new DefaultKafkaConsumerFactory<>(configMap, keyDeserializer, valueDeserializer);
	}

	private ProducerFactory<String, JsonModel> generateJsonProducerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		Serializer<String> keySerializer = new StringSerializer();
		Serializer<JsonModel> valueSerializer = new JsonSerializer<JsonModel>();
		return new DefaultKafkaProducerFactory<>(configMap, keySerializer, valueSerializer);
	}

	private ConsumerFactory<String, String> generateConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(props);
	}

	private ProducerFactory<String, String> generateProducerFactory() {
		Map<String, Object> configMap = new HashMap<>();
		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(configMap);
	}

}