package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import java.util.List;

public interface ParcelaServiceInterface {
    List<Parcela> listarParcelasPorEmprestimo(long emprestimoId);
    Parcela buscarParcela(long id);
    void gerarParcelas(Emprestimo emprestimo);
}