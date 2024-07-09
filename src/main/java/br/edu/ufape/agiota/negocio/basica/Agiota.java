package br.edu.ufape.agiota.negocio.basica;

import br.edu.ufape.agiota.negocio.basica.enums.PeriodoTaxa;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Agiota extends Usuario {
    private Double taxaDeJuros;
    private String metodoCobranca;

    @Enumerated(EnumType.STRING)
    private PeriodoTaxa periodoTaxa;

}
