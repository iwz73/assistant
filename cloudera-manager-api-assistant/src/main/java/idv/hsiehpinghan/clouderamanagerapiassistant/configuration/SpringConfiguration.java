package idv.hsiehpinghan.clouderamanagerapiassistant.configuration;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.cloudera.api.ApiRootResource;
import com.cloudera.api.ClouderaManagerClientBuilder;
import com.cloudera.api.v14.RootResourceV14;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@PropertySource("classpath:/cloudera_manager_api_assistant.property")
@Configuration("clouderaManagerApiAssistantSpringConfiguration")
@ComponentScan(basePackages = { "idv.hsiehpinghan.clouderamanagerapiassistant" })
public class SpringConfiguration implements InitializingBean {
	@Value("${host_name}")
	private String hostname;
	@Value("${port}")
	private Integer port;
	@Value("${user_name}")
	private String username;
	@Value("${password}")
	private String password;

	@Override
	public void afterPropertiesSet() throws Exception {
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password.toCharArray());
			}
		});
	}

	@Bean
	public RootResourceV14 rootResource() {
		ApiRootResource root = new ClouderaManagerClientBuilder().withHost(hostname).withPort(port)
				.withUsernamePassword(username, password).setThreadSafe(true).build();
		return root.getRootV14();
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		return objectMapper;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}