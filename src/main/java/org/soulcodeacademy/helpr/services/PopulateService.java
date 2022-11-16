package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Torna o objeto de PopulateService disponível para toda a aplicação(global)
@Service // Indica para o Spring que esta classe será gerenciada por ele
public class PopulateService {
    @Autowired // Injetar o objeto direto na classe
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void populate() {


        Cargo c1 = new Cargo(null, "Diretor Geral", "Gerencia a Empresa",30000.0 );
        Cargo c2 = new Cargo(null, "Diretor de Setor", "Gerencia um Setor da empresa", 18000.0);
        Cargo c3 = new Cargo(null, "Técnico Geral", "Resolve os chamados urgentes", 12000.0);

        //vamos persistir as entidades = salvar no banco

        Funcionario f1 = new Funcionario(null, "Renato Pereira", "renatopereira@gmail.com", "68258098144", "12345", null, c1);
        Funcionario f2 = new Funcionario(null, "Victor Icoma", "victoricoma@gmail.com", "51127383671", "12345", null, c2);

        Cliente cl1 = new Cliente(null, "Jose", "joe@gmail.com","12345678912", "12345","1196252627", null);
        Cliente cl2 = new Cliente(null, "João", "joao@gmail.com","12365478912", "12345","1296252627", null);


        Chamado ch1 = new Chamado(null, "Primeiro chamado do sistema", "Revisar as entidades criadas");
        ch1.setCliente(cl1);

        Chamado ch2 = new Chamado(null, "Ativar VPN do sistema", "Conectar aos servidores remotos");
        ch2.setCliente(cl2);
        ch2.setFuncionario(f1);
        ch2.setStatus(StatusChamado.ATRIBUIDO);

        this.cargoRepository.saveAll(List.of(c1, c2, c3));
        this.funcionarioRepository.saveAll(List.of(f1, f2));
        this.clienteRepository.saveAll(List.of(cl1, cl2));
        this.chamadoRepository.saveAll(List.of(ch1, ch2));

       //this.cargoRepository.save(c1); // INSERT INTO
        //this.cargoRepository.save(c2);
        //this.cargoRepository.save(c3);

        //this.funcionarioRepository.save(f1);
        //this.funcionarioRepository.save(f2);

        //this.clienteRepository.save(cl1);
        //this.clienteRepository.save(cl2);
    }
}

// O objetivo desta classe é inserir no banco, dados fictícios (de teste)
// IOC = Inversion of Control = Inteversão de Controle = É ele quem manda nas instâncias
// Container = é o local onde o Spring guarda os objetos anotados
//@Service = indica que a classe é um serviço
//Injeção de Dependências = quando o Spring injeta os objetos na classe
