package br.edu.ufape.agiota.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Parcela;

@RestController
@RequestMapping("/parcelas")
public class ParcelaController {

    @Autowired
    private Fachada fachada;

    @GetMapping
    public List<Parcela> listarParcelas() {
        return fachada.listarParcelas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarParcela(@PathVariable long id) {
        try {
            Parcela parcela = fachada.buscarParcela(id);
            return ResponseEntity.ok().body(parcela);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
