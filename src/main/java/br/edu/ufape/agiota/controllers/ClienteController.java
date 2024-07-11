package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.dtos.ClienteDTO;
import br.edu.ufape.agiota.fachada.Fachada;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Cliente;
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

    @GetMapping
    public List<Cliente> listarClientes() {
        return fachada.listarClientes();
    }

    @PostMapping
    public ResponseEntity<?> criarCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        try {
            Cliente cliente = fachada.criarCliente(clienteDTO);
            return ResponseEntity.ok().body(cliente);
        } catch (RegistroNaoEncontradoException e) {
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> atualizarCliente(@RequestBody @Valid ClienteDTO clienteDTO, @PathVariable long id) {
        try {
            Cliente cliente = fachada.atualizarCliente(clienteDTO, id);
            return ResponseEntity.ok().body(cliente);
        } catch (RegistroNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
