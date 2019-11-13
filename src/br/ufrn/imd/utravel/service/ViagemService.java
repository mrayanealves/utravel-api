package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.dto.ViagemDTO;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.ViagemRepository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Stateless
public class ViagemService {
    @Inject
    private ViagemRepository viagemRepository;

    @Inject
    private UsuarioService usuarioService;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Viagem> buscarTodos() {
        return viagemRepository.buscarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Viagem buscarPorId(long id) {
        return viagemRepository.buscarPorId(id);
    }

    public Viagem salvar(ViagemDTO viagemDTO, String email) throws Exception {
        Viagem viagem = new Viagem();

        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(viagemDTO.getDataInicio());
        Date dataFim = null;

        if ((viagemDTO.getDataFim() != null) && (!viagemDTO.getDataFim().equals(""))) {
            dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(viagemDTO.getDataFim());
        }

        Usuario usuario = usuarioService.usuarioLogado(email);
        System.out.println(usuario);

        viagem.setTitulo(viagemDTO.getTitulo());
        viagem.setObjetivo(viagemDTO.getObjetivo());
        viagem.setDataInicio(dataInicio);
        viagem.setDataFim(dataFim);
        viagem.getUsuarios().add(usuario);

        return viagemRepository.salvar(viagem);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String remover(Viagem viagem) {
        viagemRepository.remover(viagem);

        return "Removido com sucesso";
    }
}
