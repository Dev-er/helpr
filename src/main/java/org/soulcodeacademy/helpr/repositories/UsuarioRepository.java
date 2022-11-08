package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}

//Métodos presentes
// - findAll => SELECT * FROM usuario;
// - findById => SELECT * FROM usuario WHERE id = ?
// - save = INSERT INTO(caso id nulo) ou UPDATE (caso id não nulo)
// - delete(Usuario) => DELETE FROM usuarios WHERE id =?

//CRUD de Funcionários
// Entidade -> Repository -> DTO -> Service + Controller