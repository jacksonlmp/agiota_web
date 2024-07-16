package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Lembrete;
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

    public Lembrete toEntity() {
        Lembrete lembrete = new Lembrete();
        lembrete.setData(this.data);
        lembrete.setTexto(this.texto);
        return lembrete;
    }
}
