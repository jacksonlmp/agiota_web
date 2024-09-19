package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.fachada.exceptions.OperacaoNaoPermitidaException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

    public Object getUsuarioLogado(Class<?> tipo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object usuario = authentication.getPrincipal();

        if (tipo.isInstance(usuario)) {
            return usuario;
        }

        throw new OperacaoNaoPermitidaException("Usuário não autorizado. O tipo de usuário não corresponde com a operação a ser realizada.");
    }

    public Cliente getClienteLogado() {
        return (Cliente) getUsuarioLogado(Cliente.class);
    }

    public Agiota getAgiotaLogado() {
        return (Agiota) getUsuarioLogado(Agiota.class);
    }

}
