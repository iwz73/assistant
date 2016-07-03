package idv.hsiehpinghan.jerseyassistant.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
 
@Path("hello")
public class HelloRS {
 
    @GET
    public String sayHelloWorld() {
        return "Hello world";
    }   
 
    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        return "Hello, " + name;
    }
}
