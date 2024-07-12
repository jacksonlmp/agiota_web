package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmprestimoClienteDTO {

    @NotNull
    @Positive
    private BigDecimal valorEmprestimo;

    @NotBlank
    private String garantia;

    @NotNull
    @Positive
    private long clienteId;

    @NotNull
    @Positive
    private long agiotaId;

    public void toSolicitarEmprestimo(Emprestimo emprestimo, Cliente cliente, Agiota agiota) {
        emprestimo.setValorEmprestimo(getValorEmprestimo());
        emprestimo.setGarantia(getGarantia());
        emprestimo.setStatus(StatusEmprestimo.AGUARDANDO_APROVACAO);
        emprestimo.setCliente(cliente);
        emprestimo.setAgiota(agiota);
    }

}
