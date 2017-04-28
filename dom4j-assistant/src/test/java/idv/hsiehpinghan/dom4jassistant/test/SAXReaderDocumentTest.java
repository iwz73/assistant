package idv.hsiehpinghan.dom4jassistant.test;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import idv.hsiehpinghan.dom4jassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class SAXReaderDocumentTest extends AbstractTestNGSpringContextTests {
	private Document document;

	@Autowired
	private ResourceLoader resourceLoader;

	@BeforeClass
	public void beforeClass() throws IOException, DocumentException {
		File file = resourceLoader.getResource("classpath:/tifrs-fr0-m1-ci-cr-1102-2013Q1.xml").getFile();
		SAXReader reader = new SAXReader();
		document = reader.read(file);
	}

	@Test
	public void elementIterator() {
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = root.elementIterator();
		int i = 0;
		while (iter.hasNext()) {
			Element element = iter.next();
			System.err.println(element.getNamespacePrefix() + ":" + element.getName());
			++i;
		}
		Assert.assertTrue(i > 0);
	}

	@Test(dependsOnMethods = { "elementIterator" })
	public void subElementIterator() {
		Element root = document.getRootElement();
		@SuppressWarnings("unchecked")
		Iterator<Element> iter = root.elementIterator("Equity");
		int i = 0;
		while (iter.hasNext()) {
			Element element = iter.next();
			System.err.println(element.getNamespacePrefix() + ":" + element.getName());
			++i;
		}
		Assert.assertTrue(i > 0);
	}

	@Test(dependsOnMethods = { "subElementIterator" })
	public void attributeIterator() {
		Element root = document.getRootElement();
		Element ele = root.element("Year");
		@SuppressWarnings("unchecked")
		Iterator<Attribute> iter = ele.attributeIterator();
		int i = 0;
		while (iter.hasNext()) {
			Attribute attribute = iter.next();
			System.err.println(attribute.getName() + "=" + attribute.getValue());
			++i;
		}
		Assert.assertTrue(i > 0);
	}

	@Test(dependsOnMethods = { "attributeIterator" })
	public void xpath() {
		Node node = document.selectSingleNode("/xbrl/ifrs:CashAndCashEquivalents[@contextRef='AsOf20120331']");
		Assert.assertEquals(node.getText(), "6096166000");
	}

	@Test(dependsOnMethods = { "xpath" })
	public void fastLooping() {
		Element root = document.getRootElement();
		recursivePrint(root, "");
	}

	private void recursivePrint(Element element, String tabString) {
		for (int i = 0, size = element.nodeCount(); i < size; ++i) {
			Node node = element.node(i);
			short nodeType = node.getNodeType();
			switch (nodeType) {
			case Node.ELEMENT_NODE:
				System.err.println(tabString + node.getName());
				recursivePrint((Element) node, tabString + "\t");
			default:
				// do nothing
			}
		}
	}

}
