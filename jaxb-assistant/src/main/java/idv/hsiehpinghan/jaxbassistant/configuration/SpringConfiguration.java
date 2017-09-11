package idv.hsiehpinghan.jaxbassistant.configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import idv.hsiehpinghan.jaxbassistant.model.BasicModel;

@Configuration("jaxbAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.jaxbassistant" })
public class SpringConfiguration {
	@Bean
	public JAXBContext basicModelJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(BasicModel.class);
	}
}