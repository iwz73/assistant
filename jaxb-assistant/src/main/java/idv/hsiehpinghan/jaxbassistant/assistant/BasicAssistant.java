package idv.hsiehpinghan.jaxbassistant.assistant;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.jaxbassistant.model.BasicModel;
import idv.hsiehpinghan.jaxbassistant.utility.JAXBUtility;

@Component
public class BasicAssistant {
	@Autowired
	@Qualifier("basicModelJAXBContext")
	private JAXBContext basicModelJAXBContext;

	public String marshaller(BasicModel model) throws JAXBException {
		return JAXBUtility.marshallAsString(basicModelJAXBContext, model);
	}

	public BasicModel unmarshaller(String xmlString) throws JAXBException {
		return JAXBUtility.unmarshallWithString(basicModelJAXBContext, xmlString, BasicModel.class);
	}
}
