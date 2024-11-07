package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    public Vehicle findByLicensePlate(String licensePlate);

    public List<Vehicle> findAllByType(String type);
}
