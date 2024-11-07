package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
}
