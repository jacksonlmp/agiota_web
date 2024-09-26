package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.EmprestimoDTO;
import br.edu.ufape.agiota.dtos.RejeitarEmprestimoDTO;
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
        List<EmprestimoDTO> result = fachada.listarEmprestimosAgiota(applicationService.getAgiotaLogado().getId());
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
    public ResponseEntity<?> aprovarSolicitacao(@PathVariable("emprestimoId") long emprestimoId)
    {
        try {
            Emprestimo emprestimo = fachada.aprovarSolicitacao(
                    applicationService.getAgiotaLogado().getId(),
                    emprestimoId
            );
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (OperacaoNaoPermitidaException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PatchMapping("/emprestimos/{emprestimoId}/rejeitar")
    public ResponseEntity<?> rejeitarSolicitacao(
            @PathVariable("emprestimoId") long emprestimoId,
            @RequestBody @Valid RejeitarEmprestimoDTO rejeitarEmprestimoDTO
    )
    {
        try {
            Emprestimo emprestimo = fachada.rejeitarSolicitacao(applicationService.getAgiotaLogado().getId(), emprestimoId, rejeitarEmprestimoDTO);
            return ResponseEntity.ok().body(emprestimo);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (OperacaoNaoPermitidaException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
