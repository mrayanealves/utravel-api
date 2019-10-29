package br.ufrn.imd.utravel.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.UsuarioRepository;

@Stateless
public class UsuarioService extends AbstractService<Usuario> {
	@Inject
	private UsuarioRepository reository;
	
	@Override
	protected AbstractRepository<Usuario> repository() {
		return this.reository;
	}
}
