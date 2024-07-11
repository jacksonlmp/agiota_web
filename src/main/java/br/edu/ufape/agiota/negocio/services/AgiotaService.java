package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.repositorios.AgiotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgiotaService {
    @Autowired
    private AgiotaRepository agiotaRepository;

    public Agiota saveAgiota(Agiota agiota) {
        return agiotaRepository.save(agiota);
    }

    public List<Agiota> getAllAgiotas() {
        return agiotaRepository.findAll();
    }

    public Optional<Agiota> getAgiotaById(Integer id) {
        return agiotaRepository.findById(id);
    }

}
