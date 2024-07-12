package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.repositorios.TransacaoRepository;
import br.edu.ufape.agiota.negocio.repositorios.ParcelaRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.TransacaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService implements TransacaoServiceInterface {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Override
    public List<Transacao> listarTransacao() {
        return transacaoRepository.findAll();
    }

    @Override
    public Transacao buscarTransacao(long id) {
        Optional<Transacao> transacaoOpt = transacaoRepository.findById(id);
        if (transacaoOpt.isPresent()) return transacaoOpt.get();
        throw new RegistroNaoEncontradoException("Transação com o identificador " + id + " não foi encontrada!");
    }

    @Override
    public Transacao criarTransacao(TransacaoDTO transacaoDTO) throws RegistroNaoEncontradoException {
        Optional<Parcela> parcelaOpt = parcelaRepository.findById(transacaoDTO.getParcelaId());
        if (!parcelaOpt.isPresent()) {
            throw new RegistroNaoEncontradoException("Parcela com o identificador " + transacaoDTO.getParcelaId() + " não foi encontrada!");
        }
        Parcela parcela = parcelaOpt.get();

        Transacao transacao = new Transacao();
        transacao.setData(new Date());
        transacao.setValor(transacaoDTO.getValor());
        transacao.setMetodoPagamento(transacaoDTO.getMetodoPagamento());
        transacao.setDataTransacao(transacaoDTO.getDataTransacao());
        transacao.setParcela(parcela);

        return transacaoRepository.save(transacao);
    }

    @Override
    public List<Transacao> buscarTransacoesPorParcela(long idParcela) throws RegistroNaoEncontradoException {
        Optional<Parcela> parcelaOpt = parcelaRepository.findById(idParcela);
        if (!parcelaOpt.isPresent()) {
            throw new RegistroNaoEncontradoException("Parcela com o identificador " + idParcela + " não foi encontrada!");
        }
        return transacaoRepository.findByParcelaId(idParcela);
    }
}
