package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
