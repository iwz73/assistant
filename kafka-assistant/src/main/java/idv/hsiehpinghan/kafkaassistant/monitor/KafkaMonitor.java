package idv.hsiehpinghan.kafkaassistant.monitor;

import java.io.IOException;

import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("jmx")
public class KafkaMonitor {
	@Autowired
	private MBeanServerConnection mBeanServerConnection;

	public Object getAttribute(String name, String attribute) throws AttributeNotFoundException,
			InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		return mBeanServerConnection.getAttribute(new ObjectName(name), attribute);
	}

	public AttributeList getAttributeList(String name, String[] attributes) throws AttributeNotFoundException,
			InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		return (AttributeList) mBeanServerConnection.getAttributes(new ObjectName(name), attributes);
	}
}
