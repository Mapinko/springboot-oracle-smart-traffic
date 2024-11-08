package br.com.fiap.smarttraffic.service;

import br.com.fiap.smarttraffic.model.Incident;
import br.com.fiap.smarttraffic.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    // CREATE INCIDENT
    public Incident createIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    // GET INCIDENT BY ID
    public Optional<Incident> getIncidentById(Long id) {
        return incidentRepository.findById(id);
    }

    // GET ALL INCIDENTS
    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    // UPDATE INCIDENT
    public Incident updateIncident(Long id, Incident updatedIncidentData) {
        Optional<Incident> existingIncident = incidentRepository.findById(id);

        if (existingIncident.isPresent()) {
            Incident incidentToUpdate = existingIncident.get();
            // Update specific fields based on updatedIncidentData
            incidentToUpdate.setDescription(updatedIncidentData.getDescription() != null ? updatedIncidentData.getDescription() : incidentToUpdate.getDescription());
            incidentToUpdate.setStartTime(updatedIncidentData.getStartTime() != null ? updatedIncidentData.getStartTime() : incidentToUpdate.getStartTime());
            incidentToUpdate.setEndTime(updatedIncidentData.getEndTime() != null ? updatedIncidentData.getEndTime() : incidentToUpdate.getEndTime());
            return incidentRepository.save(incidentToUpdate);
        } else {
            throw new RuntimeException("Incident with id " + id + " not found");
        }
    }

    // DELETE INCIDENT
    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }
}