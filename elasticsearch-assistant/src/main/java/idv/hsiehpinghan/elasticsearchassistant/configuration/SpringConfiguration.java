package idv.hsiehpinghan.elasticsearchassistant.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
@PropertySource("classpath:/elasticsearch_assistant.property")
@ComponentScan(basePackages = { "idv.hsiehpinghan.elasticsearchassistant" })
public class SpringConfiguration {
	// private Logger logger = Logger.getLogger(this.getClass().getName());
	@Autowired
	private Environment environment;

	@Bean
	public Client client() throws UnknownHostException {
		String host = environment.getRequiredProperty("host");
		Integer port = environment.getRequiredProperty("port", Integer.class);
		TransportClient transportClient = TransportClient.builder().build();
		transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
		return transportClient;
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		return objectMapper;
	}
}