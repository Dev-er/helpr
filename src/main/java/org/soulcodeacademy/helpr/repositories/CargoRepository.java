package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//public interface CargoRepository extends CrudRepository<Cargo, Integer>
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
    List<Cargo> findByNome(String valor); // WHERE nome = valor

    List<Cargo> findBySalario(Double valor); // WHERE salario = valor

    List<Cargo> findBySalarioGreaterThan(Double valor); // WHERE salario > valor

    List<Cargo> findBySalarioGreaterThanEqual(Double valor); // WHERE salario >= valor

    List<Cargo> findBySalarioLessThan(Double valor); //WHERE salario < valor

    List<Cargo> findBySalarioLessThanEqual(Double valor); // Where salario <= valor

    List<Cargo> findBySalarioBetween(Double valor1, Double valor2); //WHERE salario BETWEEN valor

}
//Repository = é um recurso que permite manipular a entidade no banco de dados
// Adicionar entidades, atualizar entidades, remover entidades e listar(CRUD)
//Indicar qual a entidade vai ser gerenciada pelo repository e qual o tipo de PK.
//Obs: O Spring gra uma classe dinâmica com base em nossa interface CargoRepository.
//CrudRepository = contém as operações básicas de um banco de dados
//JpaRepository = possui mais funcionalidades para tratar entidade