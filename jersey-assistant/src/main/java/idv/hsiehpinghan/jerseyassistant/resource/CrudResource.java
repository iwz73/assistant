package idv.hsiehpinghan.jerseyassistant.resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import idv.hsiehpinghan.jerseyassistant.vo.CrudVo;

@Path("cruds")
public class CrudResource {
	private static Map<Integer, CrudVo> vos = new HashMap<>();

	/**
	 * curl -X POST -H "Content-Type: application/json" -d '{"id":1,"message":
	 * "createCrud"}' http://localhost:8080/jersey-assistant/cruds
	 * 
	 * @param vo
	 * @return
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response createCrud(CrudVo vo) {
		Integer id = vo.getId();
		vos.put(id, vo);
		return Response.status(Response.Status.CREATED)
				.entity("CrudVo(" + id + ") has been created")
				.header("location", "http://localhost:8080/jersey-assistant/cruds/" + id).build();
	}

	/**
	 * http://localhost:8080/jersey-assistant/cruds/1
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public CrudVo readCrud(@PathParam("id") int id) {
		return vos.get(id);
	}

	/**
	 * http://localhost:8080/jersey-assistant/cruds
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CrudVo> readCruds() {
		List<CrudVo> results = new ArrayList<>(vos.size());
		for (Map.Entry<Integer, CrudVo> ent : vos.entrySet()) {
			results.add(ent.getValue());
		}
		return results;
	}

	/**
	 * curl -X PUT -H "Content-Type: application/json" -d '{"id":1,"message":
	 * "updateCrud"}' http://localhost:8080/jersey-assistant/cruds
	 * @param vo
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCrud(CrudVo vo) {
		Integer id = vo.getId();
		vos.put(id, vo);
		return Response.status(Response.Status.CREATED)
				.entity("CrudVo(" + id + ") has been updated")
				.header("location", "http://localhost:8080/jersey-assistant/cruds/" + id).build();
	}

	/**
	 * curl -X DELETE http://localhost:8080/jersey-assistant/cruds
	 * 
	 * @return
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCruds() {
		vos.clear();
	    return Response.status(Response.Status.NO_CONTENT)
	            .entity("All CrudVo have been removed.").build();
	}
	
	/**
	 * curl -X DELETE http://localhost:8080/jersey-assistant/cruds/1
	 * 
	 * @return
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCrud(@PathParam("id") int id) {
		vos.remove(id);
	    return Response.status(Response.Status.NO_CONTENT)
	            .entity("CrudVo(" + id + ") have been removed.").build();
	}
}
