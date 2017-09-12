package idv.hsiehpinghan.tableauassistant.configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import idv.hsiehpinghan.tableauassistant.model.SignInRequestBody;
import idv.hsiehpinghan.tableauassistant.model.SignInResponseBody;

@Configuration("tableauAssistantSpringConfiguration")
@PropertySource("classpath:/tableau_assistant_server.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.tableauassistant" })
public class SpringConfiguration {
	@Bean
	public JAXBContext signInRequestBodyJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(SignInRequestBody.class);
	}

	@Bean
	public JAXBContext signInResponseBodyJAXBContext() throws JAXBException {
		return JAXBContext.newInstance(SignInResponseBody.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
