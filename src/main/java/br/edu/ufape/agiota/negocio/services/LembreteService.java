package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Lembrete;
import br.edu.ufape.agiota.negocio.repositorios.LembreteRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.LembreteServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class LembreteService implements LembreteServiceInterface {

    @Autowired
    private LembreteRepository lembreteRepository;

    @Override
    public List<Lembrete> listarLembrete() {
        return lembreteRepository.findAll();
    }        

    @Override
    public Lembrete buscarLembrete(long id) {
        Optional<Lembrete> parceOpt = lembreteRepository.findById(id);

        if (parceOpt.isPresent()) return parceOpt.get();

        throw new RegistroNaoEncontradoException("Lembrete com o identificador " + id + " não foi encontrada!");
    }
    public Lembrete atualizarCliente(LembreteDTO lembreteDTO, long id) throws RegistroNaoEncontradoException {
        Lembrete lembrete = buscarLembrete(id);
        return lembreteRepository.save(lembrete);
    }

	@Override
	public Lembrete criarLembrete(LembreteDTO LembreteDTo) throws RegistroNaoEncontradoException {
		return null;
	}

	@Override
	public Lembrete atualizarLembrete(LembreteDTO c, long id) throws RegistroNaoEncontradoException {
		return null;
	}

}
