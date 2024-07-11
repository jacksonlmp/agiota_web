package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.basica.enums.PeriodoTaxa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Name;
import lombok.Data;

@Entity
@Data
public class AgiotaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String cpf;
    private String reputacao;
    private Double taxaDeJuros;
    private String metodoCobranca;
    private PeriodoTaxa periodoTaxa;
}
