package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransacaoDTO {

    @NotNull
    private Long parcelaId;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    private String metodoPagamento;

    public void criarTransacao(Transacao transacao, Parcela parcela) {
        transacao.setData(new Date());
        transacao.setValor(getValor());
        transacao.setMetodoPagamento(getMetodoPagamento());
        transacao.setParcela(parcela);
    }
}
