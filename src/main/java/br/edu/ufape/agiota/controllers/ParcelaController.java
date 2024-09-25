package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Usuario;
import br.edu.ufape.agiota.negocio.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parcelas")
public class ParcelaController {

    @Autowired
    private Fachada fachada;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/emprestimo/{emprestimoId}")
    public List<Parcela> listarParcelasPorEmprestimo(@PathVariable long emprestimoId) {
        return fachada.listarParcelasPorEmprestimo(emprestimoId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarParcela(@PathVariable long id) {
        try {
            Usuario usuarioLogado = (Usuario) applicationService.getUsuarioLogado();
            Parcela parcela = fachada.buscarParcela(id, usuarioLogado.getId());
            return ResponseEntity.ok().body(parcela);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
