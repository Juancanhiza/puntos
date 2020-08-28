package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.BolsaPuntosDAO;
import py.com.progweb.prueba.model.BolsaPuntos;

@Path("bolsas")
@Consumes("application/json")
@Produces("application/json")
public class BolsaPuntosRest {
	@Inject
	private BolsaPuntosDAO bolsaPuntosDAO;
	
	@GET
	@Path("/")
	public Response listar() {
		return Response.ok(bolsaPuntosDAO.listar()).build();
	}
	
	@POST
	@Path("/")
	public Response agregar(BolsaPuntos entity) {
		this.bolsaPuntosDAO.agregar(entity);
		return Response.ok().build();
	}
}
