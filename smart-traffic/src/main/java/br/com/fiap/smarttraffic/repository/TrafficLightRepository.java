package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.TrafficLight;
import br.com.fiap.smarttraffic.model.TrafficLightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrafficLightRepository extends JpaRepository<TrafficLight, Long> {
    List<TrafficLight> findByStatus(TrafficLightStatus status);

    List<TrafficLight> findByLocation(String location);
}