package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Lembrete;

import java.util.List;

public interface LembreteServiceInterface {
    List<Lembrete> listarLembretes();

    Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroNaoEncontradoException;

    Lembrete buscarLembrete(long id) throws RegistroNaoEncontradoException;

    Lembrete atualizarLembrete(LembreteDTO lembreteDTO, long id) throws RegistroNaoEncontradoException;

}
