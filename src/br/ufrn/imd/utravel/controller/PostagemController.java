package br.ufrn.imd.utravel.controller;

import br.ufrn.imd.utravel.model.Postagem;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.security.Secured;
import br.ufrn.imd.utravel.service.PostagemService;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.annotations.jaxrs.FormParam;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

@Api("Postagem")
@Stateless
@Path("/postagem")
public class PostagemController {
    @EJB
    private PostagemService service;

    @POST
    @Path("/salvar")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response salvar(MultipartFormDataInput formDataInput) throws IOException {
        String titulo = formDataInput.getFormDataMap().get("titulo").get(0).getBodyAsString();
        String descricao = formDataInput.getFormDataMap().get("descricao").get(0).getBodyAsString();
        int viagemId = Integer.parseInt(formDataInput.getFormDataMap().get("viagemId").get(0).getBodyAsString());
        String nomeArquivo = formDataInput.getFormDataMap().get("nomeArquivo").get(0).getBodyAsString();
        Viagem viagem = new Viagem();
        viagem.setId(viagemId);
        Postagem postagem = new Postagem(titulo, descricao, viagem);
        InputStream inputStream = formDataInput.getFormDataMap().get("arquivo").get(0).getBody(InputStream.class, null);
        return Response.ok(service.salvar(postagem, inputStream, nomeArquivo)).build();
    }
}
