package br.edu.ufape.agiota.negocio.basica;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Cliente extends Usuario {
    String profissao;
    String localDeTrabalho;
}
