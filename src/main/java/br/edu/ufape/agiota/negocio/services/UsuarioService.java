package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.repositorios.UsuarioRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.UsuarioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UsuarioServiceInterface {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarUsuarioPorId(long id) throws RegistroNaoEncontradoException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent())
            return optionalUsuario.get();

        throw new RegistroNaoEncontradoException("Usuário com o identificador " + id + " não foi encontrado!");
    }

    public void atualizarReputacaoDoUsuario(Usuario avaliado, double reputacao) {
        avaliado.setReputacao(reputacao);
        usuarioRepository.save(avaliado);
    }

}
