package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.dto.GrupoUsuariosDTO;
import br.ufrn.imd.utravel.dto.ReservaDTO;
import br.ufrn.imd.utravel.dto.TransporteDTO;
import br.ufrn.imd.utravel.dto.TurismoDTO;
import br.ufrn.imd.utravel.dto.AlimentacaoDTO;
import br.ufrn.imd.utravel.dto.ViagemDTO;
import br.ufrn.imd.utravel.exception.InvalidOperationException;
import br.ufrn.imd.utravel.model.Evento;
import br.ufrn.imd.utravel.model.Passeio;
import br.ufrn.imd.utravel.model.Reserva;
import br.ufrn.imd.utravel.model.Restaurante;
import br.ufrn.imd.utravel.model.Transporte;
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
    private RestauranteService restauranteService;
    
    @Inject
    private ReservaService reservaService;
    
    @Inject
    private PasseioService passeioService;
    
    @Inject
    private TransporteService transporteService;

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
    
    public Reserva adicionarReservaHospedagem(long id, ReservaDTO reservaDTO, Usuario usuario) throws ParseException {
    	Viagem viagem = this.buscarPorId(id);
    	
    	this.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	return reservaService.salvar(reservaDTO, viagem);
	}
    
    public Restaurante adicionarRestaurantes(long id, AlimentacaoDTO alimentacaoDTO, Usuario usuario) throws ParseException {
    	Viagem viagem = this.buscarPorId(id);
    	
    	this.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	Restaurante restaurante = new Restaurante();
    	
    	if (alimentacaoDTO.getRestauranteDTO().getIdRestaurante() != 0) {
    		restaurante = restauranteService.buscarPorId(alimentacaoDTO.getRestauranteDTO().getIdRestaurante());
		} else {
			restaurante = restauranteService.montarRestaurante(alimentacaoDTO.getRestauranteDTO());
			
			restaurante = restauranteService.salvar(restaurante);
		}
    	
    	Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(alimentacaoDTO.getDataIdaPrevista());
    	
    	Evento evento = new Evento();
    	
    	evento.setTitulo("Ida ao restaurante");
    	evento.setDataInicio(dataInicio);
    	evento.setValorEstimado(alimentacaoDTO.getValorGastoPrevisto());
    	evento.setRestaurante(restaurante);
    	evento.setViagem(viagem);
    	
    	restaurante.getEventos().add(evento);
    	
    	return restauranteService.salvar(restaurante);
	}
    
    public Passeio adicionarPasseios(long id, TurismoDTO turismoDTO, Usuario usuario) throws ParseException {
    	Viagem viagem = this.buscarPorId(id);
    	
    	this.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	Passeio passeio = new Passeio();
    	
    	if (turismoDTO.getPasseioDTO().getIdPasseio() != 0) {
			passeio = passeioService.buscarPorId(turismoDTO.getPasseioDTO().getIdPasseio());
		} else {
			passeio = passeioService.montarPasseio(turismoDTO.getPasseioDTO());
			
			passeio = passeioService.salvar(passeio);
		}
    	
    	Date dataInicio = new SimpleDateFormat("dd/MM/yyyy").parse(turismoDTO.getDataPasseio());
    	
    	Evento evento = new Evento();
    	
    	evento.setTitulo("Passeio na viagem");
    	evento.setDataInicio(dataInicio);
    	evento.setValorEstimado(turismoDTO.getValorGastoPrevisto());
    	evento.setPasseio(passeio);
    	evento.setViagem(viagem);
    	
    	passeio.getEventos().add(evento);
    	
    	return passeioService.salvar(passeio);
	}
    
    public Transporte adicionarTransporte(long id, TransporteDTO transporteDTO, Usuario usuario) throws ParseException {
    	Viagem viagem = this.buscarPorId(id);
    	
    	this.verificarSeUsuarioLogadoGerenciaViagem(viagem, usuario);
    	
    	return transporteService.adicionarTrasporte(transporteDTO, viagem);
	}
    
    public void verificarSeUsuarioLogadoGerenciaViagem(Viagem viagem, Usuario usuario) {
		if (!viagem.getUsuarios().contains(usuario)) {
			throw new InvalidOperationException("Essa viagem não te pertence");
		}
	}
}
