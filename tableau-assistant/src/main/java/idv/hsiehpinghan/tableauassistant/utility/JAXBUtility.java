package idv.hsiehpinghan.tableauassistant.utility;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtility {
	public static String marshallAsString(JAXBContext jAXBContext, Object model) throws JAXBException {
		Marshaller marshaller = jAXBContext.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://tableau.com/api http://tableau.com/api/ts-api-2.6.xsd");
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(model, stringWriter);
		return stringWriter.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshallWithString(JAXBContext jAXBContext, String xmlString, Class<T> clazz)
			throws JAXBException {
		Unmarshaller unmarshaller = jAXBContext.createUnmarshaller();
		StringReader stringReader = new StringReader(xmlString);
		return (T) unmarshaller.unmarshal(stringReader);
	}
}
