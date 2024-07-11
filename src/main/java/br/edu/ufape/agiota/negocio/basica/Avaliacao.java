package br.edu.ufape.agiota.negocio.basica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int nota;

    private String descricao;

    @ManyToOne
    @JsonIgnore
    private Usuario avaliador;

    @ManyToOne
    @JsonIgnore
    private Usuario avaliado;
}
