package idv.hsiehpinghan.jaxbassistant.assistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import idv.hsiehpinghan.jaxbassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.jaxbassistant.model.BasicModel;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class BasicAssistantTest extends AbstractTestNGSpringContextTests {
	private final String STRING_XML_ATTRIBUTE = "stringXmlAttribute";
	private final int INT_XML_ATTRIBUTE = 0;
	private final String STRING_XML_ELEMENT = "stringXmlElement";
	private final int INT_XML_ELEMENT = 1;
	private final String SUB_STRING_XML_ATTRIBUTE = "subStringXmlAttribute";
	private final int SUB_INT_XML_ATTRIBUTE = 2;
	private final String SUB_STRING_XML_ELEMENT = "subStringXmlElement";
	private final int SUB_INT_XML_ELEMENT = 3;
	private final BasicModel model = BasicModel.build(STRING_XML_ATTRIBUTE, INT_XML_ATTRIBUTE, STRING_XML_ELEMENT,
			INT_XML_ELEMENT, SUB_STRING_XML_ATTRIBUTE, SUB_INT_XML_ATTRIBUTE, SUB_STRING_XML_ELEMENT,
			SUB_INT_XML_ELEMENT);
	private String actual;
	@Autowired
	private BasicAssistant assistant;

	@Test
	public void marshaller() throws Exception {
		actual = assistant.marshaller(model);
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><basicModel stringXmlAttribute=\"stringXmlAttribute\" intXmlAttribute=\"0\"><stringXmlElement>stringXmlElement</stringXmlElement><intXmlElement>1</intXmlElement><clazz stringXmlAttribute=\"subStringXmlAttribute\" intXmlAttribute=\"2\"><stringXmlElement>subStringXmlElement</stringXmlElement><intXmlElement>3</intXmlElement></clazz></basicModel>";
		Assert.assertEquals(actual, expected);
	}

	@Test(dependsOnMethods = { "marshaller" })
	public void unmarshaller() throws Exception {
		BasicModel unmarshalledModel = assistant.unmarshaller(actual);
		assertBasicModel(unmarshalledModel);
	}

	private void assertBasicModel(BasicModel unmarshalledModel) {
		Assert.assertEquals(unmarshalledModel.getStringXmlAttribute(), model.getStringXmlAttribute());
		Assert.assertEquals(unmarshalledModel.getIntXmlAttribute(), model.getIntXmlAttribute());
		Assert.assertEquals(unmarshalledModel.getStringXmlElement(), model.getStringXmlElement());
		Assert.assertEquals(unmarshalledModel.getIntXmlElement(), model.getIntXmlElement());
	}
}
