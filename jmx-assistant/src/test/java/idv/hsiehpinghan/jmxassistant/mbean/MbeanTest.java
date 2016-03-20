package idv.hsiehpinghan.jmxassistant.mbean;

import idv.hsiehpinghan.jmxassistant.suit.TestngSuitSetting;

import java.io.IOException;

import javax.management.InstanceNotFoundException;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.context.ApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MbeanTest {
	private final int NEW_ATTRIBUTE = 999;
	private MBeanServerConnection connection;

	@BeforeClass
	public void beforeClass() throws Exception {
		ApplicationContext applicationContext = TestngSuitSetting
				.getApplicationContext();
		connection = applicationContext.getBean(MBeanServerConnection.class);
	}

	@Test
	public void test() throws MalformedObjectNameException,
			InstanceNotFoundException, IOException {
		ObjectName objectName = new ObjectName(Mbean.M_BEAN_NAME);
		MbeanMBean mbeanProxy = JMX.newMBeanProxy(connection, objectName,
				MbeanMBean.class, true);
		mbeanProxy.setAttribute(NEW_ATTRIBUTE);
		Assert.assertEquals(mbeanProxy.getAttribute(), NEW_ATTRIBUTE);
	}
}
