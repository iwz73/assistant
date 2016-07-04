package idv.hsiehpinghan.jerseyassistant.resourceconfig;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import idv.hsiehpinghan.jerseyassistant.resource.BasicResource;

//@ApplicationPath("aaa")
public class MyResourceConfig extends ResourceConfig {
	public MyResourceConfig() {
		// packages("idv.hsiehpinghan.jerseyassistant.resource");
		register(BasicResource.class);
		register(RequestContextFilter.class);
		register(MultiPartFeature.class);
		register(JacksonFeature.class);
	}
}
