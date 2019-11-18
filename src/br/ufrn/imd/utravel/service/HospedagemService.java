package br.ufrn.imd.utravel.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import br.ufrn.imd.utravel.dto.HospedagemDTO;
import br.ufrn.imd.utravel.model.Empresa;
import br.ufrn.imd.utravel.model.Endereco;
import br.ufrn.imd.utravel.model.Evento;
import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.model.Localizacao;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.HospedagemRepository;

@Stateless
public class HospedagemService {
    @Inject
    private HospedagemRepository repository;
    
    @Inject
    private ViagemService viagemService;
    
    @Inject
    private EnderecoService enderecoService;
    
    @Inject
    private LocalizacaoService localizacaoService;
    
    @Inject
    private EmpresaService empresaService;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Hospedagem> buscarTodos() {
        return repository.buscarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Hospedagem buscarPorId(long id) {
        return repository.buscarPorId(id);
    }

    public Hospedagem salvar(HospedagemDTO hospedagemDTO, Usuario usuario) throws ParseException {
    	Viagem viagem = viagemService.buscarPorId(hospedagemDTO.getViagem());
    	
    	if (viagem == null) {
			throw new EntityNotFoundException("Não foi possível encontrar uma viagem com este id.");
		}
    	
    	viagemService.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	Endereco endereco = enderecoService.buscarEndereco(hospedagemDTO.getEnderecoDTO());
    	
    	if (endereco == null) {
			Localizacao localizacao = localizacaoService.buscarLocalizacao(hospedagemDTO.getEnderecoDTO());
			
			if (localizacao == null) {
				localizacao = new Localizacao();
				
				localizacao.setCidade(hospedagemDTO.getEnderecoDTO().getCidade());
				localizacao.setEstado(hospedagemDTO.getEnderecoDTO().getEstado());
				localizacao.setPais(hospedagemDTO.getEnderecoDTO().getPais());
				
				localizacao = localizacaoService.salvar(localizacao);
			}
			
			endereco = new Endereco();
			
			endereco.setEndereco(hospedagemDTO.getEnderecoDTO().getEndereco());
			endereco.setLocalizacao(localizacao);
		}
    	
    	Empresa empresa = null;
    	if (hospedagemDTO.getEmpresa() != 0) {
			empresa = empresaService.buscarPorId(hospedagemDTO.getEmpresa());
		}
    	
    	Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(hospedagemDTO.getDataHospedagem());
        Date dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(hospedagemDTO.getDataSaida());
	
    	Hospedagem hospedagem = new Hospedagem();
    	
    	hospedagem.setEndereco(endereco);
    	hospedagem.setCodigo(hospedagemDTO.getCodigo());
    	hospedagem.setQuantidadeQuartos(hospedagemDTO.getQuantidadeQuartos());
    	hospedagem.setTipoHospedagem(hospedagem.getTipoHospedagem());
    	hospedagem.setEmpresa(empresa);
    	
    	Evento evento = new Evento();
    	evento.setDataFinal(dataFim);
    	evento.setDataInicio(dataInicio);
    	evento.setValorEstimado(hospedagemDTO.getValorGasto());
    	evento.setHospedagem(hospedagem);
    	evento.setViagem(viagem);
    	
    	hospedagem.getEventos().add(evento);
    	
    	return repository.salvar(hospedagem);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String remover(Hospedagem hospedagem) {
        repository.remover(hospedagem);

        return "Removido com sucesso";
    }
}
