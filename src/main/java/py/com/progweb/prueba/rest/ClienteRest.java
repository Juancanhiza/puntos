package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.ClienteDAO;
import py.com.progweb.prueba.model.Cliente;

@Path("cliente")
@Consumes("application/json")
@Produces("application/json")
public class ClienteRest {
	@Inject
	private ClienteDAO clienteDAO;
	
	@GET
	@Path("/")
	public Response listar() {
		return Response.ok(clienteDAO.listar()).build();
	}
	
	@POST
	@Path("/")
	public Response agregar(Cliente entity) {
		this.clienteDAO.agregar(entity);
		return Response.ok().build();
	}
}
