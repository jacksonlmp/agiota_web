package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.AgiotaDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import java.util.List;

public interface AgiotaServiceInterface {
    List<Agiota> listarAgiotas();
    Agiota criarAgiota(AgiotaDTO agiotaDTO) throws RegistroNaoEncontradoException;

    Agiota buscarAgiota(long id) throws RegistroNaoEncontradoException;

    Agiota atualizarAgiota(AgiotaDTO agiotaDTO, long id) throws RegistroNaoEncontradoException;

}
