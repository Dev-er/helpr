package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoService cargoService;

    public List<Funcionario> listar() {
        //Retorna os dados da tbela em forma de lista
        // SELECT * FROM funcionario;
        return this.funcionarioRepository.findAll();
    }

    public List<Funcionario> listarPorFaixaSalarial(Double valor1, Double valor2) {
        return this.funcionarioRepository.findBySalarioEntreFaixas(valor1, valor2);
    }
    //Listar um pelo ID
    public Funcionario getFuncionario(Integer idFuncionario) {
        //SELECT * FROM cargo WHERE idCargo = ?
        //Optional = Pode haver cargo ou não
        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(idFuncionario);

        if(funcionario.isEmpty()) { // Não encontrou o cargo?
            //Não encontrou o cargo id solicitado
            throw new RuntimeException("O funcionário não foi encontrado!"); //Causa um erro com a mensagem
        } else {
            return funcionario.get(); // Extrai o cargo dentro do optional
        }
    }

    public Funcionario salvar(FuncionarioDTO dto) {
        Cargo cargo = this.cargoService.getCargo(dto.getIdCargo()); // verifica se o cargo existe mesmo
        // id, nome, email,cpf, String senha, foto, cargo
        // Transferindo informações do DTO para nossa entidade
        Funcionario funcionario = new Funcionario(null, dto.getNome(), dto.getEmail(), dto.getCpf(), dto.getSenha(), dto.getFoto(), cargo);
        Funcionario salvo = this.funcionarioRepository.save(funcionario);

        return salvo;
    }

    public Funcionario atualizar(Integer idFuncionario, FuncionarioDTO dto) {
        Funcionario funcionarioAtual = this.getFuncionario(idFuncionario);
        Cargo cargo = this.cargoService.getCargo(dto.getIdCargo());

        funcionarioAtual.setNome(dto.getNome());
        funcionarioAtual.setEmail(dto.getEmail());
        funcionarioAtual.setCpf(dto.getCpf());
        funcionarioAtual.setSenha(dto.getSenha());
        funcionarioAtual.setFoto(dto.getFoto());
        funcionarioAtual.setCargo(cargo);

        Funcionario atualizado = this.funcionarioRepository.save(funcionarioAtual);
        return atualizado;
    }

    public void deletar(Integer idFuncionario) {
        Funcionario funcionario = this.getFuncionario(idFuncionario);
        this.funcionarioRepository.delete(funcionario);
    }
}

// Exercício:
//
//Tentem implementar:
//
///funcionarios (GET) = Lista todos os funcionários.
///funcionarios/{idFuncionario} (GET) = Lista um funcionário.
//
//Obs: Montar o service e o controller, conforme feito no CRUD de Cargos.