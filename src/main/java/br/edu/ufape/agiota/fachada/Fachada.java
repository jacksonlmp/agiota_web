package br.edu.ufape.agiota.fachada;

import br.edu.ufape.agiota.dtos.*;
import br.edu.ufape.agiota.dtos.AgiotaDTO;
import br.edu.ufape.agiota.dtos.AvaliacaoDTO;
import br.edu.ufape.agiota.dtos.ClienteDTO;
import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.*;
import br.edu.ufape.agiota.negocio.services.interfaces.AgiotaServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.AvaliacaoServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.ClienteServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.EmprestimoServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.LembreteServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.ParcelaServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.TransacaoServiceInterface;
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

    @Autowired
    private LembreteServiceInterface lembreteService;

    @Autowired
    private ParcelaServiceInterface parcelasService;

    @Autowired
    private TransacaoServiceInterface transacaoService;

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

    public Avaliacao avaliarUsuario(AvaliacaoDTO avaliacaoDTO, Usuario avaliador) {
        return avaliacaoService.avaliarUsuario(avaliacaoDTO, avaliador);
    }

    public List<Avaliacao> buscarAvaliacoesDoUsuario(long idUsuario) {
        return avaliacaoService.buscarAvaliacoesDoUsuario(idUsuario);
    }

    public List<EmprestimoDTO> listarEmprestimosCliente(long clienteId) {
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

    public  List<EmprestimoDTO> listarEmprestimosAgiota(long agiotaId) {
        return emprestimoService.listarEmprestimosAgiota(agiotaId);
    }

    public Emprestimo buscarEmprestimoAgiota(long idEmprestimo, long agiotaId) {
        return emprestimoService.buscarEmprestimoAgiota(idEmprestimo, agiotaId);
    }

    public Emprestimo aprovarSolicitacao(long agiotaId, long emprestimoId)
    {
        return emprestimoService.aprovarSolicitacao(agiotaId, emprestimoId);
    }

    public Emprestimo rejeitarSolicitacao(long agiotaId, long emprestimoId, RejeitarEmprestimoDTO rejeitarEmprestimoDTO)
    {
        return emprestimoService.rejeitarSolicitacao(agiotaId, emprestimoId, rejeitarEmprestimoDTO);
    }

    public Lembrete buscarLembreteAgiota(long id, long idAgiota) throws RegistroNaoEncontradoException {
        return lembreteService.buscarLembreteAgiota(id, idAgiota);
    }

    public List<Lembrete> listarLembretesPorAgiotaId(long agiotaId) {
        return lembreteService.listarLembretesPorAgiotaId(agiotaId);
    }

    public Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroNaoEncontradoException {
        return lembreteService.criarLembrete(lembreteDTO);
    }

    public Parcela buscarParcela(long idParcela, long idUsuario) throws RegistroNaoEncontradoException {
        return parcelasService.buscarParcela(idParcela, idUsuario);
    }

    public List<Parcela> listarParcelasPorEmprestimo(long idEmprestimo, long idUsuario) {
        return parcelasService.listarParcelasPorEmprestimo(idEmprestimo, idUsuario);
    }

    public List<Transacao> buscarTransacoesPorParcela(long idParcela) throws RegistroNaoEncontradoException {
        return transacaoService.buscarTransacoesPorParcela(idParcela);
    }

    public Transacao buscarTransacao(long id) throws RegistroNaoEncontradoException {
        return transacaoService.buscarTransacao(id);
    }

    public Transacao criarTransacao(TransacaoDTO transacaoDTO) {
        return transacaoService.criarTransacao(transacaoDTO);
    }

    public List<Transacao> listarTransacoesPorEmprestimo(long emprestimoId) throws RegistroNaoEncontradoException {
        return transacaoService.listarTransacoesPorEmprestimo(emprestimoId);
    }

}