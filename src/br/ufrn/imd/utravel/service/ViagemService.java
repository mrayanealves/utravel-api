package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.dto.GrupoUsuariosDTO;
import br.ufrn.imd.utravel.dto.HospedagemDTO;
import br.ufrn.imd.utravel.dto.RestauranteDTO;
import br.ufrn.imd.utravel.dto.ViagemDTO;
import br.ufrn.imd.utravel.exception.InvalidOperationException;
import br.ufrn.imd.utravel.model.Evento;
import br.ufrn.imd.utravel.model.Hospedagem;
import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.model.Viagem;
import br.ufrn.imd.utravel.repository.ViagemRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

@Stateless
public class ViagemService {
    @Inject
    private ViagemRepository viagemRepository;
    
    @Inject 
    private UsuarioService usuarioService;
    
    @Inject 
    private HospedagemService hospedagemService;
    
    @Inject
    private RestauranteService restauranteService;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public List<Viagem> buscarTodos() {
        return viagemRepository.buscarTodos();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Viagem buscarPorId(long id) {
        return viagemRepository.buscarPorId(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Viagem salvar(ViagemDTO viagemDTO, Usuario usuario) throws Exception {
        Viagem viagem = new Viagem();

        Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(viagemDTO.getDataInicio());
        Date dataFim = null;

        if ((viagemDTO.getDataFim() != null) && (!viagemDTO.getDataFim().equals(""))) {
            dataFim = new SimpleDateFormat("dd/MM/yyyy").parse(viagemDTO.getDataFim());
        }

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
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Viagem adicionarParticipantes(long id, GrupoUsuariosDTO grupoUsuariosDTO, 
    									 Usuario usuario) {
    	Viagem viagem = this.buscarPorId(id);
    	
    	this.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	if (grupoUsuariosDTO.getEmailUsuarios().isEmpty()) {
			throw new InvalidOperationException("Não há nenhum usuário para ser adicionado.");
		}
    	
    	Set<Usuario> novosUsuariosAutores = new HashSet<Usuario>();
    	
    	for(String emailUsuario : grupoUsuariosDTO.getEmailUsuarios()) {
    		Usuario usuarioParaAdicionar = usuarioService.buscarUsuarioPorEmail(emailUsuario);
    		
    		if (usuarioParaAdicionar == null) {
				StringBuilder stringBuilder = new StringBuilder();
				
				stringBuilder.append("Não foi possível localizar o usuário de email ")
							 .append(emailUsuario)
							 .append(".");
				
				throw new EntityNotFoundException(stringBuilder.toString());
			}
    		
    		novosUsuariosAutores.add(usuarioParaAdicionar);
    	}
    	
    	viagem.getUsuarios().addAll(novosUsuariosAutores);
    	
    	return viagemRepository.salvar(viagem);
    }
    
    public Hospedagem adicionarHospedagem(long id, HospedagemDTO hospedagemDTO, Usuario usuario) throws ParseException {
    	Viagem viagem = this.buscarPorId(id);
    	
    	this.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	return hospedagemService.salvar(hospedagemDTO, usuario, viagem);
	}
    
    public Restaurante adicionarRestaurantes(long id, RestauranteDTO restauranteDTO, Usuario usuario) throws ParseException {
    	Viagem viagem = this.buscarPorId(id);
    	
    	this.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	Restaurante restaurante = restauranteService.buscarPorId(restauranteDTO.getRestaurante());
    	
    	Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(restauranteDTO.getDataIdaPrevista());
    	
    	Evento evento = new Evento();
    	evento.setDataInicio(dataInicio);
    	evento.setValorEstimado(restauranteDTO.getValorGastoPrevisto());
    	evento.setRestaurante(restaurante);
    	evento.setViagem(viagem);
    	
    	restaurante.getEventos().add(evento);
    	
    	return restauranteService.salvar(restaurante);
	}
    
    public void verificarSeUsuarioLogadoGerenciaViagem(Viagem viagem, Usuario usuario) {
		if (!viagem.getUsuarios().contains(usuario)) {
			throw new InvalidOperationException("Essa viagem não te pertence");
		}
	}
}
