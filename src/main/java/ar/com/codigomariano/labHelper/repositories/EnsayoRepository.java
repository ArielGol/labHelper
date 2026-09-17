package ar.com.codigomariano.labHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codigomariano.labHelper.domain.Ensayo;

@Repository
public interface EnsayoRepository extends JpaRepository<Ensayo, Long> {

}
