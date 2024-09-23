package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.dtos.RejeitarEmprestimoDTO;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;

import java.util.List;

public interface EmprestimoServiceInterface {
    List<Emprestimo> listarEmprestimosCliente(long clienteId);

    Emprestimo criarSolicitacaoEmprestimo(EmprestimoClienteDTO emprestimoClienteDTO, long clienteId);

    boolean cancelarSolicitacaoEmprestimo(long idEmprestimo, long clienteId);

    Emprestimo buscarEmprestimoCliente(long clienteId, long idEmprestimo);

    List<Emprestimo> listarEmprestimosAgiota(long agiotaId);

    Emprestimo buscarEmprestimoAgiota(long id, long agiotaId);

    Emprestimo aprovarSolicitacao(long agiotaId, long emprestimoId);

    Emprestimo rejeitarSolicitacao(long agiotaId, long emprestimoId, RejeitarEmprestimoDTO rejeitarEmprestimoDTO);
}
