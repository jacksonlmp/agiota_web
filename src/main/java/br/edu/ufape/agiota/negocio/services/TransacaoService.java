package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import br.edu.ufape.agiota.negocio.repositorios.TransacaoRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.TransacaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService implements TransacaoServiceInterface {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ParcelaService parcelaService;

    @Override
    public List<Transacao> listarTransacoesPorEmprestimo(long idEmprestimo) {
        List<Parcela> parcelas = parcelaService.listarParcelasPorEmprestimo(idEmprestimo);
        List<Transacao> transacoes = transacaoRepository.findByParcelaIn(parcelas);
        if (transacoes.isEmpty()) {
            throw new RegistroNaoEncontradoException("Não foram encontradas transações para o empréstimo com identificador " + idEmprestimo);
        }
        return transacoes;
    }

    @Override
    public Transacao buscarTransacao(long id) {
        Optional<Transacao> transacaoOpt = transacaoRepository.findById(id);
        if (transacaoOpt.isPresent()) return transacaoOpt.get();
        throw new RegistroNaoEncontradoException("Transação com o identificador " + id + " não foi encontrada!");
    }

    @Override
    public Transacao criarTransacao(TransacaoDTO transacaoDTO) {
        Parcela parcela = parcelaService.buscarParcela(transacaoDTO.getParcelaId());

        Transacao transacao = new Transacao();
        transacaoDTO.criarTransacao(transacao, parcela);

        // Verificar se parcela está atrasada
        abaterValorDaParcela(parcela, transacaoDTO.getValor());

        return transacaoRepository.save(transacao);
    }

    public void abaterValorDaParcela(Parcela parcela, BigDecimal valor) {
        BigDecimal dividaAtual = (parcela.getValor()).subtract(valor);
        parcela.setValor(dividaAtual);
        parcelaService.salvar(parcela);
    }

    @Override
    public List<Transacao> buscarTransacoesPorParcela(long idParcela) {
        Parcela parcela = parcelaService.buscarParcela(idParcela);
        return transacaoRepository.findByParcelaId(parcela.getId());
    }

}
