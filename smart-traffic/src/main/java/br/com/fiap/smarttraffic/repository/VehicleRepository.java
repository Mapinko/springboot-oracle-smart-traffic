package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    public Vehicle findByLicensePlate(String licensePlate);

    public List<Vehicle> findAllByType(String type);
}
