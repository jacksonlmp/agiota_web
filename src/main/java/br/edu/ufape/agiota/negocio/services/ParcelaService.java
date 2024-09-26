package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.infra.GeradorDeDatas;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.repositorios.ParcelaRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.ParcelaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class ParcelaService implements ParcelaServiceInterface {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private GeradorDeDatas geradorDeDatas;

    public Parcela salvar(Parcela parcela) {
        return parcelaRepository.save(parcela);
    }

    @Override
    public List<Parcela> listarParcelasPorEmprestimo(long idEmprestimo, long idUsuario) {
        return parcelaRepository.findByEmprestimoIdAndUsuarioId(idEmprestimo, idUsuario);
    }

    @Override
    public Parcela buscarParcela(long idParcela, long idUsuario) throws RegistroNaoEncontradoException {
        Parcela parcela = parcelaRepository.findByIdAndEmprestimoClienteId(idParcela, idUsuario);
        if (nonNull(parcela)) {
            return parcela;
        }
        throw new RegistroNaoEncontradoException("Parcela com o identificador " + idParcela + " não foi encontrada!");
    }

    public void gerarParcelas(Emprestimo emprestimo) {
        Date vencimento = emprestimo.getDataDeVencimentoInicial();

        BigDecimal valorTotal = emprestimo.getValorEmprestimo();
        int quantidadeParcelas = emprestimo.getQuantidadeParcelas();
        double taxaJuros = emprestimo.getTaxaJuros();
        BigDecimal valorParcela = valorTotal.divide(BigDecimal.valueOf(quantidadeParcelas), RoundingMode.HALF_UP);

        BigDecimal taxaJurosBD = BigDecimal.valueOf(taxaJuros).divide(BigDecimal.valueOf(100));
        BigDecimal valorComJuros = valorParcela.multiply(taxaJurosBD).add(valorParcela);

        int periodoParcelas = emprestimo.getPeriodoParcelas();

        for (int i = 0; i < quantidadeParcelas; i++) {
            parcelaRepository.save(new Parcela(vencimento, valorComJuros, emprestimo));
            vencimento = geradorDeDatas.getDataMaisNDias(vencimento, periodoParcelas);
        }
    }

    public void abaterValorDaParcela(Parcela parcela, BigDecimal valor) {
        BigDecimal dividaAtual = (parcela.getValor()).subtract(valor);
        parcela.setValor(dividaAtual);
    }

}
