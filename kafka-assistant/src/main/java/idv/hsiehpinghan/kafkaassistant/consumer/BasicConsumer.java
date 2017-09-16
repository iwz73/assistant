package idv.hsiehpinghan.kafkaassistant.consumer;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class BasicConsumer implements InitializingBean {
	private final long TIMEOUT = 1000;
	private String topic;
	@Autowired
	@Qualifier("basicConsumer_0")
	private Consumer<String, String> basicConsumer;
	@Autowired
	private Environment environment;

	@Override
	public void afterPropertiesSet() throws Exception {
		topic = environment.getRequiredProperty("basic_topic");
	}

	public ConsumerRecords<String, String> poll() {
		basicConsumer.subscribe(Arrays.asList(topic));
		while (true) {
			return basicConsumer.poll(TIMEOUT);
		}
	}

}
