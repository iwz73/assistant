package idv.hsiehpinghan.jaxbassistant.assistant;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import idv.hsiehpinghan.jaxbassistant.model.BasicModel;

@Component
public class BasicAssistant {
	@Autowired
	@Qualifier("basicModelJAXBContext")
	private JAXBContext basicModelJAXBContext;

	public String marshaller(BasicModel model) throws JAXBException {
		Marshaller marshaller = basicModelJAXBContext.createMarshaller();
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(model, stringWriter);
		return stringWriter.toString();
	}

	public BasicModel unmarshaller(String xmlString) throws JAXBException {
		Unmarshaller unmarshaller = basicModelJAXBContext.createUnmarshaller();
		StringReader stringReader = new StringReader(xmlString);
		return (BasicModel) unmarshaller.unmarshal(stringReader);
	}
}
