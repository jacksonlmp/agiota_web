package br.edu.ufape.agiota.negocio.basica;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Agiota extends Usuario {
    private Double taxaDeJuros;
    private String metodoCobranca;
    private PeriodoTaxa periodoTaxa;

    enum PeriodoTaxa {
        DIARIA,
        SEMANAL,
        MENSAL,
        ANUAL
    }

    //Todos
    public void emprestar() {
    }

    public void avaliarCliente() {
    }

}
