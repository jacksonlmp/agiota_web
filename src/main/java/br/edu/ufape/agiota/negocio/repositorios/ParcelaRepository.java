package br.edu.ufape.agiota.negocio.repositorios;

import br.edu.ufape.agiota.negocio.basica.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

    @Query("SELECT p FROM Parcela p JOIN Emprestimo e ON p.emprestimo.id = e.id " +
            "WHERE e.id = :idEmprestimo AND (e.cliente.id = :idUsuario OR e.agiota.id = :idUsuario)")
    List<Parcela> findByEmprestimoIdAndUsuarioId(@Param("idEmprestimo") long idEmprestimo, @Param("idUsuario") long idUsuario);

    Parcela findByIdAndEmprestimoClienteId(long idParcela, long clienteId);
}