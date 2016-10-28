package idv.hsiehpinghan.nekohtmlassistant.assistant;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import idv.hsiehpinghan.nekohtmlassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class NekohtmlAssistantTest extends AbstractTestNGSpringContextTests {
	private Document doc;
	private Node lastChild;
	private String elementStructureId;
	@Autowired
	private NekohtmlAssistant assistant;

	@BeforeClass
	public void beforeClass() throws SAXException, IOException {
		InputStream inputStream = new ClassPathResource("html/nekohtml_index.html").getInputStream();
		InputSource inputSource = new InputSource(inputStream);
		doc = assistant.getDocument(inputSource);
	}

	// @Test
	public void getBody() {
		Assert.assertEquals(assistant.getElementsByTagName(doc, "body").getLength(), 1);
	}

	// @Test
	public void getHtmlString() {
		System.err.println(assistant.getHtmlString(doc));
	}

	// @Test
	public void getHtmlStructureId() {
		System.err.println(assistant.getHtmlStructureId(doc));
	}

	// @Test
	public void getElementStructureId() {
		Node bodyNode = assistant.getBodyNode(doc);
		lastChild = getLatestChild(bodyNode);
		elementStructureId = assistant.getElementStructureId(lastChild);
		System.err.println("getElementStructureId : " + elementStructureId);
	}

	// @Test(dependsOnMethods = { "getElementStructureId" })
	public void getNodeByElementStructureId() {
		Node node = assistant.getNodeByElementStructureId(doc, elementStructureId);
		Assert.assertTrue(lastChild.isEqualNode(node));
		System.err.println("getNodeByElementStructureId : " + node.getTextContent());
	}

	// @Test
	public void getAttribute() {
		Node node = assistant.getElementsByTagName(doc, "body").item(0);
		String attr = assistant.getAttribute(node, "bgcolor");
		Assert.assertEquals(attr, "#999999");
	}

	// @Test
	public void addAllElementWithStructureId() {
		Document doc = assistant.addAllElementWithStructureId(this.doc);
		System.err.println(assistant.getHtmlString(doc));
	}

	private Node getLatestChild(Node node) {
		NodeList nodeList = node.getChildNodes();
		for (int i = nodeList.getLength() - 1; 0 <= i; --i) {
			Node nd = nodeList.item(i);
			if (assistant.isIgnore(nd.getNodeName())) {
				continue;
			}
			Node n = getLatestChild(nd);
			if (n == null) {
				return nd;
			} else {
				return n;
			}
		}
		return null;
	}
}
