package idv.hsiehpinghan.clouderamanagerapiassistant.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cloudera.api.model.ApiVersionInfo;
import com.cloudera.api.v14.ClouderaManagerResourceV14;
import com.cloudera.api.v14.RootResourceV14;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.clouderamanagerapiassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ClouderaManagerResourceTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RootResourceV14 rootResource;
	private ClouderaManagerResourceV14 clouderaManagerResource;

	@BeforeClass
	public void beforeClass() {
		clouderaManagerResource = rootResource.getClouderaManagerResource();
	}

	@Test
	public void apiVersionInfoTest() throws Exception {
		ApiVersionInfo apiVersionInfo = clouderaManagerResource.getVersion();
		System.err.println("----------------<<apiVersionInfo>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiVersionInfo));
	}
}
