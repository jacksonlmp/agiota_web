package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmprestimoDTO {
    private long id;
    private Date dataEmprestimo;
    private BigDecimal valorEmprestimo;
    private Date dataDeVencimentoInicial;
    private String garantia;
    private int quantidadeParcelas;
    private int periodoParcelas;
    private double taxaJuros;
    private String motivoRecusa;
    private StatusEmprestimo status;
    private long agiota_id;
    private long cliente_id;

    public static EmprestimoDTO fromEmprestimo(Emprestimo emprestimo) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setId(emprestimo.getId());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setValorEmprestimo(emprestimo.getValorEmprestimo());
        dto.setDataDeVencimentoInicial(emprestimo.getDataDeVencimentoInicial());
        dto.setGarantia(emprestimo.getGarantia());
        dto.setQuantidadeParcelas(emprestimo.getQuantidadeParcelas());
        dto.setPeriodoParcelas(emprestimo.getPeriodoParcelas());
        dto.setTaxaJuros(emprestimo.getTaxaJuros());
        dto.setMotivoRecusa(emprestimo.getMotivoRecusa());
        dto.setStatus(emprestimo.getStatus());
        dto.setAgiota_id(emprestimo.getAgiota().getId());
        dto.setCliente_id(emprestimo.getCliente().getId());
        return dto;
    }
}

