package br.edu.ufape.agiota.dtos;

import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvaliacaoDTO {
    @NotNull
    @Min(0)
    @Max(100)
    private int nota;

    private String descricao;

    @NotNull
    private int idAvaliado;

    public void toAvaliacao(Avaliacao avaliacao, Usuario avaliador, Usuario avaliado) {
        avaliacao.setNota(getNota());
        avaliacao.setDescricao(getDescricao());
        avaliacao.setAvaliador(avaliador);
        avaliacao.setAvaliado(avaliado);
    }
}
