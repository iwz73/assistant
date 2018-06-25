package idv.hsiehpinghan.clouderamanagerapiassistant;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cloudera.api.model.ApiMetricSchemaList;
import com.cloudera.api.model.ApiTimeSeriesEntityAttributeList;
import com.cloudera.api.model.ApiTimeSeriesEntityTypeList;
import com.cloudera.api.model.ApiTimeSeriesResponseList;
import com.cloudera.api.v11.TimeSeriesResourceV11;
import com.cloudera.api.v14.RootResourceV14;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import idv.hsiehpinghan.clouderamanagerapiassistant.configuration.SpringConfiguration;

@ContextConfiguration(classes = { SpringConfiguration.class })
public class TimeSeriesResourceTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private RootResourceV14 rootResource;
	private TimeSeriesResourceV11 timeSeriesResource;

	@BeforeClass
	public void beforeClass() {
		timeSeriesResource = rootResource.getTimeSeriesResource();
	}

	@Test
	public void apiTimeSeriesEntityTypeListTest() throws Exception {
		ApiTimeSeriesEntityTypeList apiTimeSeriesEntityTypeList = timeSeriesResource.getEntityTypes();
		System.err.println("----------------<<apiTimeSeriesEntityTypeListTest>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiTimeSeriesEntityTypeList));
	}

	@Test
	public void apiTimeSeriesEntityAttributeListTest() throws Exception {
		ApiTimeSeriesEntityAttributeList apiTimeSeriesEntityAttributeList = timeSeriesResource
				.getEntityTypeAttributes();
		System.err.println("----------------<<apiTimeSeriesEntityAttributeListTest>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiTimeSeriesEntityAttributeList));
	}

	@Test
	public void apiMetricSchemaListTest() throws Exception {
		ApiMetricSchemaList apiMetricSchemaList = timeSeriesResource.getMetricSchema();
		System.err.println("----------------<<apiMetricSchemaListTest>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiMetricSchemaList));
	}

	@Test
	public void responseTest() throws Exception {
		responseTest_cluster();
		responseTest_service();
	}

	private void responseTest_cluster() throws JsonParseException, JsonMappingException, IOException {
		String query = "SELECT kafka_offsets_requests_1min_rate_across_kafka_brokers WHERE category = CLUSTER AND entityName = \"1\"";
		String from = "2018-06-25T09:00:00.000Z";
		String to = "2018-06-25T09:30:00.000Z";
		ApiTimeSeriesResponseList apiTimeSeriesResponseList = timeSeriesResource.queryTimeSeries(query, from, to);
		System.err.println("----------------<<responseTest_service>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiTimeSeriesResponseList));
	}

	private void responseTest_service() throws JsonParseException, JsonMappingException, IOException {
		String query = "SELECT total_kafka_messages_received_rate_across_kafka_brokers WHERE category = SERVICE AND entityName = \"kafka\"";
		String from = "2018-06-25T09:00:00.000Z";
		String to = "2018-06-25T09:30:00.000Z";
		ApiTimeSeriesResponseList apiTimeSeriesResponseList = timeSeriesResource.queryTimeSeries(query, from, to);
		System.err.println("----------------<<responseTest_service>>----------------");
		System.err.println(objectMapper.writeValueAsString(apiTimeSeriesResponseList));
	}
}
