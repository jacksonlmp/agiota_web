package br.edu.ufape.agiota.fachada;

import br.edu.ufape.agiota.dtos.*;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.services.interfaces.AgiotaServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.AvaliacaoServiceInterface;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.services.interfaces.ClienteServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.EmprestimoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Fachada {
    @Autowired
    private ClienteServiceInterface clienteService;

    @Autowired
    private AgiotaServiceInterface agiotaService;

    @Autowired
    private AvaliacaoServiceInterface avaliacaoService;

    @Autowired
    private EmprestimoServiceInterface emprestimoService;

    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    public Cliente buscarCliente(long id) throws RegistroNaoEncontradoException {
        return clienteService.buscarCliente(id);
    }

    public Cliente criarCliente(ClienteDTO clienteDTO) throws RegistroJaExistenteException, SenhaNulaException {
        return clienteService.criarCliente(clienteDTO);
    }

    public Cliente atualizarCliente(ClienteDTO clienteDTO, long id) throws RegistroNaoEncontradoException {
        return clienteService.atualizarCliente(clienteDTO, id);
    }

    public List<Agiota> listarAgiotas() {
        return agiotaService.listarAgiotas();
    }
    public Agiota criarAgiota(AgiotaDTO agiotaDTO) throws RegistroJaExistenteException, SenhaNulaException {
        return agiotaService.criarAgiota(agiotaDTO);
    }

    public Agiota buscarAgiota(long id) throws RegistroNaoEncontradoException {
        return agiotaService.buscarAgiota(id);
    }

    public Agiota atualizarAgiota(AgiotaDTO agiotaDTO, long id) throws RegistroNaoEncontradoException {
        return agiotaService.atualizarAgiota(agiotaDTO, id);
    }

    public Avaliacao avaliarUsuario(AvaliacaoDTO avaliacaoDTO) {
        return avaliacaoService.avaliarUsuario(avaliacaoDTO);
    }

    public List<Avaliacao> buscarAvaliacoesDoUsuario(long idUsuario) {
        return avaliacaoService.buscarAvaliacoesDoUsuario(idUsuario);
    }

    public List<Emprestimo> listarEmprestimosCliente(long clienteId) {
        return emprestimoService.listarEmprestimosCliente(clienteId);
    }

    public Emprestimo criarSolicitacaoEmprestimo(EmprestimoClienteDTO emprestimoClienteDTO, long clienteId)
    {
        return emprestimoService.criarSolicitacaoEmprestimo(emprestimoClienteDTO, clienteId);
    }

    public boolean cancelarSolicitacaoEmprestimo(long idEmprestimo, long clienteId) {
        return emprestimoService.cancelarSolicitacaoEmprestimo(idEmprestimo, clienteId);
    }

    public Emprestimo buscarEmprestimo(long clienteId, long idEmprestimo) {
        return emprestimoService.buscarEmprestimoCliente(clienteId, idEmprestimo);
    }

    public List<Emprestimo> listarEmprestimosAgiota(long agiotaId) {
        return emprestimoService.listarEmprestimosAgiota(agiotaId);
    }

    public Emprestimo buscarEmprestimoAgiota(long idEmprestimo, long agiotaId) {
        return emprestimoService.buscarEmprestimoAgiota(idEmprestimo, agiotaId);
    }

    public Emprestimo aprovarSolicitacao(long agiotaId, long emprestimoId, AprovarEmprestimoDTO aprovarEmprestimoDTO)
    {
        return emprestimoService.aprovarSolicitacao(agiotaId, emprestimoId, aprovarEmprestimoDTO);
    }

    public Emprestimo rejeitarSolicitacao(long agiotaId, long emprestimoId)
    {
        return emprestimoService.rejeitarSolicitacao(agiotaId, emprestimoId);
    }
}
