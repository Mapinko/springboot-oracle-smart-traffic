package br.com.fiap.smarttraffic.service;


import br.com.fiap.smarttraffic.model.Vehicle;
import br.com.fiap.smarttraffic.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // CREATE VEHICLE
    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getLicensePlate() == null || vehicle.getLicensePlate().isEmpty()) {
            throw new IllegalArgumentException("License plate cannot be empty");
        }
        return vehicleRepository.save(vehicle);
    }



    // FIND VEHICLE BY ID
    public Vehicle findVehicleById(Long id) {
        Optional<Vehicle> OptionaVehicle = vehicleRepository.findById(id);

        if (OptionaVehicle.isPresent()) {
            return OptionaVehicle.get();
        } else {
            throw new RuntimeException("Vehicle not found!");
        }
    }

    //FIND ALL VEHICLES BY TYPE
    public List<Vehicle> findAllVehiclesByType(String type) {
        return vehicleRepository.findAllByType(type);
    }

    // FIND VEHICLE BY LICENSE PLATE
    public Vehicle findVehicleByLicensePlate(String licensePlate) {
        return vehicleRepository.findByLicensePlate(licensePlate);
    }

    //LIST ALL VEHICLES
    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    //UPDATE VEHICLE
    public Vehicle updateVehicle(Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicle.getId());

        if (vehicleOptional.isPresent()) {
            return vehicleRepository.save(vehicle);
        } else {
            throw new RuntimeException("Vehicle not found!");
        }
    }

    //DELETE VEHICLE
    public void deleteVehicle(Long id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

        if (vehicleOptional.isPresent()) {
            vehicleRepository.delete(vehicleOptional.get());
        } else {
            throw new RuntimeException("Vehicle not found!");
        }
    }


}
