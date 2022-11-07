package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

    //Listar todos
    public List<Cargo> listar() {
        //Retorna os dados da tbela em forma de lista
        // SELECT * FROM cargo;
        return this.cargoRepository.findAll();
    }
    //Listar um pelo ID
    public Cargo getCargo(Integer idCargo) {
        //SELECT * FROM cargo WHERE idCargo = ?
        //Optional = Pode haver cargo ou não
        Optional<Cargo> cargo = this.cargoRepository.findById(idCargo);

        if(cargo.isEmpty()) { // Não encontrou o cargo?
            //Não encontrou o cargo id solicitado
            throw new RuntimeException("O cargo não foi encontrado!"); //Causa um erro com a mensagem
        } else {
            return cargo.get(); // Extrai o cargo dentro do optional
        }
    }
    //Salvar
    public Cargo salvar(@Valid CargoDTO dto) {
      //  novoCargo.setIdCargo(null); // Limpar o campo id para não substituir
        //INSERT INTO cargo
        Cargo cargo = new Cargo(null, dto.getNome(), dto.getDescricao(), dto.getSalario());
        Cargo cargoSalvo = this.cargoRepository.save(cargo);
        return cargoSalvo;
    }

    ///Atualizar
    //Precisa do ID do cargo e dos dados atualizados
    public Cargo atualizar(Integer idCargo, CargoDTO dto) {
        // verificar se o cargo existe mesmo
        Cargo cargoAtual = this.getCargo(idCargo);

        cargoAtual.setNome(dto.getNome());
        cargoAtual.setDescricao(dto.getDescricao());
        cargoAtual.setSalario(dto.getSalario());

        //Atualiza a entidsde pois ela possui um ID diferente de nulo
        Cargo atualizado = this.cargoRepository.save(cargoAtual);
        return atualizado;
    }

    //Deletar
    public void deletar(Integer idCargo) {
        Cargo cargo = this.getCargo(idCargo);
        //DELETE FROM cargo WHERE idCargo = ?
        this.cargoRepository.delete(cargo);
    }
}
