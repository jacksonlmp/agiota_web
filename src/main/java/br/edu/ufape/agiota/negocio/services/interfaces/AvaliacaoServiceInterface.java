package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.AvaliacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import br.edu.ufape.agiota.negocio.basica.Usuario;

import java.util.List;

public interface AvaliacaoServiceInterface {
    Avaliacao avaliarUsuario(AvaliacaoDTO avaliacaoDTO, Usuario avaliador) throws RegistroNaoEncontradoException;

    List<Avaliacao> buscarAvaliacoesDoUsuario(long idUsuario) throws RegistroNaoEncontradoException;
}
