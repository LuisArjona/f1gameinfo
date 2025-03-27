package com.arjona.f1gameinfo.business.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjona.f1gameinfo.business.model.Circuito;

/**
 * Repositorio que maneja todo lo
 * relacionado con los circuitos
 */
@Repository
public interface CircuitoRepository extends JpaRepository<Circuito, Long>{

}
