package br.edu.ufape.agiota.dtos;

import java.util.Date;

import br.edu.ufape.agiota.negocio.basica.Lembrete;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LembreteDTO {
    @NotNull(message = "A data não pode ser nula.")
    private Date data;

    @NotBlank(message = "O texto do lembrete não pode estar em branco.")
    private String texto;

    @Positive(message = "O ID da parcela deve ser um número positivo.")
    private long parcelaId;
    
    public Lembrete toLembrete() {  
        Lembrete lembrete = new Lembrete();  
        lembrete.setData(getData());  
        lembrete.setTexto(getTexto());  
        return lembrete;  
    } 
}
