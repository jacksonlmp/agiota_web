package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.enums.PeriodoTaxa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AgiotaDTO {
    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    private String senha;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cpf;

    @NotNull
    private Double taxaDeJuros;

    @NotBlank
    private String metodoCobranca;

    @NotNull
    private PeriodoTaxa periodoTaxa;

    @NotNull
    private EnderecoDTO endereco;

    public void toAgiota(Agiota agiota, String senhEncriptada) {
        agiota.setNome(getNome());
        agiota.setEmail(getEmail());
        agiota.setSenha(senhEncriptada);
        agiota.setTelefone(getTelefone());
        agiota.setCpf(getCpf());
        agiota.setTaxaDeJuros(getTaxaDeJuros());
        agiota.setMetodoCobranca(getMetodoCobranca());
        agiota.setPeriodoTaxa(getPeriodoTaxa());
    }
}
