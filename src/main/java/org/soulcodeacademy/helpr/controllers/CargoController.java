package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //retornos de dados
public class CargoController {
    //Endpoit é o endereço que será acessado no backend

    @GetMapping("/oi")//com base em localhost:8080/oi retorna a String
    public String dizOla() {
        return "Olá, tudo bem?";
    }

    @Autowired
    private CargoService cargoService;

    @GetMapping("/cargos")
    public List<Cargo> listar() {
        //Requisição -> Controller -> Service -> Repository - SELECT * FROM cargo;
        return this.cargoService.listar(); //JSON = JAVASCRIPT OBJECT NOTATION
    }

    @GetMapping("/cargos/{idCargo}")
    public Cargo getCargo(@PathVariable Integer idCargo) {
        //@PathVariable => extrai do endpoint o valor dinâmico
        return this.cargoService.getCargo(idCargo);
    }

    @PostMapping("/cargos")
    public Cargo salvar(@Valid @RequestBody CargoDTO cargo) {
        Cargo salvo = this.cargoService.salvar(cargo);
        return salvo;
    }

    //Mapeia requisições do verbo PUT
    @PutMapping("/cargos/{idCargo}") //Cargos/5
    public Cargo atualizar(@PathVariable Integer idCargo, @RequestBody CargoDTO cargo) {
        Cargo atualizado =  this.cargoService.atualizar(idCargo, cargo);
        return atualizado; // resposta para o cliente (cargo atualizado)
    }

    @DeleteMapping("/cargos/{idCargo}") // Verbo DELETE no /cargo/1
    public void deletar(@PathVariable Integer idCargo) {
        this.cargoService.deletar(idCargo);
    }
}
