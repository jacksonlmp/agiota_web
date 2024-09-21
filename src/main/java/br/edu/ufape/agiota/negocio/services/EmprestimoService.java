package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.dtos.RejeitarEmprestimoDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
import br.edu.ufape.agiota.infra.ChecarDataNoPassado;
import br.edu.ufape.agiota.negocio.basica.Agiota;
import br.edu.ufape.agiota.negocio.basica.Cliente;
import br.edu.ufape.agiota.negocio.basica.Emprestimo;
import br.edu.ufape.agiota.negocio.basica.enums.StatusEmprestimo;
import br.edu.ufape.agiota.negocio.repositorios.EmprestimoRepository;
import br.edu.ufape.agiota.negocio.services.interfaces.EmprestimoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class EmprestimoService implements EmprestimoServiceInterface {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private AgiotaService agiotaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ParcelaService parcelaService;

    @Autowired
    private ChecarDataNoPassado checarDataNoPassado;

    @Override
    public List<Emprestimo> listarEmprestimosCliente(long clienteId) {
        return emprestimoRepository.findAllByClienteId(clienteId);
    }

    @Override
    public Emprestimo criarSolicitacaoEmprestimo(EmprestimoClienteDTO emprestimoClienteDTO, long clienteId) {
        checarDataNoPassado.handle(emprestimoClienteDTO.getDataDeVencimentoInicial(), "A data de vencimento inicial não pode ser no passado.");
        checarDataNoPassado.handle(emprestimoClienteDTO.getDataEmprestimo(), "A data do empréstimo não pode ser no passado.");

        Cliente cliente = clienteService.buscarCliente(clienteId);
        Agiota agiota = agiotaService.buscarAgiota(emprestimoClienteDTO.getAgiotaId());

        Emprestimo emprestimo = new Emprestimo();

        emprestimoClienteDTO.toSolicitarEmprestimo(emprestimo, cliente, agiota);

        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public boolean cancelarSolicitacaoEmprestimo(long idEmprestimo, long clienteId) {
        Emprestimo emprestimo = buscarEmprestimoCliente(clienteId, idEmprestimo);

        emprestimo.checarPossibilidadeDeCancelar();

        emprestimo.setStatus(StatusEmprestimo.CANCELADO);

        emprestimoRepository.save(emprestimo);

        return true;
    }

    public Emprestimo buscarEmprestimo(long id) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(id);

        if (emprestimoOpt.isEmpty()) {
            throw new RegistroNaoEncontradoException("Emprestimo não encontrado para o identificador " + id);
        }

        return emprestimoOpt.get();
    }

    @Override
    public List<Emprestimo> listarEmprestimosAgiota(long agiotaId) {
        return emprestimoRepository.findAllByAgiotaId(agiotaId);
    }

    @Override
    public Emprestimo buscarEmprestimoAgiota(long agiotaId, long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findByIdAndAgiotaId(emprestimoId, agiotaId);

        if (isNull(emprestimo)) {
            throw new RegistroNaoEncontradoException("Empréstimo não encontrado");
        }

        return emprestimo;
    }

    @Override
    public Emprestimo buscarEmprestimoCliente(long clienteId, long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findByIdAndClienteId(emprestimoId, clienteId);

        if (isNull(emprestimo)) {
            throw new RegistroNaoEncontradoException("Empréstimo não encontrado");
        }

        return emprestimo;
    }

    @Override
    public Emprestimo aprovarSolicitacao(long agiotaId, long emprestimoId)  {

        Agiota agiota = agiotaService.buscarAgiota(agiotaId);
        Emprestimo emprestimo = buscarEmprestimo(emprestimoId);

        checarDataNoPassado.handle(emprestimo.getDataEmprestimo(), "A data do empréstimo não pode ser no passado.");

        emprestimo.checarAprocacao();
        emprestimo.setStatus(StatusEmprestimo.APROVADO);

        Emprestimo savedEmprestimo = emprestimoRepository.save(emprestimo);

        parcelaService.gerarParcelas(savedEmprestimo);

        return savedEmprestimo;
    }

    @Override
    public Emprestimo rejeitarSolicitacao(long agiotaId, long emprestimoId, RejeitarEmprestimoDTO rejeitarEmprestimoDTO)
    {
        agiotaService.buscarAgiota(agiotaId);
        Emprestimo emprestimo = buscarEmprestimo(emprestimoId);

        emprestimo.checarRejeicao();

        rejeitarEmprestimoDTO.rejeitar(emprestimo);

        return emprestimoRepository.save(emprestimo);
    }

}
