package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.ClienteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Endereco;
import br.edu.ufape.agiota.negocio.repositorios.ClienteRepository;
import br.edu.ufape.agiota.negocio.repositorios.EnderecoRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.ClienteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ClienteService implements ClienteServiceInterface {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente criarCliente(ClienteDTO clienteDTO) throws RegistroNaoEncontradoException, SenhaNulaException {
        if ( nonNull(buscarClientePorEmail(clienteDTO.getEmail())) ) {
            throw new RegistroNaoEncontradoException("O email informado já se encontra cadastrado no sistema");
        }

        if ( isNull(clienteDTO.getSenha())) {
            throw new SenhaNulaException("Senha é um campo obrigatório");
        }

        Endereco endereco = new Endereco();

        clienteDTO.getEndereco().toEndereco(endereco);

        Endereco novoEndereco = enderecoRepository.save(endereco);

        Cliente cliente = new Cliente();

        clienteDTO.toCliente(cliente);

        cliente.setEndereco(novoEndereco);

        return clienteRepository.save(cliente);
    }

    public Cliente buscarCliente(long id) throws RegistroNaoEncontradoException {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) return clienteOpt.get();

        throw new RegistroNaoEncontradoException("Cliente com o identificador " + id + " não foi encontrado!");
    }

    public Cliente atualizarCliente(ClienteDTO clienteDTO, long id) throws RegistroNaoEncontradoException {
        Cliente cliente = buscarCliente(id);

        clienteDTO.getEndereco().toEndereco(cliente.getEndereco());

        enderecoRepository.save(cliente.getEndereco());

        clienteDTO.toCliente(cliente);

        return clienteRepository.save(cliente);
    }

    private Cliente buscarClientePorEmail(String email)
    {
        return clienteRepository.findByEmail(email);
    }
}
