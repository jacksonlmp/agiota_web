package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.negocio.basica.Parcela;
import java.util.List;

public interface ParcelaServiceInterface {
    List<Parcela> listarParcelasPorEmprestimo(long emprestimoId);
    Parcela buscarParcela(long id);
}