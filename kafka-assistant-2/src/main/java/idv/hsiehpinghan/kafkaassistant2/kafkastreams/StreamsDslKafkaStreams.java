package idv.hsiehpinghan.kafkaassistant2.kafkastreams;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StreamsDslKafkaStreams {
	@Value("${application.id}")
	private String applicationId;
	@Value("${bootstrap.servers}")
	private String bootstrapServers;

	public void kStreamReadWrite() {
		StreamsBuilder streamsBuilder = new StreamsBuilder();
		streamsBuilder.stream("kStreamReadWriteInputTopic", Consumed.with(Serdes.Long(), Serdes.String()))
				.to("kStreamReadWriteOutputTopic", Produced.with(Serdes.Long(), Serdes.String()));
		start(streamsBuilder);
	}

	private void start(StreamsBuilder streamsBuilder) {
		Properties properties = generateProperties();
		KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
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
			countDownLatch.await();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	private Properties generateProperties() {
		Properties properties = new Properties();
		properties.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
		properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return properties;
	}

}
