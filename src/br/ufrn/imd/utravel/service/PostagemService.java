package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.model.Postagem;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.PostagemRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@Stateless
public class PostagemService extends AbstractService<Postagem> {
    @Inject
    private PostagemRepository repository;
    @EJB
    private ArquivoService arquivoService;

    @Override
    protected AbstractRepository<Postagem> repository() {
        return this.repository;
    }

    public Postagem salvar(Postagem entity, InputStream inputStream, String nomeArquivo) throws IOException {
        Postagem postagem = super.salvar(entity);
        arquivoService.salvar(inputStream, Paths.get("C:\\temp\\" + postagem.getId() + "_" + nomeArquivo));
        return postagem;
    }
}
