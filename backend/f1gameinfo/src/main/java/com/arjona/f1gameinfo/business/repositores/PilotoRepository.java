package com.arjona.f1gameinfo.business.repositores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arjona.f1gameinfo.business.model.Piloto;

/**
 * Repositorio que maneja todo lo
 * relacionado con los pilotos
 */
@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Long>{

}
