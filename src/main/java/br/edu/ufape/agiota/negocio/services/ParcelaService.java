package br.edu.ufape.agiota.negocio.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.repositorios.ParcelaRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.ParcelaServiceInterface;

@Service
public class ParcelaService implements ParcelaServiceInterface {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Override
    public List<Parcela> listarParcelasPorEmprestimo(long emprestimoId) {
        return parcelaRepository.findByEmprestimoId(emprestimoId);
    }

    @Override
    public Parcela buscarParcela(long id) {
        Optional<Parcela> parcelaOpt = parcelaRepository.findById(id);
        if (parcelaOpt.isPresent()) {
            return parcelaOpt.get();
        }
        throw new RegistroNaoEncontradoException("Parcela com o identificador " + id + " não foi encontrada!");
    }


    @Override
    public void gerarParcelas(Emprestimo emprestimo) {
        BigDecimal valorParcela = emprestimo.getValorEmprestimo().divide(BigDecimal.valueOf(emprestimo.getQuantidadeParcelas()));
        Date vencimento = emprestimo.getDataDeVencimentoInicial();

        for (int i = 0; i < emprestimo.getQuantidadeParcelas(); i++) {
            Parcela parcela = new Parcela();

            parcela.setDataVencimento(vencimento);
            parcela.setEmprestimo(emprestimo);
            parcela.setValor(valorParcela);

            parcelaRepository.save(parcela);

            vencimento = adicionarDias((long) emprestimo.getPeriodoParcelas(), vencimento);
        }
    }

    public Date adicionarDias(long dias, Date vencimento) {
        LocalDate localDate = vencimento.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate novaData = localDate.plusDays(dias);

        return Date.from(novaData.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


}
