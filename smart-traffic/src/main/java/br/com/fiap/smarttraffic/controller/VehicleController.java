package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.model.Vehicle;
import br.com.fiap.smarttraffic.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    private VehicleService service;

    // CREATE VEHICLE
    @PostMapping("/vehicles")
    public ResponseEntity<Vehicle> createVehicles(@RequestBody Vehicle vehicle) {
        Vehicle createdVehicle = service.createVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    // FIND VEHICLE BY ID
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> findVehicleById(@PathVariable Long id) {
        Vehicle vehicle = service.findVehicleById(id);
        return ResponseEntity.ok(vehicle);
    }

    //FIND ALL VEHICLES BY TYPE (with pagination)
    @GetMapping("/vehicles/type/{type}")
    public ResponseEntity<List<Vehicle>> findVehiclesByType(@PathVariable String type) {

        List<Vehicle> vehicles = service.findAllVehiclesByType(type);
        return ResponseEntity.ok(vehicles);
    }

    // FIND VEHICLE BY LICENSE PLATE
    @GetMapping("/vehicles/licensePlate/{licensePlate}")
    public ResponseEntity<Vehicle> findVehicleByLicensePlate(@PathVariable String licensePlate) {
        Vehicle vehicle = service.findVehicleByLicensePlate(licensePlate);
        return ResponseEntity.ok(vehicle);
    }

    //LIST ALL VEHICLES
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> findAllVehicles() {
        List<Vehicle> vehicles = service.findAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    // UPDATE VEHICLE BY ID
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle updatedVehicleData) {
        Vehicle updatedVehicle = service.updateVehicle(id, updatedVehicleData);
        return ResponseEntity.ok(updatedVehicle);
    }

    // DELETE VEHICLE
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

}