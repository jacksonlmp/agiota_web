package br.edu.ufape.agiota.negocio.services;

import br.edu.ufape.agiota.dtos.EmprestimoClienteDTO;
import br.edu.ufape.agiota.fachada.exceptions.RegistroNaoEncontradoException;
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

@Service
public class EmprestimoService implements EmprestimoServiceInterface {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private AgiotaService agiotaService;

    @Autowired
    private ClienteService clienteService;

    @Override
    public List<Emprestimo> listarEmprestimosCliente(long clienteId) {
        return emprestimoRepository.findAllByClienteId(clienteId);
    }

    @Override
    public Emprestimo criarSolicitacaoEmprestimo(EmprestimoClienteDTO emprestimoClienteDTO) {
        Cliente cliente = clienteService.buscarCliente(emprestimoClienteDTO.getClienteId());
        Agiota agiota = agiotaService.buscarAgiota(emprestimoClienteDTO.getAgiotaId());

        Emprestimo emprestimo = new Emprestimo();

        emprestimoClienteDTO.toSolicitarEmprestimo(emprestimo, cliente, agiota);

        return emprestimoRepository.save(emprestimo);
    }

    @Override
    public boolean cancelarSolicitacaoEmprestimo(long idEmprestimo) {
        Emprestimo emprestimo = buscarEmprestimo(idEmprestimo);

        emprestimo.checarPossibilidadeDeCancelar();

        emprestimo.setStatus(StatusEmprestimo.CANCELADO);

        emprestimoRepository.save(emprestimo);

        return true;
    }

    @Override
    public Emprestimo buscarEmprestimo(long id) {
        Optional<Emprestimo> emprestimoOpt = emprestimoRepository.findById(id);

        if (emprestimoOpt.isEmpty()) {
            throw new RegistroNaoEncontradoException("Emprestimo não encontrado para o identificador " + id);
        }

        return emprestimoOpt.get();
    }

}
