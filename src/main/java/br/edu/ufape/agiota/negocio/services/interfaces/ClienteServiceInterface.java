package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.ClienteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Cliente;

import java.util.List;

public interface ClienteServiceInterface {
    List<Cliente> listarClientes();

    Cliente criarCliente(ClienteDTO clienteDTo) throws RegistroNaoEncontradoException;

    Cliente buscarCliente(long id) throws RegistroNaoEncontradoException;

   Cliente atualizarCliente(ClienteDTO c, long id) throws RegistroNaoEncontradoException;
}
