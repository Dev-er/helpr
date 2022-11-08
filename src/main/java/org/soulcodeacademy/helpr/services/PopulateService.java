package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Torna o objeto de PopulateService disponível para toda a aplicação(global)
@Service // Indica para o Spring que esta classe será gerenciada por ele
public class PopulateService {
    @Autowired // Injetar o objeto direto na classe
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public void populate() {
        Cargo c1 = new Cargo(null, "Diretor Geral", "Gerencia a Empresa",30000.0 );
        Cargo c2 = new Cargo(null, "Diretor de Setor", "Gerencia um Setor da empresa", 18000.0);
        Cargo c3 = new Cargo(null, "Técnico Geral", "Resolve os chamados urgentes", 12000.0);
        // vamos persistir as entidades = salvar no banco

        Funcionario f1 = new Funcionario(null, "Renato Pereira", "renatopereira@gmail.com", "68258098144", "12345", null, c1);
        Funcionario f2 = new Funcionario(null, "Victor Icoma", "victoricoma@gmail.com", "51127383671", "12345", null, c2);

        this.cargoRepository.save(c1); // INSERT INTO
        this.cargoRepository.save(c2);
        this.cargoRepository.save(c3);

        this.funcionarioRepository.save(f1);
        this.funcionarioRepository.save(f2);
    }
}

// O objetivo desta classe é inserir no banco, dados fictícios (de teste)
// IOC = Inversion of Control = Inteversão de Controle = É ele quem manda nas instâncias
// Container = é o local onde o Spring guarda os objetos anotados
//@Service = indica que a classe é um serviço
//Injeção de Dependências = quando o Spring injeta os objetos na classe
