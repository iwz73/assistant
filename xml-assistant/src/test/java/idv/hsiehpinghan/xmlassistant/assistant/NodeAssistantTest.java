package idv.hsiehpinghan.xmlassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Node;

import idv.hsiehpinghan.xmlassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class NodeAssistantTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private NodeAssistant assistant;

	@Test
	public void getNodeTypeName() {
		String nodeTypeName = assistant.getNodeTypeName(Node.ELEMENT_NODE);
		Assert.assertEquals(nodeTypeName, "ELEMENT_NODE");
	}
}
