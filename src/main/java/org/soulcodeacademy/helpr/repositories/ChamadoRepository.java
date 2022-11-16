package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
