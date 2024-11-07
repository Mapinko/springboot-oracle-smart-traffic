package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.TrafficLight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrafficLightRepository extends JpaRepository<TrafficLight, Long> {
}
