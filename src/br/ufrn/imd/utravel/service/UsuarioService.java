package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.UsuarioRepository;

@Stateless
public class UsuarioService extends AbstractService<Usuario> {
	@Inject
	private UsuarioRepository usuarioRepositorio;
	
	@Override
	protected AbstractRepository<Usuario> repositorio() {
		return this.usuarioRepositorio;
	}
}
