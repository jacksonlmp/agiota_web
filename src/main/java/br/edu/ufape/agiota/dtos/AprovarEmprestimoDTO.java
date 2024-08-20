package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.Date;

@Data
public class AprovarEmprestimoDTO {

    @NotNull
    private Date dataEmprestimo;

    @NotNull
    private Date dataDeVencimento;

    @Positive
    @Min(1)
    private int quantidadeParcelas;

    @NotNull
    @Positive
    private double periodoParcelas;

    public void aprovar(Emprestimo emprestimo, double taxaDeJuros) {
        emprestimo.setDataEmprestimo(getDataEmprestimo());
        emprestimo.setDataDeVencimento(getDataDeVencimento());
        emprestimo.setQuantidadeParcelas(getQuantidadeParcelas());
        emprestimo.setPeriodoParcelas(getPeriodoParcelas());

        emprestimo.setStatus(StatusEmprestimo.APROVADO);
        emprestimo.setTaxaJuros(taxaDeJuros);
    }

}
