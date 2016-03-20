package idv.hsiehpinghan.jmxassistant.configuration;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "idv.hsiehpinghan.jmxassistant" })
public class SpringConfiguration {

	@Bean
	public MBeanServerConnection mBeanServerConnection() throws IOException {
		JMXServiceURL url = new JMXServiceURL(
				"service:jmx:rmi:///jndi/rmi://:9999/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(url, null);
		return connector.getMBeanServerConnection();
	}
}