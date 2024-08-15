package br.edu.ufape.agiota.negocio.repositorios;

import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findAllByClienteId(Long clienteId);

    List<Emprestimo> findAllByAgiotaId(Long agiotaId);

    Emprestimo findByIdAndAgiotaId(Long id, Long agiotaId);

    Emprestimo findByIdAndClienteId(Long id, Long clienteId);

}
