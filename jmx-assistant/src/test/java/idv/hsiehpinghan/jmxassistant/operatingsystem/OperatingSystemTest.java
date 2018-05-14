package idv.hsiehpinghan.jmxassistant.operatingsystem;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jmxassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class OperatingSystemTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private MBeanServerConnection connection;

	@Test
	public void test() throws Exception {
		AttributeList attributeList = connection.getAttributes(new ObjectName("java.lang:type=OperatingSystem"),
				new String[] { "FreePhysicalMemorySize", "TotalPhysicalMemorySize", "ProcessCpuLoad",
						"SystemCpuLoad" });
		Long freePhysicalMemorySize = (Long) ((Attribute) attributeList.get(0)).getValue();
		Assert.assertTrue(0.0 <= freePhysicalMemorySize);
		Long totalPhysicalMemorySize = (Long) ((Attribute) attributeList.get(1)).getValue();
		Assert.assertTrue(0.0 <= totalPhysicalMemorySize);
		Double processCpuLoad = (Double) ((Attribute) attributeList.get(2)).getValue();
		Assert.assertTrue(0.0 <= processCpuLoad);
		Double systemCpuLoad = (Double) ((Attribute) attributeList.get(3)).getValue();
		Assert.assertTrue(0.0 <= systemCpuLoad);
	}

}
