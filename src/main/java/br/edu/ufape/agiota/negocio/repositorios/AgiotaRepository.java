package br.edu.ufape.agiota.negocio.repositorios;

import br.edu.ufape.agiota.negocio.basica.Agiota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgiotaRepository extends JpaRepository<Agiota, Long> {
    public Agiota findByEmail(String email);
}
