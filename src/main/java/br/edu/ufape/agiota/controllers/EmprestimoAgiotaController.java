package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.AprovarEmprestimoDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.OperacaoNaoPermitidaException;
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
@RequestMapping("/agiota")
public class EmprestimoAgiotaController {

    @Autowired
    private Fachada fachada;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/emprestimos")
    public ResponseEntity<?> listarEmprestimosAgiota() {
        List<Emprestimo> result = fachada.listarEmprestimosAgiota(applicationService.getAgiotaLogado().getId());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/emprestimos/{emprestimoId}")
    public ResponseEntity<?> buscarEmprestimoAgiota(@PathVariable("emprestimoId") long emprestimoId) {
        try {
            Emprestimo emprestimo = fachada.buscarEmprestimoAgiota(applicationService.getAgiotaLogado().getId(), emprestimoId);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/emprestimos/{emprestimoId}/aprovar")
    public ResponseEntity<?> aprovarSolicitacao(
            @PathVariable("emprestimoId") long emprestimoId,
            @RequestBody @Valid AprovarEmprestimoDTO aprovarEmprestimoDTO
    )
    {
        try {
            Emprestimo emprestimo = fachada.aprovarSolicitacao(
                    applicationService.getAgiotaLogado().getId(),
                    emprestimoId,
                    aprovarEmprestimoDTO
            );
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (OperacaoNaoPermitidaException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }


    @PatchMapping("/emprestimos/{emprestimoId}/rejeitar")
    public ResponseEntity<?> rejeitarSolicitacao(@PathVariable("id") long id, @PathVariable("emprestimoId") long emprestimoId)
    {
        try {
            Emprestimo emprestimo = fachada.rejeitarSolicitacao(id, emprestimoId);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (OperacaoNaoPermitidaException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
