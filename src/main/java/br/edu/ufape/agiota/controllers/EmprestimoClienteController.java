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
@RequestMapping("/cliente/emprestimos")
public class EmprestimoClienteController {

    @Autowired
    private Fachada fachada;

    @GetMapping
    public ResponseEntity<?> listarEmprestimosCliente(@RequestParam("clienteId") long clienteId) {
        List<Emprestimo> result = fachada.listarEmprestimosCliente(clienteId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEmprestimo(@PathVariable long id) {
        try {
            Emprestimo emprestimo = fachada.buscarEmprestimo(id);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> criarSolicitacaoEmprestimo(@RequestBody @Valid EmprestimoClienteDTO emprestimoClienteDTO) {
        try {
            Emprestimo emprestimo = fachada.criarSolicitacaoEmprestimo(emprestimoClienteDTO);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> cancelarSolicitacaoEmprestimo(@PathVariable long id)
    {
        try {
            return ResponseEntity.ok().body(fachada.cancelarSolicitacaoEmprestimo(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
