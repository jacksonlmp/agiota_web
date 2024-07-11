package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Usuario;

public interface UsuarioServiceInterface {
    Usuario buscarUsuarioPorId(long id) throws RegistroNaoEncontradoException;

    void atualizarReputacaoDoUsuario(Usuario avaliado, double reputacao);
}
