package br.edu.ufape.agiota.controllers;

import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.services.AgiotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agiotas")
public class AgiotaController {
    @Autowired
    private AgiotaService agiotaService;

    @PostMapping
    public Agiota criarAgiota(@RequestBody Agiota agiota) throws Exception{
        return agiotaService.saveAgiota(agiota);
    }

    @GetMapping
    public List<Agiota> buscarTodosAgiotas() {
        return agiotaService.getAllAgiotas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agiota> buscarAgiotaPorId(@PathVariable Integer id) {
        Optional<Agiota> agiota = agiotaService.getAgiotaById(id);
        return agiota.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agiota> atualizarAgiota(@PathVariable Integer id, @RequestBody Agiota agiotaDetails) {
        Optional<Agiota> optionalAgiota = agiotaService.getAgiotaById(id);
        if (optionalAgiota.isPresent()) {
            Agiota agiota = optionalAgiota.get();
            agiota.setNome(agiotaDetails.getNome());
            agiota.setEmail(agiotaDetails.getEmail());
            agiota.setSenha(agiotaDetails.getSenha());
            agiota.setTelefone(agiotaDetails.getTelefone());
            agiota.setCpf(agiotaDetails.getCpf());
            agiota.setReputacao(agiotaDetails.getReputacao());
            agiota.setEndereco(agiotaDetails.getEndereco());
            agiota.setTaxaDeJuros(agiotaDetails.getTaxaDeJuros());
            agiota.setMetodoCobranca(agiotaDetails.getMetodoCobranca());
            agiota.setPeriodoTaxa(agiotaDetails.getPeriodoTaxa());

            final Agiota updatedAgiota = agiotaService.saveAgiota(agiota);
            return ResponseEntity.ok(updatedAgiota);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
