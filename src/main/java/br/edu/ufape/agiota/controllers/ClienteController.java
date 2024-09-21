package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.ClienteDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private Fachada fachada;

    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return fachada.listarClientes();
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        try {
            Cliente cliente = fachada.criarCliente(clienteDTO);
            return ResponseEntity.ok().body(cliente);
        } catch (RegistroJaExistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (SenhaNulaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable long id) {
        try {
            Cliente cliente = fachada.buscarCliente(id);
            return ResponseEntity.ok().body(cliente);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping(value = "/edit")
    public ResponseEntity<?> atualizarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        try {
            Cliente cliente = fachada.atualizarCliente(clienteDTO, applicationService.getClienteLogado().getId());
            return ResponseEntity.ok().body(cliente);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
