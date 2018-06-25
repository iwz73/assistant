package idv.hsiehpinghan.clouderamanagerapiassistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cloudera.api.DataView;
import com.cloudera.api.model.ApiClusterList;
import com.cloudera.api.v14.ClustersResourceV14;
import com.cloudera.api.v14.RootResourceV14;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.clouderamanagerapiassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ClustersResourceTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RootResourceV14 rootResource;
	private ClustersResourceV14 clustersResource;

	@BeforeClass
	public void beforeClass() {
		clustersResource = rootResource.getClustersResource();
	}

	@Test
	public void apiClusterListTest() throws JsonProcessingException {
		ApiClusterList apiClusterList = clustersResource.readClusters(DataView.FULL_WITH_HEALTH_CHECK_EXPLANATION);
		System.err.println("----------------<<apiClusterListTest>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiClusterList));
	}
}
