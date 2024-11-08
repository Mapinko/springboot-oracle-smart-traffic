package br.com.fiap.smarttraffic.service;

import br.com.fiap.smarttraffic.model.Sensor;
import br.com.fiap.smarttraffic.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensorById(Long id) {
        return sensorRepository.findById(id);
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

}