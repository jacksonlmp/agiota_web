package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Lembrete;
import br.edu.ufape.agiota.negocio.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agiota")
public class AgiotaLembreteController {

    @Autowired
    private Fachada fachada;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/lembretes")
    public ResponseEntity<?> listarLembretesPorAgiotaId() {
        try {
            List<Lembrete> lembretes = fachada.listarLembretesPorAgiotaId(applicationService.getAgiotaLogado().getId());
            return ResponseEntity.ok().body(lembretes);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/lembretes/{id}")
    public ResponseEntity<?> buscarLembrete(@PathVariable long id) {
        try {
            Lembrete lembrete = fachada.buscarLembreteAgiota(id, applicationService.getAgiotaLogado().getId());
            return ResponseEntity.ok().body(lembrete);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/lembretes")
    public ResponseEntity<?> criarLembrete(@RequestBody @Valid LembreteDTO lembreteDTO) {
        try {
            applicationService.getAgiotaLogado().getId();

            Lembrete lembrete = fachada.criarLembrete(lembreteDTO);
            return ResponseEntity.ok().body(lembrete);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}