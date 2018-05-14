package idv.hsiehpinghan.kafkaassistant.monitor;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.kafkaassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class KafkaMonitorTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private KafkaMonitor kafkaMonitor;

	/**
	 * reference : https://kafka.apache.org/0101/documentation.html#monitoring
	 * 
	 * @throws AttributeNotFoundException
	 * @throws InstanceNotFoundException
	 * @throws MalformedObjectNameException
	 * @throws MBeanException
	 * @throws ReflectionException
	 * @throws IOException
	 */
	@Test
	public void getAttribute() throws AttributeNotFoundException, InstanceNotFoundException,
			MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		Long messagesInPerSec = (Long) kafkaMonitor
				.getAttribute("kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec,topic=test_topic", "Count");
		Assert.assertTrue(0L <= messagesInPerSec.longValue());
	}
}
