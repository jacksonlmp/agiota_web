package br.edu.ufape.agiota.negocio.services.interfaces;

import java.util.List;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Lembrete;

public interface LembreteServiceInterface {
    List<Lembrete> listarLembretesPorUsuarioId(long agiotaId);

    Lembrete buscarLembrete(long id) throws RegistroNaoEncontradoException;

    Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroJaExistenteException;
}
