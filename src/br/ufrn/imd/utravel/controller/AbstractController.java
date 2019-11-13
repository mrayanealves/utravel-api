package br.ufrn.imd.utravel.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.ufrn.imd.utravel.model.AbstractModel;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.AbstractService;

@Secured
public abstract class AbstractController<T extends AbstractModel> {
    protected abstract AbstractService<T> service();

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    @Secured
    public Response buscarTodos() {
        return Response.ok(service().buscarTodos()).build();
    }

    @GET
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response buscarPorId(@PathParam("id") long id) {
        return Response.ok(service().buscarPorId(id)).build();
    }

    @POST
    @Consumes("application/json; charset=UTF-8")
    @Produces("application/json; charset=UTF-8")
    @Path("/")
    @Secured
    public Response salvar(T entity) {
        return Response.ok(service().salvar(entity)).build();
    }

    @DELETE
    @Produces("application/json; charset=UTF-8")
    @Path("/{id}")
    @Secured
    public Response remover(@PathParam("id") long id) {
        T entity = service().buscarPorId(id);

        if (entity == null) {
            return Response.status(404).build();
        }

        return Response.ok(service().remover(entity)).build();
    }
}
