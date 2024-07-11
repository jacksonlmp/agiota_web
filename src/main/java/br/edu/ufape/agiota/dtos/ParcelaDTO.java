package br.edu.ufape.agiota.dtos;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotBlank;

public class ParcelaDTO {
    @NotBlank
    private Date dataVencimento;
    @NotBlank
    private BigDecimal valorVencimento;    
}