package br.edu.ufape.agiota.negocio.services.interfaces;

import java.util.List;

import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Parcela;

public interface ParcelaServiceInterface {
    List<Parcela> Parcelas = null;
	static List<Parcela> listarParcelas() {
		return Parcelas;
	}
    Parcela buscarParcela(long id) throws RegistroNaoEncontradoException;
}
