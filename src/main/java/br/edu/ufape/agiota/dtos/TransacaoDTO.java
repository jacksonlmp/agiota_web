package br.edu.ufape.agiota.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

import br.edu.ufape.agiota.negocio.basica.Parcela;

@Data
public class TransacaoDTO {
    @NotNull
    private long parcelaId;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private String metodoPagamento;

    @NotNull
    private Date dataTransacao;

	private Date data;

	private Parcela parcela;

    @NotNull
    public Date getData() {
        return data;
    }

    @NotNull
    public void setData(Date data) {
        this.data = data;
    }

    @NotNull
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

    public Parcela getParcela() {
        return getParcela();
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }
}
