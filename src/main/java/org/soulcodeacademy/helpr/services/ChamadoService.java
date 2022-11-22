package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.ChamadoDTO;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    public List<Chamado> ListarChamados() {
        return this.chamadoRepository.findAll();
    }

    public Chamado getChamado(Integer idChamado) {
        //ou return.this.chamadoRepository.findById(idChamado)
        //.orElseThrow(() -> new RuntimeException("Chamado não Encontrado"));
        Optional<Chamado> chamado = this.chamadoRepository.findById(idChamado);

        if(chamado.isEmpty()) {
            throw new RuntimeException("Chamado não encontrado");
        } else {
            return chamado.get();
        }

    }

    public Chamado salvar(ChamadoDTO dto) {
        Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());
        Chamado chamado = new Chamado(null, dto.getTitulo(), dto.getDescricao());
        chamado.setCliente(cliente);

        return this.chamadoRepository.save(chamado);

    }

    public Chamado atualizar(Integer idChamado, @Valid ChamadoDTO dto) {
        Chamado chamadoAtual = this.getChamado(idChamado);
        Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());
        chamadoAtual.setTitulo(dto.getTitulo());
        chamadoAtual.setDescricao(dto.getDescricao());
        chamadoAtual.setCliente(cliente);

        if(dto.getIdFuncionario() == null) {
            throw new RuntimeException("idFuncionario obrigatório");
        } else {
            Funcionario funcionario = this.funcionarioService.getFuncionario(dto.getIdFuncionario());

            switch (dto.getStatus()) {
                case RECEBIDO -> {
                    chamadoAtual.setStatus(StatusChamado.RECEBIDO);
                    chamadoAtual.setFuncionario(null);
                    chamadoAtual.setDataFechamento(null);
                }
                case ATRIBUIDO -> {
                    chamadoAtual.setStatus(StatusChamado.ATRIBUIDO);
                    chamadoAtual.setFuncionario(funcionario);
                    chamadoAtual.setDataFechamento(null);
                }
                case CONCLUIDO -> {
                    chamadoAtual.setStatus(StatusChamado.CONCLUIDO);
                    chamadoAtual.setFuncionario(funcionario);
                    chamadoAtual.setDataFechamento(LocalDate.now());
                }
            }
        }
            return this.chamadoRepository.save(chamadoAtual);
    }

    public List<Chamado> listarPorStatus(StatusChamado status) {
        return this.chamadoRepository.findByStatus(status);
    }
    public List<Chamado> listarPorFuncionario(Integer idFuncionario) {
        Funcionario funcionario = this.funcionarioService.getFuncionario(idFuncionario);
        return  this.chamadoRepository.findByFuncionario(funcionario);
    }

    public List<Chamado> listarPorCliente(Integer idCliente) {
        Cliente cliente = this.clienteService.getCliente(idCliente);
        return this.chamadoRepository.findByCliente(cliente);
    }

    public List<Chamado> listarPorIntervalorDatas(LocalDate data1, LocalDate data2) {
        return this.chamadoRepository.buscarEntreDatas(data1, data2);
    }
}
