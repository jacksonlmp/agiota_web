package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente/{id}")
public class EmprestimoClienteController {

    @Autowired
    private Fachada fachada;

    @GetMapping("/emprestimos")
    public ResponseEntity<?> listarEmprestimosCliente(@PathVariable("id") long clienteId) {
        List<Emprestimo> result = fachada.listarEmprestimosCliente(clienteId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/emprestimos/{emprestimoId}")
    public ResponseEntity<?> buscarEmprestimo(@PathVariable("id") long clienteId, @PathVariable long emprestimoId) {
        try {
            Emprestimo emprestimo = fachada.buscarEmprestimo(clienteId, emprestimoId);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/emprestimos")
    public ResponseEntity<?> criarSolicitacaoEmprestimo(@RequestBody @Valid EmprestimoClienteDTO emprestimoClienteDTO, @PathVariable("id") long clienteId) {
        try {
            Emprestimo emprestimo = fachada.criarSolicitacaoEmprestimo(emprestimoClienteDTO, clienteId);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/emprestimos/{emprestimoId}")
    public ResponseEntity<?> cancelarSolicitacaoEmprestimo(@PathVariable long emprestimoId, @PathVariable("id") long clienteId)
    {
        try {
            return ResponseEntity.ok().body(fachada.cancelarSolicitacaoEmprestimo(emprestimoId, clienteId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
