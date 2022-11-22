package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.FuncionarioDTO;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.soulcodeacademy.helpr.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/funcionarios")
    public List<Funcionario> listar() {
        //Requisição -> Controller -> Service -> Repository - SELECT * FROM cargo;
        return this.funcionarioService.listar(); //JSON = JAVASCRIPT OBJECT NOTATION
    }

    @GetMapping("/funcionarios/salario")
    public List<Funcionario> listarPorFaixaSalarial(@RequestParam Double valor1, @RequestParam Double valor2) {
        return this.funcionarioService.listarPorFaixaSalarial(valor1, valor2);
    }

    @GetMapping("/funcionario/{idFuncionario}")
    public Funcionario getFuncionario(@PathVariable Integer idFuncionario) {
        //@PathVariable => extrai do endpoint o valor dinâmico
        return this.funcionarioService.getFuncionario(idFuncionario);
    }

    @PostMapping("/funcionarios")
    public Funcionario salvar(@Valid @RequestBody FuncionarioDTO funcionario) {
        Funcionario salvo = this.funcionarioService.salvar(funcionario);
        return salvo;
    }
    @PutMapping("/funcionarios/{idFuncionario}")
    public Funcionario atualizar(@PathVariable Integer idFuncionario, @Valid @RequestBody FuncionarioDTO dto) {
        Funcionario atualizado = this.funcionarioService.atualizar(idFuncionario, dto);
        return atualizado;
    }

    @DeleteMapping("/funcionarios/{idFuncionario}")
    public void deletar(@PathVariable Integer idFuncionario){
    }
}
