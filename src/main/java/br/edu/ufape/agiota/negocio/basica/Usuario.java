package br.edu.ufape.agiota.negocio.basica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = "id")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;

    @JsonIgnore
    private String senha;
    private String telefone;
    private String cpf;
    private String reputacao;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Endereco endereco;
}
