package idv.hsiehpinghan.jmxassistant.mxbean;

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

public class MxbeanTest {
	private final CompositeData COMPOSITE_DATA = new CompositeData(999,
			"MbeanTest");
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
		ObjectName objectName = new ObjectName(Mxbean.MX_BEAN_NAME);
		MxbeanMXBean mxbeanProxy = JMX.newMXBeanProxy(connection, objectName,
				MxbeanMXBean.class, true);
		mxbeanProxy.setCompositeData(COMPOSITE_DATA);
		Assert.assertEquals(mxbeanProxy.getCompositeData().getInteger(),
				COMPOSITE_DATA.getInteger());
		Assert.assertEquals(mxbeanProxy.getCompositeData().getString(),
				COMPOSITE_DATA.getString());
	}

}
