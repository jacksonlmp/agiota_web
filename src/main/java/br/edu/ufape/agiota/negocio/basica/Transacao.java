package br.edu.ufape.agiota.negocio.basica;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date data;
    private BigDecimal valor;
    private String metodoPagamento;
    private Date dataTransacao;
    
    @ManyToOne
    private Parcela parcela;
}
