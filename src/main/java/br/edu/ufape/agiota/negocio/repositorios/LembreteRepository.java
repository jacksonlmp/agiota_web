package br.edu.ufape.agiota.negocio.repositorios;

import br.edu.ufape.agiota.negocio.basica.Lembrete;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

	String findAllByAgiotaQuery = "SELECT lembrete.id AS id, lembrete.data AS data, " +
			"lembrete.texto AS texto, lembrete.parcela_id AS parcela_id " +
			"FROM lembrete " +
			"JOIN parcela AS p ON p.id = lembrete.parcela_id " +
			"JOIN emprestimo AS e ON e.id = p.emprestimo_id " +
			"WHERE e.agiota_id = :agiotaId";

	String findByIdAndAgiotaQuery = "SELECT lembrete.id AS id, lembrete.data AS data, " +
			"lembrete.texto AS texto, lembrete.parcela_id AS parcela_id " +
			"FROM lembrete " +
			"JOIN parcela AS p ON p.id = lembrete.parcela_id " +
			"JOIN emprestimo AS e ON e.id = p.emprestimo_id " +
			"WHERE e.agiota_id = :agiotaId AND lembrete.id = :id " +
			"limit 1";

	@Query(value = findAllByAgiotaQuery, nativeQuery = true)
	List<Lembrete> findAllByAgiota(long agiotaId);

	@Query(value = findByIdAndAgiotaQuery, nativeQuery = true)
	Optional<Lembrete> findByIdAndAgiotaId(long id, long agiotaId);
}