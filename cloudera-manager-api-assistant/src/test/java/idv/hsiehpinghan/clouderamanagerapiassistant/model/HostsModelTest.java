package idv.hsiehpinghan.clouderamanagerapiassistant.model;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.clouderamanagerapiassistant.configuration.SpringConfiguration;
import idv.hsiehpinghan.clouderamanagerapiassistant.utility.HttpUtility;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class HostsModelTest extends AbstractTestNGSpringContextTests {
	private String hostsUrl = null;
	@Value("${host_name}")
	private String hostname;
	@Value("${port}")
	private Integer port;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() {
		hostsUrl = String.format("http://%s:%d/api/v14/hosts?view=FULL_WITH_HEALTH_CHECK_EXPLANATION", hostname, port);
	}

	@Test
	public void hostsModel() throws Exception {
		HttpURLConnection connection = HttpUtility.getConnection(hostsUrl);
		HostsModel hostsModel = objectMapper.readValue(connection.getInputStream(), HostsModel.class);
		System.err.println("----------------<<hostsModel>>----------------");
		System.err.println(hostsModel);
	}
}
