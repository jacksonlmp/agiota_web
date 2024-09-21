package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class RejeitarEmprestimoDTO {

    @NotNull
    private String motivoRecusa;

    public void rejeitar(Emprestimo emprestimo) {
        emprestimo.setMotivoRecusa(getMotivoRecusa());

        emprestimo.setStatus(StatusEmprestimo.REJEITADO);
    }

}
