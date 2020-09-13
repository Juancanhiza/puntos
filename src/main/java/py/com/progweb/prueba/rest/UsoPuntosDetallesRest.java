package py.com.progweb.prueba.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import py.com.progweb.prueba.ejb.UsoPuntosDetallesBean;
import py.com.progweb.prueba.model.UsoPuntosDetalle;

@Path("usos/detalles")
@Consumes("application/json")
@Produces("application/json")
public class UsoPuntosDetallesRest {

    @Inject
    private UsoPuntosDetallesBean usoPuntosDetallesBean;

    @GET
    @Path("/")
    public Response listar() {
        return Response.ok(usoPuntosDetallesBean.listar()).build();
    }

    @POST
    @Path("/")
    public Response agregar(UsoPuntosDetalle entity) {
        this.usoPuntosDetallesBean.agregar(entity);
        return Response.ok().build();
    }
}
