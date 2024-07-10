package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> index(){
        return clienteService.usuarios();
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) throws Exception {
        return clienteService.create(cliente);
    }

    @GetMapping("/{id}")
    public Cliente find(@PathVariable long id) throws Exception {
        return clienteService.find(id);
    }

    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Optional<Cliente> update(@RequestBody Cliente cliente, @PathVariable long id) throws Exception {
        return clienteService.update(cliente, id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable long id) {
        return clienteService.delete(id);
    }

}
