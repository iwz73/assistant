package idv.hsiehpinghan.jmxassistant.mbean;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jmxassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class MbeanTest extends AbstractTestNGSpringContextTests {
	private final int NEW_ATTRIBUTE = 999;
	@Autowired
	private MBeanServerConnection connection;

	@Test
	public void test() throws MalformedObjectNameException, InstanceNotFoundException, IOException {
		ObjectName objectName = new ObjectName(Mbean.M_BEAN_NAME);
		MbeanMBean mbeanProxy = JMX.newMBeanProxy(connection, objectName, MbeanMBean.class, true);
		mbeanProxy.setAttribute(NEW_ATTRIBUTE);
		Assert.assertEquals(mbeanProxy.getAttribute(), NEW_ATTRIBUTE);
	}
}
