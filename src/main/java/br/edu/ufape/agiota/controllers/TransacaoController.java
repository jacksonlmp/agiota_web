package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private Fachada fachada;

    @PostMapping
    public ResponseEntity<?> criarTransacao(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        Transacao transacao = fachada.criarTransacao(transacaoDTO);
        return ResponseEntity.ok().body(transacao);
    }

    @GetMapping("/emprestimo/{emprestimoId}")
    public ResponseEntity<?> listarTransacoesPorEmprestimo(@PathVariable long emprestimoId) {
        try {
            List<Transacao> transacoes = fachada.listarTransacoesPorEmprestimo(emprestimoId);
            return ResponseEntity.ok().body(transacoes);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTransacao(@PathVariable long id) {
        try {
            Transacao transacao = fachada.buscarTransacao(id);
            return ResponseEntity.ok().body(transacao);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/parcela/{parcelaId}")
    public ResponseEntity<?> buscarTransacoesPorParcela(@PathVariable long parcelaId) {
        try {
            List<Transacao> transacoes = fachada.buscarTransacoesPorParcela(parcelaId);
            return ResponseEntity.ok().body(transacoes);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}