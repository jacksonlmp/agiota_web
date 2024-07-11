package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDTO {
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

    @NotBlank
    private String profissao;

    @NotBlank
    private String localDeTrabalho;

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
