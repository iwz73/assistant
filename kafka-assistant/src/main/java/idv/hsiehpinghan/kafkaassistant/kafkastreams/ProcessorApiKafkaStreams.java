package idv.hsiehpinghan.kafkaassistant.kafkastreams;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.TopologyBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.kafkaassistant.deserializer.JsonDeserializer;
import idv.hsiehpinghan.kafkaassistant.processor.UpperCaseJsonVoProcessor;
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

	public void startJsonProcessor(String inputTopic, String outputTopic) {
		TopologyBuilder topologyBuilder = generateJsonProcessorTopologyBuilder(inputTopic, outputTopic);
		start(topologyBuilder);
	}

	TopologyBuilder generateJsonProcessorTopologyBuilder(String inputTopic, String outputTopic) {
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		topologyBuilder.addSource("SOURCE", new LongDeserializer(), new JsonDeserializer<>(JsonVo.class), inputTopic)
				.addProcessor("UPPER_CASE", UpperCaseJsonVoProcessor::new, "SOURCE").addSink("SINK", outputTopic,
						new LongSerializer(), new JsonSerializer<UpperCaseJsonVo>(), "UPPER_CASE");
		return topologyBuilder;
	}

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
