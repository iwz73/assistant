package idv.hsiehpinghan.kafkaassistant2.kafkastreams;

import java.util.Arrays;
import java.util.Locale;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import idv.hsiehpinghan.kafkaassistant2.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class KafkaStreamsDslKafkaStreamsTest extends AbstractTestNGSpringContextTests {
//	@Autowired
//	private KafkaStreamsDslKafkaStreams kafkaStreams;

	@Test
	public void start() {

	}
}
