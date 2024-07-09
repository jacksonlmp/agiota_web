package br.edu.ufape.agiota.negocio.basica;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
public class Parcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date dataVencimento;
    private Double valorVencimento;

    @ManyToOne
    private Emprestimo emprestimo;

}
