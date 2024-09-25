package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.OperacaoInvalidaException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.repositorios.TransacaoRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.TransacaoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService implements TransacaoServiceInterface {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private ParcelaService parcelaService;

    @Autowired
    private ApplicationService applicationService;

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
        Usuario usuarioLogado = (Usuario) applicationService.getUsuarioLogado();
        Parcela parcela = parcelaService.buscarParcela(transacaoDTO.getParcelaId(), usuarioLogado.getId());

        if (transacaoDTO.getValor().compareTo(parcela.getValor()) > 0)
            throw new OperacaoInvalidaException("Valor a ser pago não pode ser maior que valor da parcela");

        Transacao transacao = new Transacao();
        transacaoDTO.criarTransacao(transacao, parcela);

        // Verificar se parcela está atrasada
        parcelaService.abaterValorDaParcela(parcela, transacaoDTO.getValor());
        parcelaService.salvar(parcela);

        return transacaoRepository.save(transacao);
    }

    @Override
    public List<Transacao> buscarTransacoesPorParcela(long idParcela) {
        Usuario usuarioLogado = (Usuario) applicationService.getUsuarioLogado();
        Parcela parcela = parcelaService.buscarParcela(idParcela, usuarioLogado.getId());
        return transacaoRepository.findByParcelaId(parcela.getId());
    }

}
