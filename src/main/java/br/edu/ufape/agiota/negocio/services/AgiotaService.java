package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.AgiotaDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Endereco;
import br.edu.ufape.agiota.negocio.repositorios.AgiotaRepository;
import br.edu.ufape.agiota.negocio.repositorios.EnderecoRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.AgiotaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class AgiotaService implements AgiotaServiceInterface {
    @Autowired
    private AgiotaRepository agiotaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Agiota criarAgiota(AgiotaDTO agiotaDTO) throws RegistroJaExistenteException, SenhaNulaException {
        if (nonNull(buscarAgiotaPorEmail(agiotaDTO.getEmail()))) {
            throw new RegistroJaExistenteException("O email informado já se encontra cadastrado no sistema");
        }

        if (isNull(agiotaDTO.getSenha())) {
            throw new SenhaNulaException("Senha é um campo obrigatório");
        }

        Endereco endereco = new Endereco();
        agiotaDTO.getEndereco().toEndereco(endereco);
        Endereco novoEndereco = enderecoRepository.save(endereco);

        Agiota agiota = new Agiota();
        agiotaDTO.toAgiota(agiota, passwordEncoder.encode(agiotaDTO.getSenha()));
        agiota.setEndereco(novoEndereco);

        return agiotaRepository.save(agiota);
    }

    public Agiota buscarAgiota(long id) throws RegistroNaoEncontradoException {
        Optional<Agiota> agiotaOpt = agiotaRepository.findById(id);

        if (agiotaOpt.isPresent()) return agiotaOpt.get();

        throw new RegistroNaoEncontradoException("Agiota com o identificador " + id + " não foi encontrado!");
    }

    @Override
    public Agiota atualizarAgiota(AgiotaDTO agiotaDTO, long id) throws RegistroNaoEncontradoException {
        Agiota agiota = buscarAgiota(id);

        agiotaDTO.getEndereco().toEndereco(agiota.getEndereco());
        enderecoRepository.save(agiota.getEndereco());

        agiotaDTO.toAgiota(agiota, passwordEncoder.encode(agiotaDTO.getSenha()));

        return agiotaRepository.save(agiota);
    }

    public List<Agiota> listarAgiotas() {
        return agiotaRepository.findAll();
    }

    private Agiota buscarAgiotaPorEmail(String email){
        return agiotaRepository.findByEmail(email);
    }

}
