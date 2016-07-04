package idv.hsiehpinghan.jerseyassistant.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.core.io.ClassPathResource;

import idv.hsiehpinghan.jerseyassistant.utility.StreamUtility;

@Path("basics")
public class BasicResource {

	/**
	 * http://localhost:8080/jersey-assistant/basics
	 * 
	 * @return
	 */
	@GET
	public String basicGet() {
		return "basicGet";
	}

	/**
	 * http://localhost:8080/jersey-assistant/basics/uriInfoGet
	 * 
	 * @param uriInfo
	 * @return
	 */
	@GET
	@Path("/uriInfoGet")
	public String uriInfoGet(@Context UriInfo uriInfo) {
		return "uriInfoGet : " + uriInfo.getPath();

	}

	/**
	 * http://localhost:8080/jersey-assistant/basics/pathParamGet/path_param
	 * 
	 * @param pathParam
	 * @return
	 */
	@GET
	@Path("pathParamGet/{pathParam : [a-zA-Z_]+}")
	public String pathParamGet(@PathParam("pathParam") String pathParam) {
		return "pathParamGet : " + pathParam;
	}

	/**
	 * http://localhost:8080/jersey-assistant/basics/queryParamGet
	 * http://localhost:8080/jersey-assistant/basics/queryParamGet?queryParam=3
	 * 
	 * @param queryParam
	 * @return
	 */
	@GET
	@Path("queryParamGet")
	public String queryParamGet(@QueryParam("queryParam") @DefaultValue("1") int queryParam) {
		return "queryParamGet : " + queryParam;
	}

	/**
	 * http://localhost:8080/jersey-assistant/basics/matrixParamGet;
	 * matrixParam_0=matrix_param_0;matrixParam_1=1
	 * 
	 * @param matrixParam_0
	 * @param matrixParam_1
	 * @return
	 */
	@GET
	@Path("matrixParamGet")
	public String matrixParamGet(@MatrixParam("matrixParam_0") String matrixParam_0,
			@MatrixParam("matrixParam_1") int matrixParam_1) {
		return "matrixParamGet : matrixParam_0(" + matrixParam_0 + "), matrixParam_1(" + matrixParam_1 + ")";
	}

	/**
	 * http://localhost:8080/jersey-assistant/basics/headerParamGet
	 * 
	 * @param headerParam
	 * @return
	 */
	@GET
	@Path("headerParamGet")
	public String headerParamGet(@HeaderParam("user-agent") String headerParam) {
		return "headerParamGet : " + headerParam;
	}

	/**
	 * http://localhost:8080/jersey-assistant/basics/httpHeadersGet
	 * 
	 * @param httpHeaders
	 * @return
	 */
	@GET
	@Path("httpHeadersGet")
	public String httpHeadersGet(@Context HttpHeaders httpHeaders) {
		return "httpHeadersGet : " + httpHeaders.getRequestHeader("user-agent").get(0);
	}

	/**
	 * http://localhost:8080/jersey-assistant/basics/fileGet
	 * 
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("fileGet")
	@Produces("text/plain")
	public Response fileGet() throws IOException {
		File file = new ClassPathResource("fileGet.txt").getFile();
		ResponseBuilder response = Response.ok(file);
		response.header("Content-Disposition", "attachment; filename=\"fileGet.txt\"");
		return response.build();

	}

	/**
	 * using formParamPost.html
	 * 
	 * @param formParam
	 * @return
	 */
	@POST
	@Path("formParamPost")
	public String formParamPost(@FormParam("formParam") @DefaultValue("formParam") String formParam) {
		return "formParamPost : " + formParam;
	}

	/**
	 * using uploadFile.html
	 * 
	 * @param inputStream
	 * @param formDataContentDisposition
	 * @return
	 * @throws IOException
	 */
	@POST
	@Path("uploadFile")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream inputStream,
			@FormDataParam("file") FormDataContentDisposition formDataContentDisposition) throws IOException {
		String filePath = "/tmp/" + formDataContentDisposition.getFileName();
		StreamUtility.writeToFile(inputStream, filePath);
		String output = "file uploaded to : " + filePath;
		return Response.status(200).entity(output).build();
	}
}
