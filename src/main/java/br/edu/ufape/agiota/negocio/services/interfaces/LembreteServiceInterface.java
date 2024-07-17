package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Lembrete;

import java.util.List;

public interface LembreteServiceInterface {
    List<Lembrete> listarLembretesPorUsuarioId(long usuarioId);

    Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroJaExistenteException;

    Lembrete buscarLembrete(long id) throws RegistroNaoEncontradoException;
}
