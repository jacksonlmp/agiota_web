package br.edu.ufape.agiota.dtos;

import lombok.Data;

@Data
public class UsuarioAutenticadoDTO {

    public UsuarioAutenticadoDTO(long id, String tipo, String nome, String email) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.email = email;
    }

    private long id;
    private String tipo;
    private String nome;
    private String email;
}
