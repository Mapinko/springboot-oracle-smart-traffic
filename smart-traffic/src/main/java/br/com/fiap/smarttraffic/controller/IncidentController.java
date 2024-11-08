package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.model.Incident;
import br.com.fiap.smarttraffic.service.IncidentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    // CREATE INCIDENT
    @PostMapping
    public ResponseEntity<Incident> createIncident(@RequestBody @Valid Incident incident) {
        Incident createdIncident = incidentService.createIncident(incident);
        return ResponseEntity.ok(createdIncident);
    }

    // GET INCIDENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable Long id) {
        Optional<Incident> incident = incidentService.getIncidentById(id);
        return incident.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET ALL INCIDENTS
    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        List<Incident> incidents = incidentService.getAllIncidents();
        return ResponseEntity.ok(incidents);
    }

    // UPDATE INCIDENT
    @PutMapping("/{id}")
    public ResponseEntity<Incident> updateIncident(@PathVariable Long id, @RequestBody Incident updatedIncidentData) {
        Incident updatedIncident = incidentService.updateIncident(id, updatedIncidentData);

        if (updatedIncident == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedIncident);
    }


    // DELETE INCIDENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }
}