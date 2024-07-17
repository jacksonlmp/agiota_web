package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Lembrete;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {

    @Autowired
    private Fachada fachada;

    @GetMapping
    public ResponseEntity<?> listarLembretesPorUsuarioId(@RequestParam long usuarioId) {
        try {
            List<Lembrete> lembretes = fachada.listarLembretesPorUsuarioId(usuarioId);
            return ResponseEntity.ok().body(lembretes);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarLembrete(@PathVariable long id) {
        try {
            Lembrete lembrete = fachada.buscarLembrete(id);
            return ResponseEntity.ok().body(lembrete);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criarLembrete(@RequestBody @Valid LembreteDTO lembreteDTO) {
        try {
            Lembrete lembrete = fachada.criarLembrete(lembreteDTO);
            return ResponseEntity.ok().body(lembrete);
        } catch (RegistroJaExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}