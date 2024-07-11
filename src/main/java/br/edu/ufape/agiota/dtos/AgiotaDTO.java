package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.basica.enums.PeriodoTaxa;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Enabled;
import jdk.jfr.Name;
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

    public void toAgiota(Agiota agiota) {
        agiota.setNome(getNome());
        agiota.setEmail(getEmail());
        agiota.setSenha(getSenha());
        agiota.setTelefone(getTelefone());
        agiota.setCpf(getCpf());
        agiota.setTaxaDeJuros(getTaxaDeJuros());
        agiota.setMetodoCobranca(getMetodoCobranca());
        agiota.setPeriodoTaxa(getPeriodoTaxa());
    }
}
