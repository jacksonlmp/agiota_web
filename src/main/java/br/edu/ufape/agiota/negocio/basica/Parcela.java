package br.edu.ufape.agiota.negocio.basica;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date dataVencimento;
    private BigDecimal valor;

    @ManyToOne
    private Emprestimo emprestimo;

    public Parcela() {

    }
    public Parcela(Date dataVencimento, BigDecimal valor, Emprestimo emprestimo) {
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.emprestimo = emprestimo;
    }

}