package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
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
    public List<Lembrete> listarLembretesPorAgiotaId(long agiotaId) {
        return lembreteRepository.findAllByAgiota(agiotaId);
    }

    @Override
    public Lembrete buscarLembreteAgiota(long id, long idAgiota) {
        Optional<Lembrete> lembreteOpt = lembreteRepository.findByIdAndAgiotaId(id, idAgiota);

        if (lembreteOpt.isPresent())
            return lembreteOpt.get();

        throw new RegistroNaoEncontradoException("Lembrete com o identificador " + id + " não foi encontrado!");
    }

    @Override
    public Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroNaoEncontradoException {
        Lembrete lembrete = lembreteDTO.toLembrete();
        Optional<Parcela> parcelaOpt = parcelaRepository.findById(lembreteDTO.getParcelaId());

        if (parcelaOpt.isEmpty()) {
            throw new RegistroNaoEncontradoException(
                    "Parcela com o identificador " + lembreteDTO.getParcelaId() + " não foi encontrada!");
        }

        lembrete.setParcela(parcelaOpt.get());
        return lembreteRepository.save(lembrete);
    }
}
