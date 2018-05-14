package idv.hsiehpinghan.jmxassistant.mxbean;

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
public class MxbeanTest extends AbstractTestNGSpringContextTests {
	private final CompositeData COMPOSITE_DATA = new CompositeData(999, "MbeanTest");
	@Autowired
	private MBeanServerConnection connection;

	@Test
	public void test() throws MalformedObjectNameException, InstanceNotFoundException, IOException {
		ObjectName objectName = new ObjectName(Mxbean.MX_BEAN_NAME);
		MxbeanMXBean mxbeanProxy = JMX.newMXBeanProxy(connection, objectName, MxbeanMXBean.class, true);
		
		System.err.println(mxbeanProxy.getCompositeData().getInteger());
		
		mxbeanProxy.setCompositeData(COMPOSITE_DATA);
		Assert.assertEquals(mxbeanProxy.getCompositeData().getInteger(), COMPOSITE_DATA.getInteger());
		Assert.assertEquals(mxbeanProxy.getCompositeData().getString(), COMPOSITE_DATA.getString());
	}

}
