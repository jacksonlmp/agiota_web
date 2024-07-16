package br.edu.ufape.agiota.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;

@Data
public class TransacaoDTO {

    @NotNull
    private Long parcelaId;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private String metodoPagamento;

    @NotNull
    private Date dataTransacao;

    private Date data;

    private Parcela parcela;

    public Transacao criarTransacao(Parcela parcela) {
        Transacao transacao = new Transacao();
        transacao.setData(new Date());
        transacao.setValor(this.valor);
        transacao.setMetodoPagamento(this.metodoPagamento);
        transacao.setDataTransacao(this.dataTransacao);
        transacao.setParcela(parcela);

        return transacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;		
	}

}
