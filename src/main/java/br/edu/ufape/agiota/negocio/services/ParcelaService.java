package br.edu.ufape.agiota.negocio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.repositorios.ParcelaRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.ParcelaServiceInterface;

@Service
public class ParcelaService implements ParcelaServiceInterface {

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Override
    public List<Parcela> listarParcelasPorEmprestimo(long emprestimoId) {
        return parcelaRepository.findByEmprestimoId(emprestimoId);
    }

    @Override
    public Parcela buscarParcela(long id) {
        Optional<Parcela> parceOpt = parcelaRepository.findById(id);

        if (parceOpt.isPresent()) return parceOpt.get();

        throw new RegistroNaoEncontradoException("Parcela com o identificador " + id + " não foi encontrada!");
    }

	@Override
	public List<Parcela> listarParcelas() {
		// TODO Auto-generated method stub
		return null;
	}
}
