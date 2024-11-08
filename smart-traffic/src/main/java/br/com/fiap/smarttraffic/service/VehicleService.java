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

    public Vehicle updateVehicle(Long id, Vehicle updatedVehicleData) {
        Optional<Vehicle> existingVehicle = vehicleRepository.findById(id);

        if (existingVehicle.isPresent()) {
            Vehicle vehicleToUpdate = existingVehicle.get();
            vehicleToUpdate.setLicensePlate(updatedVehicleData.getLicensePlate());  // Update license plate
            vehicleToUpdate.setType(updatedVehicleData.getType());              // Update type

            return vehicleRepository.save(vehicleToUpdate);
        } else {
            throw new RuntimeException("Vehicle with id " + id + " not found");
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
