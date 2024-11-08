package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.dto.VehicleDTO;
import br.com.fiap.smarttraffic.model.Vehicle;
import br.com.fiap.smarttraffic.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // CREATE VEHICLE
    @PostMapping("/vehicles")
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(vehicleDTO.getType());
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());

        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);

        return ResponseEntity.status(HttpStatus.CREATED).body(mapToVehicleDTO(createdVehicle));
    }

    // GET VEHICLE BY ID
    @GetMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapToVehicleDTO(vehicle));
    }

    // FIND ALL VEHICLES BY TYPE (with pagination)
    @GetMapping("/vehicles/type/{type}")
    public ResponseEntity<List<VehicleDTO>> findVehiclesByType(@PathVariable String type) {
        List<Vehicle> vehicles = vehicleService.findAllVehiclesByType(type);
        return ResponseEntity.ok(mapVehiclesToDTOs(vehicles));
    }

    // FIND VEHICLE BY LICENSE PLATE
    @GetMapping("/vehicles/licensePlate/{licensePlate}")
    public ResponseEntity<VehicleDTO> findVehicleByLicensePlate(@PathVariable String licensePlate) {
        Vehicle vehicle = vehicleService.findVehicleByLicensePlate(licensePlate);
        return ResponseEntity.ok(mapToVehicleDTO(vehicle));
    }

    // LIST ALL VEHICLES
    @GetMapping("/vehicles")
    public ResponseEntity<List<VehicleDTO>> findAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findAllVehicles();
        return ResponseEntity.ok(mapVehiclesToDTOs(vehicles));
    }

    // UPDATE VEHICLE BY ID
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable Long id, @RequestBody VehicleDTO updatedVehicleDTO) {
        Vehicle updatedVehicle = vehicleService.updateVehicle(id, updatedVehicleDTO);
        return ResponseEntity.ok(mapToVehicleDTO(updatedVehicle));
    }

    // DELETE VEHICLE
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

    private VehicleDTO mapToVehicleDTO(Vehicle vehicle) {
        VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setType(vehicle.getType());
        vehicleDTO.setLicensePlate(vehicle.getLicensePlate());
        return vehicleDTO;
    }

    private List<VehicleDTO> mapVehiclesToDTOs(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(this::mapToVehicleDTO)
                .collect(Collectors.toList());
    }
}