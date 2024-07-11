package br.edu.ufape.agiota.negocio.basica;

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
    private Usuario avaliador;

    @ManyToOne
    private Usuario avaliado;
}
