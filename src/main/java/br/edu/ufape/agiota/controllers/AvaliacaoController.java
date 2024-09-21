package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.AvaliacaoDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AvaliacaoController {

    @Autowired
    private Fachada fachada;
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/avaliar")
    public ResponseEntity<?> avaliarUsuario(@RequestBody @Valid AvaliacaoDTO avaliacaoDTO) {
        try {
            Avaliacao avaliacao = fachada.avaliarUsuario(avaliacaoDTO, (Usuario) applicationService.getUsuarioLogado());
            return ResponseEntity.ok().body(avaliacao);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity<?> buscarAvaliacoesDeUmUsuario(@PathVariable long id) {
        try {
            List<Avaliacao> avaliacoes = fachada.buscarAvaliacoesDoUsuario(id);
            return ResponseEntity.ok().body(avaliacoes);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
