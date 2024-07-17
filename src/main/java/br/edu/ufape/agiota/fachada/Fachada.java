package br.edu.ufape.agiota.fachada;

import br.edu.ufape.agiota.dtos.AgiotaDTO;
import br.edu.ufape.agiota.dtos.AvaliacaoDTO;
import br.edu.ufape.agiota.dtos.ClienteDTO;
import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.Lembrete;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;
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

    public Avaliacao avaliarUsuario(AvaliacaoDTO avaliacaoDTO) {
        return avaliacaoService.avaliarUsuario(avaliacaoDTO);
    }

    public List<Avaliacao> buscarAvaliacoesDoUsuario(long idUsuario) {
        return avaliacaoService.buscarAvaliacoesDoUsuario(idUsuario);
    }

    public List<Emprestimo> listarEmprestimosCliente(long clienteId) {
        return emprestimoService.listarEmprestimosCliente(clienteId);
    }

    public Emprestimo criarSolicitacaoEmprestimo(EmprestimoClienteDTO emprestimoClienteDTO)
    {
        return emprestimoService.criarSolicitacaoEmprestimo(emprestimoClienteDTO);
    }

    public boolean cancelarSolicitacaoEmprestimo(long idEmprestimo) {
        return emprestimoService.cancelarSolicitacaoEmprestimo(idEmprestimo);
    }

    public Emprestimo buscarEmprestimo(long idEmprestimo) {
        return emprestimoService.buscarEmprestimo(idEmprestimo);
    }
    
    public Lembrete buscarLembrete(long id) throws RegistroNaoEncontradoException {
        return lembreteService.buscarLembrete(id);
    }

    public List<Lembrete> listarLembretesPorUsuarioId(long usuarioId) {
        return lembreteService.listarLembretesPorUsuarioId(usuarioId);
    }

    public Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroJaExistenteException {
        return lembreteService.criarLembrete(lembreteDTO);
    }
    
    public Parcela buscarParcela(long id) throws RegistroNaoEncontradoException {
        return parcelasService.buscarParcela(id);
    }
    
    public List<Parcela> listarParcelasPorEmprestimo(long emprestimoId) {
        return parcelasService.listarParcelasPorEmprestimo(emprestimoId);
    }
    
    public List<Transacao> buscarTransacoesPorParcela(long idParcela) throws RegistroNaoEncontradoException {
        return transacaoService.buscarTransacoesPorParcela(idParcela);
    }
    
    public Transacao buscarTransacao(long id) throws RegistroNaoEncontradoException {
        return transacaoService.buscarTransacao(id);
    }

    public Transacao criarTransacao(TransacaoDTO transacaoDTO) throws RegistroNaoEncontradoException {
        return transacaoService.criarTransacao(transacaoDTO);
    }

    public List<Transacao> listarTransacoesPorEmprestimo(long emprestimoId) throws RegistroNaoEncontradoException {
        return transacaoService.listarTransacoesPorEmprestimo(emprestimoId);
    }
    
}