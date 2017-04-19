package idv.hsiehpinghan.kafkaassistant.assistant;

import java.util.Properties;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class ProducerAssistant implements InitializingBean {
	private ProducerConfig producerConfig;
	@Autowired
	private Environment environment;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Properties properties = new Properties();
		String metadataBrokerList = environment.getRequiredProperty("metadata_broker_list");
		properties.put("metadata.broker.list", metadataBrokerList);
		properties.put("serializer.class", "kafka.serializer.StringEncoder");
		properties.put("request.required.acks", "1");
//		ProducerConfig producerConfig = new ProducerConfig(properties);
//		Producer producer = new Producer<String, String>(producerConfig);
	}

	
	
//	private static Producer<String, String> producer;
//	public SimpleProducer() {
//	Properties props = new Properties();
//	props.put("metadata.broker.list", "192.168.146.132:9092, 192.168.146.132:9093, 192.168.146.132:9094");
//	props.put("serializer.class", "kafka.serializer.StringEncoder");
//	props.put("request.required.acks", "1");
//	ProducerConfig config = new ProducerConfig(props);
//	producer = new Producer<String, String>(config);
//	}
//	public static void main(String[] args) {
//	int argsCount = args.length;
//	if (argsCount == 0 || argsCount == 1)
//	throw new IllegalArgumentException(
//	"Please provide topic name and Message count as arguments");
//	// Topic name and the message count to be published is passed from
//	the
//	// command line
//	String topic = (String) args[0];
//	String count = (String) args[1];
//	int messageCount = Integer.parseInt(count);
//	System.out.println("Topic Name - " + topic);
//	System.out.println("Message Count - " + messageCount);
//	SimpleProducer simpleProducer = new SimpleProducer();
//	simpleProducer.publishMessage(topic, messageCount);
//	}
//	private void publishMessage(String topic, int messageCount) {
//		for (int mCount = 0; mCount < messageCount; mCount++) {
//			String runtime = new Date().toString();
//			String msg = "Message Publishing Time - " + runtime;
//			System.out.println(msg);
//			// Creates a KeyedMessage instance
//			KeyedMessage<String, String> data =
//			new KeyedMessage<String, String>(topic, msg);
//			// Publish the message
//			producer.send(data);
//			}
//			// Close producer connection with broker.
//			producer.close();
//			}

}
