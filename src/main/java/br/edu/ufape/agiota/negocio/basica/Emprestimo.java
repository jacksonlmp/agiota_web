package br.edu.ufape.agiota.negocio.basica;

import br.edu.ufape.agiota.fachada.exceptions.OperacaoNaoPermitidaException;
import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date dataEmprestimo;
    private BigDecimal valorEmprestimo;
    private Date dataDeVencimentoInicial;
    private String garantia;
    private int quantidadeParcelas;
    private double periodoParcelas;
    private double taxaJuros;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToOne
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    private Agiota agiota;


    public void checarPossibilidadeDeCancelar()
    {
        switch (getStatus()) {
            case APROVADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser cancelado, pois já foi aprovado");
            case QUITADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser cancelado, pois já foi quitado");
            case ATRASADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser cancelado, pois está sendo pago");
            case REJEITADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser cancelado, pois foi recusado pelo agiota");
            case CANCELADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser cancelado, pois já foi cancelado");
            default -> { }
        }
    }

    public void checarAprocacao()
    {
        if (getStatus() != StatusEmprestimo.AGUARDANDO_APROVACAO) {
            throw new OperacaoNaoPermitidaException("O empréstimo só pode ser aprovado enquanto estiver aguardando aprovação!");
        }
    }

    public void checarRejeicao()
    {
        switch (getStatus()) {
            case APROVADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser rejeitado, pois já foi aprovado");
            case QUITADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser rejeitado, pois já foi quitado");
            case ATRASADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser rejeitado, pois está sendo pago");
            case REJEITADO -> throw new OperacaoNaoPermitidaException("O empréstimo já foi rejeitado");
            case CANCELADO -> throw new OperacaoNaoPermitidaException("O empréstimo não pode ser rejeitado, pois já foi cancelado");
            default -> { }
        }
    }
}
