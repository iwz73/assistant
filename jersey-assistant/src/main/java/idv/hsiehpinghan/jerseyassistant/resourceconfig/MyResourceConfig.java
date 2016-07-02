package idv.hsiehpinghan.jerseyassistant.resourceconfig;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import idv.hsiehpinghan.jerseyassistant.webservice.PodcastResource;

//@ApplicationPath("rest")
public class MyResourceConfig extends ResourceConfig {
	public MyResourceConfig() {
		// packages("idv.hsiehpinghan.jerseyassistant.webservice");
		register(RequestContextFilter.class);
		register(PodcastResource.class);
//		register(JacksonFeature.class);
	}
}
