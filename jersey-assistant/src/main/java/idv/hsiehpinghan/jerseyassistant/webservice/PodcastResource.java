package idv.hsiehpinghan.jerseyassistant.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
@Path("/podcasts")
public class PodcastResource {
	@Autowired
	private Test test;
	
	@GET
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.TEXT_HTML })
	public Response createPodcast() {
		test.test();
		
//	    Long createPodcastId = podcastService.createPodcast(podcast);
	    return Response.status(Response.Status.CREATED)// 201
	            .entity("A new podcast has been created")
	            .header("Location",
	                    "http://localhost:8888/demo-rest-jersey-spring/podcasts/"
	                            + String.valueOf(333)).build();
	}
}
