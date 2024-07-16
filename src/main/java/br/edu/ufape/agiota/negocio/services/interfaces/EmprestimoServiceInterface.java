package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;

import java.util.List;

public interface EmprestimoServiceInterface {
    List<Emprestimo> listarEmprestimosCliente(long clienteId);

    Emprestimo criarSolicitacaoEmprestimo(EmprestimoClienteDTO emprestimoClienteDTO);

    boolean cancelarSolicitacaoEmprestimo(long idEmprestimo);

    Emprestimo buscarEmprestimo(long id);
}
