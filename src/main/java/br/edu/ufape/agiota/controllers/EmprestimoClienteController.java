package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cliente")
public class EmprestimoClienteController {

    @Autowired
    private Fachada fachada;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/emprestimos")
    public ResponseEntity<?> listarEmprestimosCliente() {
        List<Emprestimo> result = fachada.listarEmprestimosCliente(applicationService.getCliente().getId());

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/emprestimos/teste")
    public ResponseEntity<?> teste() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Cliente cliente = (Cliente) authentication.getPrincipal();

        return ResponseEntity.ok().body(cliente.getClass());
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
