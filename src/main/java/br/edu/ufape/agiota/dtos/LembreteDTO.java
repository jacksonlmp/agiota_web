package br.edu.ufape.agiota.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class LembreteDTO {
    private Date data;
    private String texto;
    private long parcelaId;

    public Date getData() {
        return data;
    }

    public String getTexto() {
        return texto;
    }

    public long getParcelaId() {
        return parcelaId;
    }
}
