package br.com.fiap.smarttraffic.service;

import br.com.fiap.smarttraffic.model.TrafficLight;
import br.com.fiap.smarttraffic.model.TrafficLightStatus;
import br.com.fiap.smarttraffic.repository.TrafficLightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrafficLightService {

    @Autowired
    private TrafficLightRepository trafficLightRepository;

    public TrafficLight createTrafficLight(TrafficLight trafficLight) {
        return trafficLightRepository.save(trafficLight);
    }

    public Optional<TrafficLight> getTrafficLightById(Long id) {
        return trafficLightRepository.findById(id);
    }

    public List<TrafficLight> getAllTrafficLights() {
        return trafficLightRepository.findAll();
    }

    public List<TrafficLight> getTrafficLightsByLocation(String location) {
        return trafficLightRepository.findByLocation(location);
    }

    public List<TrafficLight> getTrafficLightsByStatus(TrafficLightStatus status) {
        return trafficLightRepository.findByStatus(status);
    }
}