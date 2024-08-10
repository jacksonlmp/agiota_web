package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;

import java.util.List;

public interface TransacaoServiceInterface {

    List<Transacao> listarTransacoesPorEmprestimo(long idEmprestimo) throws RegistroNaoEncontradoException;

    Transacao buscarTransacao(long id) throws RegistroNaoEncontradoException;

    Transacao criarTransacao(TransacaoDTO transacaoDTO) throws RegistroJaExistenteException, RegistroNaoEncontradoException;

    List<Transacao> buscarTransacoesPorParcela(long idParcela) throws RegistroNaoEncontradoException;
}
