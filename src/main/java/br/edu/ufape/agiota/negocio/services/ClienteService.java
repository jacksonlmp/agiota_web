package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.repositorios.ClienteRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.UsuarioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements UsuarioServiceInterface {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente create(Cliente cliente) throws Exception {
        if(findByEmail(cliente.getEmail()) == null)
            return clienteRepository.save(cliente);
        else
            throw new Exception("O email informado já se encontra cadastrado no sistema");
    }

    public Cliente find(long id) throws Exception {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);

        if (clienteOpt.isPresent()) return clienteOpt.get();

        throw new Exception("Cliente com o identificador " + id + " não foi encontrado!");
    }

    public Optional<Cliente> update(Cliente c, long id) throws Exception {
        return clienteRepository.findById(id)
                .map(currentCliente -> {
                    if (c.getNome() != null) {
                        currentCliente.setNome(c.getNome());
                    }
                    if(c.getEmail() != null) {
                        currentCliente.setEmail(c.getEmail());
                    }
                    if(c.getSenha() != null) {
                        currentCliente.setSenha(c.getSenha());
                    }
                    if(c.getTelefone() != null) {
                        currentCliente.setTelefone(c.getTelefone());
                    }
                    if(c.getCpf() != null) {
                        currentCliente.setCpf(c.getCpf());
                    }
                    if(c.getProfissao() != null) {
                        currentCliente.setProfissao(c.getProfissao());
                    }
                    if(c.getLocalDeTrabalho() != null) {
                        currentCliente.setLocalDeTrabalho(c.getLocalDeTrabalho());
                    }

                    return currentCliente;
                })
                .map(clienteRepository::save);
    }

    public boolean delete(long id) throws Exception {
        find(id);

        clienteRepository.deleteById(id);

        return true;
    }

    private Cliente buscarClientePorEmail(String email)
    {
        return clienteRepository.findByEmail(email);
    }
}
