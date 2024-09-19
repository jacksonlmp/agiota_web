package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


@Service
public class ApplicationService {


    public Object getUsuarioLogado(Class<?> tipo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object usuario = authentication.getPrincipal();

        if (tipo.isInstance(usuario)) {
            return usuario;
        }

        throw new RuntimeException("Usuário não autorizado");
    }

    public Cliente getCliente()
    {
        return (Cliente) getUsuarioLogado(Cliente.class);
    }

    public Agiota getAgiota()
    {
        return (Agiota) getUsuarioLogado(Agiota.class);
    }

}
