package idv.hsiehpinghan.clouderamanagerapiassistant.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cloudera.api.DataView;
import com.cloudera.api.model.ApiHostList;
import com.cloudera.api.v10.HostsResourceV10;
import com.cloudera.api.v14.RootResourceV14;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.clouderamanagerapiassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class HostsResourceTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RootResourceV14 rootResource;
	private HostsResourceV10 hostsResource;

	@BeforeClass
	public void beforeClass() {
		hostsResource = rootResource.getHostsResource();
	}

	@Test
	public void apiHostListTest() throws JsonProcessingException {
		ApiHostList apiHostList = hostsResource.readHosts(DataView.FULL_WITH_HEALTH_CHECK_EXPLANATION);
		System.err.println("----------------<<apiHostListTest>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiHostList));
	}
}
