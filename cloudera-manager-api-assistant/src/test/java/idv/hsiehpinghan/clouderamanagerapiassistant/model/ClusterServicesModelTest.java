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
public class ClusterServicesModelTest extends AbstractTestNGSpringContextTests {
	private String clusterServicesUrl = null;
	@Value("${host_name}")
	private String hostname;
	@Value("${port}")
	private Integer port;
	@Autowired
	private ObjectMapper objectMapper;

	@BeforeClass
	public void beforeClass() {
		clusterServicesUrl = String.format("http://%s:%d/api/v14/clusters/Cluster%%201/services", hostname, port);
	}

	@Test
	public void clusterServicesModel() throws Exception {
		HttpURLConnection connection = HttpUtility.getConnection(clusterServicesUrl);
		ClusterServicesModel clusterServicesModel = objectMapper.readValue(connection.getInputStream(),
				ClusterServicesModel.class);
		System.err.println("----------------<<clusterServicesModel>>----------------");
		System.err.println(clusterServicesModel);
	}
}
