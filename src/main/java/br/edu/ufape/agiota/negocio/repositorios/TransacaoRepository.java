package br.edu.ufape.agiota.negocio.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

	List<Transacao> findByParcelaId(long idParcela);
    List<Transacao> findByParcelaIn(List<Parcela> parcelas);
}

