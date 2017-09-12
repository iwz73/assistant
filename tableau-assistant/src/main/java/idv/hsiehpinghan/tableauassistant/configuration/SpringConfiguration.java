package idv.hsiehpinghan.tableauassistant.configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import idv.hsiehpinghan.tableauassistant.model.SignInRequest;
import idv.hsiehpinghan.tableauassistant.model.SignInResponse;

@Configuration("tableauAssistantSpringConfiguration")
@PropertySource("classpath:/tableau_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.tableauassistant" })
public class SpringConfiguration {
	@Bean
	public JAXBContext signInRequestJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(SignInRequest.class);
	}

	@Bean
	public JAXBContext signInResponseJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(SignInResponse.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
