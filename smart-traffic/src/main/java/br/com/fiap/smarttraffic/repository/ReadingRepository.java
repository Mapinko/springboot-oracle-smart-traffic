package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.Reading;
import br.com.fiap.smarttraffic.model.Sensor;
import br.com.fiap.smarttraffic.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findByVehicle(Vehicle vehicle);

    List<Reading> findBySensor(Sensor sensor);
}
