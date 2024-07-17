package br.edu.ufape.agiota.fachada;

import br.edu.ufape.agiota.dtos.AgiotaDTO;
import br.edu.ufape.agiota.dtos.AvaliacaoDTO;
import br.edu.ufape.agiota.dtos.ClienteDTO;
import br.edu.ufape.agiota.dtos.LembreteDTO;
import br.edu.ufape.agiota.dtos.TransacaoDTO;
import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.fachada.exceptions.RegistroJaExistenteException;
import br.edu.ufape.agiota.fachada.exceptions.SenhaNulaException;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Avaliacao;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Lembrete;
import br.edu.ufape.agiota.negocio.basica.Parcela;
import br.edu.ufape.agiota.negocio.basica.Transacao;
import br.edu.ufape.agiota.negocio.services.interfaces.AgiotaServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.AvaliacaoServiceInterface;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.services.interfaces.ClienteServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.LembreteServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.ParcelaServiceInterface;
import br.edu.ufape.agiota.negocio.services.interfaces.TransacaoServiceInterface;
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
    private ParcelaServiceInterface parcelasService;

    @Autowired
    private LembreteServiceInterface lembreteService;

    @Autowired
    private TransacaoServiceInterface transacaoService;
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

    public Avaliacao avaliarUsuario(AvaliacaoDTO avaliacaoDTO) {
        return avaliacaoService.avaliarUsuario(avaliacaoDTO);
    }

    public List<Avaliacao> buscarAvaliacoesDoUsuario(long idUsuario) {
        return avaliacaoService.buscarAvaliacoesDoUsuario(idUsuario);
    }

    public List<Parcela> listarParcelasPorEmprestimo(long emprestimoId) {
        return parcelasService.listarParcelasPorEmprestimo(emprestimoId);
    }

    public Parcela buscarParcela(long id) throws RegistroNaoEncontradoException {
        return parcelasService.buscarParcela(id);
    }

    public Lembrete buscarLembrete(long id) throws RegistroNaoEncontradoException {
        return lembreteService.buscarLembrete(id);
    }

    public List<Lembrete> listarLembretesPorUsuarioId(long usuarioId) {
        return lembreteService.listarLembretesPorUsuarioId(usuarioId);
    }

    public Lembrete criarLembrete(LembreteDTO lembreteDTO) throws RegistroNaoEncontradoException {
        return lembreteService.criarLembrete(lembreteDTO);
    }

    public Transacao criarTransacao(TransacaoDTO transacaoDTO) throws RegistroNaoEncontradoException {
        return transacaoService.criarTransacao(transacaoDTO);
    }

    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    public Transacao buscarTransacao(long id) throws RegistroNaoEncontradoException {
        return transacaoService.buscarTransacao(id);
    }

    public List<Transacao> buscarTransacoesPorParcela(long idParcela) throws RegistroNaoEncontradoException {
        return transacaoService.buscarTransacoesPorParcela(idParcela);
    }
}