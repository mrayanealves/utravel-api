package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.dto.Login;
import br.ufrn.imd.utravel.exception.LoginException;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.UsuarioRepository;
import br.ufrn.imd.utravel.security.JWTUtil;

@Stateless
public class UsuarioService extends AbstractService<Usuario> {
	@Inject
	private UsuarioRepository repository;
	
	@Override
	protected AbstractRepository<Usuario> repository() {
		return this.repository;
	}
	
	public String login(Login login) throws Exception, LoginException {
		Usuario usuarioCadastrado = repository.buscarUsuarioPorEmailSenha(login.getEmail(), 
																					login.getSenha());
		
		if (usuarioCadastrado == null) {
			throw new LoginException();
		}
		
		return JWTUtil.create(usuarioCadastrado.getEmail());
	}
}
