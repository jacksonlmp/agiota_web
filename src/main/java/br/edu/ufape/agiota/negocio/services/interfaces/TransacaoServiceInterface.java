package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Transacao;

import java.util.List;

public interface TransacaoServiceInterface {
    Transacao criarTransacao(TransacaoDTO transacaoDTO) throws RegistroNaoEncontradoException;

    List<Transacao> buscarTransacoesPorParcela(long idParcela) throws RegistroNaoEncontradoException;

    Transacao buscarTransacao(long idTransacao) throws RegistroNaoEncontradoException;

	List<Transacao> listarTransacao();
}
