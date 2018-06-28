package idv.hsiehpinghan.clouderamanagerapiassistant.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cloudera.api.DataView;
import com.cloudera.api.model.ApiCluster;
import com.cloudera.api.model.ApiClusterList;
import com.cloudera.api.model.ApiServiceList;
import com.cloudera.api.v14.ClustersResourceV14;
import com.cloudera.api.v14.RootResourceV14;
import com.cloudera.api.v14.ServicesResourceV14;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.clouderamanagerapiassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class ServicesResourceTest extends AbstractTestNGSpringContextTests {
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
	public void apiClusterListTest() throws Exception {
		ApiClusterList apiClusterList = clustersResource.readClusters(DataView.FULL_WITH_HEALTH_CHECK_EXPLANATION);
		System.err.println("----------------<<apiClusterListTest>>----------------");
		for(ApiCluster apiCluster : apiClusterList) {
			String clusterName = apiCluster.getName();
			System.err.println("----------------<" + clusterName + ">----------------");
			ServicesResourceV14 servicesResource = clustersResource.getServicesResource(clusterName);
			ApiServiceList apiServiceList = servicesResource.readServices(DataView.FULL_WITH_HEALTH_CHECK_EXPLANATION);
			System.err.println(objectMapper.writeValueAsString(apiServiceList));
		}
	}
}
