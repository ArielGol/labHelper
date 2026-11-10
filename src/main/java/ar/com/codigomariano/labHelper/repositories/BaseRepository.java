package ar.com.codigomariano.labHelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.codigomariano.labHelper.domain.Persistible;

public interface BaseRepository<T extends Persistible> extends JpaRepository<T, Long> {

}
