package br.edu.ufape.agiota.negocio.basica;

import br.edu.ufape.agiota.negocio.basica.enums.PeriodoTaxa;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Agiota extends Usuario {
    private Double taxaDeJuros;
    private String metodoCobranca;
    private PeriodoTaxa periodoTaxa;

    //Todos
    public void emprestar() {
    }

    public void avaliarCliente() {
    }

}
