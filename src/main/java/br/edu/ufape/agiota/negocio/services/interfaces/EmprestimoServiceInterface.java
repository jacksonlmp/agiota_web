package br.edu.ufape.agiota.negocio.services.interfaces;

import br.edu.ufape.agiota.dtos.AprovarEmprestimoDTO;
import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;

import java.util.List;

public interface EmprestimoServiceInterface {
    List<Emprestimo> listarEmprestimosCliente(long clienteId);

    Emprestimo criarSolicitacaoEmprestimo(EmprestimoClienteDTO emprestimoClienteDTO);

    boolean cancelarSolicitacaoEmprestimo(long idEmprestimo);

    Emprestimo buscarEmprestimo(long id);

    List<Emprestimo> listarEmprestimosAgiota(long agiotaId);

    Emprestimo buscarEmprestimoAgiota(long id, long agiotaId);

    Emprestimo aprovarSolicitacao(long agiotaId, long emprestimoId, AprovarEmprestimoDTO aprovarEmprestimoDTO);

    Emprestimo rejeitarSolicitacao(long agiotaId, long emprestimoId);
}
