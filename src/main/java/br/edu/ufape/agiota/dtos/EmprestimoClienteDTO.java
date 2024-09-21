package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmprestimoClienteDTO {

    @NotNull
    @Positive
    private BigDecimal valorEmprestimo;

    @NotBlank
    private String garantia;

    @NotNull
    @Positive
    private long agiotaId;

    // -=-=-=-=

    @NotNull
    private Date dataEmprestimo;

    @NotNull
    private Date dataDeVencimentoInicial;

    @Positive
    @Min(1)
    private int quantidadeParcelas;

    @NotNull
    @Positive
    private int periodoParcelas;

    public void toSolicitarEmprestimo(Emprestimo emprestimo, Cliente cliente, Agiota agiota) {
        emprestimo.setValorEmprestimo(getValorEmprestimo());
        emprestimo.setGarantia(getGarantia());
        emprestimo.setDataEmprestimo(getDataEmprestimo());
        emprestimo.setDataDeVencimentoInicial(getDataDeVencimentoInicial());
        emprestimo.setQuantidadeParcelas(getQuantidadeParcelas());
        emprestimo.setPeriodoParcelas(getPeriodoParcelas());
        emprestimo.setStatus(StatusEmprestimo.AGUARDANDO_APROVACAO);
        emprestimo.setCliente(cliente);
        emprestimo.setAgiota(agiota);
    }

}
