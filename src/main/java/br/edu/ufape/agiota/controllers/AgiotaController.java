package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.AgiotaDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/agiotas")
public class AgiotaController {
    @Autowired
    private Fachada fachada;

    @PostMapping
    public ResponseEntity<?> criarAgiota(@RequestBody @Valid AgiotaDTO agiotaDTO) throws RegistroJaExistenteException, SenhaNulaException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (nonNull(authentication) || !(authentication.getPrincipal() instanceof Usuario usuario)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            long clienteId = usuario.getId();

            Agiota agiota = fachada.criarAgiota(agiotaDTO);
            return ResponseEntity.ok().body(agiota);
        } catch (RegistroJaExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SenhaNulaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public List<Agiota> listarAgiotas() {
        return fachada.listarAgiotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAgiota(@PathVariable long id) throws RegistroNaoEncontradoException {
        try {
            Agiota agiota = fachada.buscarAgiota(id);
            return ResponseEntity.ok().body(agiota);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarAgiota(@RequestBody @Valid AgiotaDTO agiotaDTO, @PathVariable long id) throws Exception  {
        try {
            Agiota agiota = fachada.atualizarAgiota(agiotaDTO, id);;
            return ResponseEntity.ok().body(agiota);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

