package ar.com.codigomariano.labHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codigomariano.labHelper.domain.Muestra;

@Repository
public interface MuestraRepository extends JpaRepository<Muestra, Long> {

}
