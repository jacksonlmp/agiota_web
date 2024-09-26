package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.AvaliacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.repositorios.AvaliacaoRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.AvaliacaoServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.UsuarioServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class AvaliacaoService implements AvaliacaoServiceInterface {

    @Autowired
    private UsuarioServiceInterface usuarioService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao avaliarUsuario(AvaliacaoDTO avaliacaoDTO, Usuario avaliador) throws RegistroNaoEncontradoException {
        Usuario avaliado = usuarioService.buscarUsuarioPorId(avaliacaoDTO.getIdAvaliado());

        Avaliacao avaliacao = new Avaliacao();
        avaliacaoDTO.toAvaliacao(avaliacao, avaliador, avaliado);

        atualizarReputacaoDoUsuario(avaliado, avaliacaoDTO.getNota());

        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> buscarAvaliacoesDoUsuario(long idUsuarioAvaliado) {
        return avaliacaoRepository.findByAvaliadoId(idUsuarioAvaliado);
    }

    public Integer somarNotasDoAvaliado(long idUsuarioAvaliado) {
        Integer nota = avaliacaoRepository.somarNotasDoAvaliado(idUsuarioAvaliado);
        return nonNull(nota) ? nota : 5;
    }

    public int contarQuantidadeDeAvaliacoes(long idUsuarioAvaliado) {
        return avaliacaoRepository.countByAvaliadoId(idUsuarioAvaliado);
    }

    public void atualizarReputacaoDoUsuario(Usuario avaliado, int nota) {
        Integer somaDasNotas = somarNotasDoAvaliado(avaliado.getId()) + nota;
        int quantidadeDeAvaliacoes = 1 + contarQuantidadeDeAvaliacoes(avaliado.getId());

        usuarioService.atualizarReputacaoDoUsuario(avaliado, somaDasNotas / quantidadeDeAvaliacoes);
    }

}
