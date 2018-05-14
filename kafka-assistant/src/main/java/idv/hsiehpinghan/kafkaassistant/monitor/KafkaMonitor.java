package idv.hsiehpinghan.kafkaassistant.monitor;

import java.io.IOException;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaMonitor {
	@Autowired
	private MBeanServerConnection mBeanServerConnection;

	public Object getAttribute(String name, String attribute) throws AttributeNotFoundException,
			InstanceNotFoundException, MalformedObjectNameException, MBeanException, ReflectionException, IOException {
		return mBeanServerConnection.getAttribute(new ObjectName(name), attribute);
	}

}
