package idv.hsiehpinghan.kafkaassistant.kafkastreams;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.kafkaassistant.deserializer.JsonDeserializer;
import idv.hsiehpinghan.kafkaassistant.serializer.JsonSerializer;
import idv.hsiehpinghan.kafkaassistant.vo.JsonVo;
import idv.hsiehpinghan.kafkaassistant.vo.UpperCaseJsonVo;

@Component
public class ProcessorApiKafkaStreams {
	private static final long SLEEP_MILLIS = 10 * 1000;
	@Value("${application_id}")
	private String applicationId;
	@Value("${bootstrap_servers}")
	private String bootstrapServers;

	public void startJsonTopicStreamTopic(String inputTopic, String outputTopic) {
		TopologyBuilder topologyBuilder = generateJsonTopicStreamTopicTopologyBuilder(inputTopic, outputTopic);
		start(topologyBuilder);
	}

	TopologyBuilder generateJsonTopicStreamTopicTopologyBuilder(String inputTopic, String outputTopic) {
//		 TopologyBuilder topologyBuilder = new TopologyBuilder();
//		 topologyBuilder.addSource("SOURCE", stringDeserializer, purchaseJsonDeserializer, "src-topic")
		 
		return null;
		 
//		Serde<Long> inputKeySerde = Serdes.Long();
//		Serde<JsonVo> inputValueSerde = Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(JsonVo.class));
//		Serde<Long> outputKeySerde = Serdes.Long();
//		Serde<UpperCaseJsonVo> outputValueSerde = Serdes.serdeFrom(new JsonSerializer<>(),
//				new JsonDeserializer<>(UpperCaseJsonVo.class));
//		KStreamBuilder kStreamBuilder = new KStreamBuilder();
//		KStream<Long, JsonVo> kStream = kStreamBuilder.stream(inputKeySerde, inputValueSerde, inputTopic);
//		kStream.mapValues(jsonVo -> UpperCaseJsonVo.builder(jsonVo).build()).to(outputKeySerde, outputValueSerde,
//				outputTopic);
//		return kStreamBuilder;
	}

//	JsonDeserializer<Purchase> purchaseJsonDeserializer = new JsonDeserializer<>(Purchase.class);
//	JsonSerializer<Purchase> purchaseJsonSerializer = new JsonSerializer<>();
//	JsonSerializer<RewardAccumulator> rewardAccumulatorJsonSerializer = new JsonSerializer<>();
//	JsonSerializer<PurchasePattern> purchasePatternJsonSerializer = new JsonSerializer<>();
//
//	StringDeserializer stringDeserializer = new StringDeserializer();
//	StringSerializer stringSerializer = new StringSerializer();
//
//	 TopologyBuilder topologyBuilder = new TopologyBuilder();
//	 topologyBuilder.addSource("SOURCE", stringDeserializer, purchaseJsonDeserializer, "src-topic")
//
//	    .addProcessor("PROCESS", CreditCardAnonymizer::new, "SOURCE")
//	    .addProcessor("PROCESS2", PurchasePatterns::new, "PROCESS")
//	    .addProcessor("PROCESS3", CustomerRewards::new, "PROCESS")
//
//	    .addSink("SINK", "patterns", stringSerializer, purchasePatternJsonSerializer, "PROCESS2")
//	    .addSink("SINK2", "rewards",stringSerializer, rewardAccumulatorJsonSerializer, "PROCESS3")
//	    .addSink("SINK3", "purchases", stringSerializer, purchaseJsonSerializer, "PROCESS");
//
//	//Use the topologyBuilder and streamingConfig to start the kafka streams process
//	KafkaStreams streaming = new KafkaStreams(topologyBuilder, streamingConfig);
//	streaming.start();

	
	Properties generateProperties() {
		Properties properties = new Properties();
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}

	private void start(TopologyBuilder topologyBuilder) {
		Properties properties = generateProperties();
		KafkaStreams kafkaStreams = new KafkaStreams(topologyBuilder, properties);
		kafkaStreams.setUncaughtExceptionHandler((Thread thread, Throwable throwable) -> {
			throw new RuntimeException(throwable);
		});
		CountDownLatch countDownLatch = new CountDownLatch(1);
		// attach shutdown handler to catch control-c
		Runtime.getRuntime().addShutdownHook(new Thread(this.getClass().getName()) {
			@Override
			public void run() {
				kafkaStreams.close();
				countDownLatch.countDown();
			}
		});
		try {
			kafkaStreams.start();
			sleepAndCloseForTest(kafkaStreams, countDownLatch);
			countDownLatch.await();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	private void sleepAndCloseForTest(KafkaStreams kafkaStreams, CountDownLatch countDownLatch)
			throws InterruptedException {
		Thread.sleep(SLEEP_MILLIS);
		kafkaStreams.close();
		countDownLatch.countDown();
	}
}
