package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Lembrete;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.repositorios.LembreteRepository;
import br.edu.ufape.agiota.negocio.repositorios.ParcelaRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.LembreteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LembreteService implements LembreteServiceInterface {

    @Autowired
    private LembreteRepository lembreteRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Override
    public List<Lembrete> listarLembretes() {
        return lembreteRepository.findAll();
    }

    @Override
    public Lembrete buscarLembrete(long id) {
        Optional<Lembrete> lembreteOpt = lembreteRepository.findById(id);

        if (lembreteOpt.isPresent()) return lembreteOpt.get();

        throw new RegistroNaoEncontradoException("Lembrete com o identificador " + id + " não foi encontrado!");
    }

    @Override
    public Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroNaoEncontradoException {
        if (lembreteDTO == null) {
            throw new IllegalArgumentException("Lembrete não pode ser vazio");
        }

        Lembrete lembrete = lembreteDTO.toEntity();
        Optional<Parcela> parcelaOpt = parcelaRepository.findById(lembreteDTO.getParcelaId());

        if (!parcelaOpt.isPresent()) {
            throw new RegistroNaoEncontradoException("Parcela com o identificador " + lembreteDTO.getParcelaId() + " não foi encontrada!");
        }

        lembrete.setParcela(parcelaOpt.get());
        return lembreteRepository.save(lembrete);
    }

    @Override
    public Lembrete atualizarLembrete(LembreteDTO lembreteDTO, long id) throws RegistroNaoEncontradoException {
        Optional<Lembrete> lembreteOpt = lembreteRepository.findById(id);

        if (!lembreteOpt.isPresent()) {
            throw new RegistroNaoEncontradoException("Lembrete com o identificador " + id + " não foi encontrado!");
        }

        Lembrete lembrete = lembreteOpt.get();
        lembrete.setData(lembreteDTO.getData());
        lembrete.setTexto(lembreteDTO.getTexto());
        lembrete.setUsuarioId(lembreteDTO.getUsuarioId());

        Optional<Parcela> parcelaOpt = parcelaRepository.findById(lembreteDTO.getParcelaId());
        if (!parcelaOpt.isPresent()) {
            throw new RegistroNaoEncontradoException("Parcela com o identificador " + lembreteDTO.getParcelaId() + " não foi encontrada!");
        }

        lembrete.setParcela(parcelaOpt.get());
        return lembreteRepository.save(lembrete);
    }

    public List<Lembrete> listarLembretesPorUsuario(long usuarioId) {
        return lembreteRepository.findByUsuarioId(usuarioId);
    }
}
