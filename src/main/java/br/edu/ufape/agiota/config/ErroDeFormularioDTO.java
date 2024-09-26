package br.edu.ufape.agiota.config;

import lombok.Getter;

@Getter
public class ErroDeFormularioDTO {
    private String campo;
    private String erro;

    public ErroDeFormularioDTO(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

}
