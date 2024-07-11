package br.edu.ufape.agiota.negocio.repositorios;

import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByAvaliadoId(long id);

    @Query("SELECT SUM(a.nota) FROM Avaliacao a WHERE a.avaliado.id = :id")
    Integer somarNotasDoAvaliado(long id);

    int countByAvaliadoId(long id);

}
