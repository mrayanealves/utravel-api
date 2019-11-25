package br.ufrn.imd.utravel.service;

import br.ufrn.imd.utravel.dto.Login;
import br.ufrn.imd.utravel.dto.TokenDTO;
import br.ufrn.imd.utravel.exception.LoginException;
import br.ufrn.imd.utravel.model.Usuario;
import br.ufrn.imd.utravel.repository.AbstractRepository;
import br.ufrn.imd.utravel.repository.UsuarioRepository;
import br.ufrn.imd.utravel.security.JWTUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UsuarioService extends AbstractService<Usuario> {
    @Inject
    private UsuarioRepository repository;

    @Override
    protected AbstractRepository<Usuario> repository() {
        return this.repository;
    }

    public TokenDTO login(Login login) throws LoginException {
        Usuario usuarioCadastrado = repository.buscarUsuarioPorEmailSenha(login.getEmail(),
                login.getSenha());

        if (usuarioCadastrado == null) {
            throw new LoginException("Usuário ou senha incorretos.");
        }

        return new TokenDTO(
                JWTUtil.create(usuarioCadastrado.getEmail()),
                usuarioCadastrado.getNome(),
                usuarioCadastrado.getLogin(),
                usuarioCadastrado.getEmail());
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return repository.buscarUsuarioPorEmail(email);
    }
}
