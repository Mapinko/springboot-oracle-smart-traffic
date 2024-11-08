package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.dto.IncidentDTO;
import br.com.fiap.smarttraffic.model.Incident;
import br.com.fiap.smarttraffic.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    // CREATE INCIDENT
    @PostMapping
    public ResponseEntity<IncidentDTO> createIncident(@RequestBody @Valid IncidentDTO incidentDTO) {
        Incident incident = mapToIncident(incidentDTO);
        Incident createdIncident = incidentService.createIncident(incident);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToIncidentDTO(createdIncident));
    }

    // GET INCIDENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<IncidentDTO> getIncidentById(@PathVariable Long id) {
        Optional<Incident> incident = incidentService.getIncidentById(id);
        return incident.map(this::mapToIncidentDTO).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET ALL INCIDENTS
    @GetMapping
    public ResponseEntity<List<IncidentDTO>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents.stream().map(this::mapToIncidentDTO).collect(Collectors.toList()));
    }

    // UPDATE INCIDENT
    @PutMapping("/{id}")
    public ResponseEntity<IncidentDTO> updateIncident(@PathVariable Long id, @RequestBody @Valid IncidentDTO updatedIncidentDTO) {
        Incident updatedIncident = mapToIncident(updatedIncidentDTO);
        updatedIncident = incidentService.updateIncident(id, updatedIncident);

        if (updatedIncident == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(mapToIncidentDTO(updatedIncident));
    }

    // DELETE INCIDENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }

    private Incident mapToIncident(IncidentDTO incidentDTO) {
        Incident incident = new Incident();
        incident.setDescription(incidentDTO.getDescription());

        incident.setStartTime(Timestamp.valueOf(incidentDTO.getOccurrenceTime())); // Assuming LocalDateTime in Incident
        incident.setEndTime(Timestamp.valueOf(incidentDTO.getOccurrenceTime().plusHours(1))); // Adjust as needed

        // ... other properties
        return incident;
    }
    private IncidentDTO mapToIncidentDTO(Incident incident) {
        IncidentDTO incidentDTO = new IncidentDTO();
        incidentDTO.setId(incident.getId());
        incidentDTO.setDescription(incident.getDescription());
        incidentDTO.setOccurrenceTime(incident.getStartTime().toLocalDateTime());
        incidentDTO.setVehicleLicensePlate(incident.getInvolvedVehicle().getLicensePlate());
        return incidentDTO;
    }
}