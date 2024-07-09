package br.edu.ufape.agiota.negocio.basica;

import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
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
    private Date dataDeVencimento;
    private String garantia;
    private int quantidadeParcelas;
    private double periodoParcelas;
    private double taxaJuros;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Agiota agiota;
}
