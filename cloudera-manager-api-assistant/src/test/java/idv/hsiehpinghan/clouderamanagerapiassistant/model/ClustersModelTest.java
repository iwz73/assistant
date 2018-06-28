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
public class ClustersModelTest extends AbstractTestNGSpringContextTests {
	private String clustersUrl = null;
	@Value("${host_name}")
	private String hostname;
	@Value("${port}")
	private Integer port;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() {
		clustersUrl = String.format("http://%s:%d/api/v14/clusters?view=FULL_WITH_HEALTH_CHECK_EXPLANATION", hostname,
				port);
	}

	@Test
	public void clustersModel() throws Exception {
		HttpURLConnection connection = HttpUtility.getConnection(clustersUrl);
		ClustersModel clustersModel = objectMapper.readValue(connection.getInputStream(), ClustersModel.class);
		System.err.println("----------------<<clustersModel>>----------------");
		System.err.println(clustersModel);
	}
}
