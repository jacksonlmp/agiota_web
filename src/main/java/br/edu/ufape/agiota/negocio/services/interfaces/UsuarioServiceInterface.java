package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Usuario;

import java.util.List;

public interface UsuarioServiceInterface {
    List<Cliente> listarClientes();

    Cliente criarCliente(Cliente cliente) throws Exception;
}
