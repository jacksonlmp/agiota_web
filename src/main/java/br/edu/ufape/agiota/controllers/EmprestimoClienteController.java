package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.dtos.EmprestimoDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<EmprestimoDTO> result = fachada.listarEmprestimosCliente(applicationService.getClienteLogado().getId());

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/emprestimos/{emprestimoId}")
    public ResponseEntity<?> buscarEmprestimo(@PathVariable long emprestimoId) {
        try {
            Emprestimo emprestimo = fachada.buscarEmprestimo(applicationService.getClienteLogado().getId(), emprestimoId);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/emprestimos")
    public ResponseEntity<?> criarSolicitacaoEmprestimo(@RequestBody @Valid EmprestimoClienteDTO emprestimoClienteDTO) {
        try {
            Emprestimo emprestimo = fachada.criarSolicitacaoEmprestimo(emprestimoClienteDTO, applicationService.getClienteLogado().getId());
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/emprestimos/{emprestimoId}")
    public ResponseEntity<?> cancelarSolicitacaoEmprestimo(@PathVariable long emprestimoId)
    {
        try {
            return ResponseEntity.ok().body(fachada.cancelarSolicitacaoEmprestimo(emprestimoId, applicationService.getClienteLogado().getId()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
