package br.edu.ufape.agiota.negocio.repositorios;

import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findByParcelaId(long idParcela);

    List<Transacao> findByParcelaIn(List<Parcela> parcelas);

    @Query("SELECT t FROM Transacao t " +
            "JOIN t.parcela p " +
            "JOIN p.emprestimo e " +
            "WHERE t.id = :idTransacao AND (e.agiota.id = :idUsuario OR e.cliente.id = :idUsuario)")
    Optional<Transacao> findByTransacaoIdAndUsuarioId(@Param("idTransacao") long idTransacao, @Param("idUsuario") long usuarioId);

}

