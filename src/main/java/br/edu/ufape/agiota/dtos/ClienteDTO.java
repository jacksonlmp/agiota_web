package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDTO {
    @NotNull
    private String nome;

    @NotNull
    @Email
    private String email;

    private String senha;

    @NotNull
    private String telefone;

    @NotBlank
    private String cpf;

    @NotNull
    private String profissao;

    @NotNull
    private String localDeTrabalho;

    @NotBlank
    private EnderecoDTO endereco;

    public void toCliente(Cliente cliente) {
        cliente.setNome(getNome());
        cliente.setEmail(getEmail());
        cliente.setSenha(getSenha());
        cliente.setTelefone(getTelefone());
        cliente.setCpf(getCpf());
        cliente.setProfissao(getProfissao());
        cliente.setLocalDeTrabalho(getLocalDeTrabalho());
    }
}
