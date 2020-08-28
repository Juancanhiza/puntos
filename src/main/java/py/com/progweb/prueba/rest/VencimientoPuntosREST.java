package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.VencimientoPuntosDAO;
import py.com.progweb.prueba.model.VencimientoPuntos;

@Path("vencimientos")
@Consumes("application/json")
@Produces("application/json")
public class VencimientoPuntosREST {
	@Inject
	private VencimientoPuntosDAO vencimientoPuntosDAO;
	
	@GET
	@Path("/")
	public Response listar() {
		return Response.ok(vencimientoPuntosDAO.listar()).build();
	}
	
	@POST
	@Path("/")
	public Response agregar(VencimientoPuntos entity) {
		this.vencimientoPuntosDAO.agregar(entity);
		return Response.ok().build();
	}
}
