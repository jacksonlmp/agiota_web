package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO {
    private int id;

    @NotBlank
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String bairro;

    @NotBlank
    private String numero;

    public void toEndereco(Cliente cliente) {

    }
}
