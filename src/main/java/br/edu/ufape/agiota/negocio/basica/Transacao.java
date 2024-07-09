package br.edu.ufape.agiota.negocio.basica;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date data;
    private BigDecimal valor;
    private String metodoPagamento;
    private Date dataTransacao;

    public void acompanharProgresso() {
    }	
}