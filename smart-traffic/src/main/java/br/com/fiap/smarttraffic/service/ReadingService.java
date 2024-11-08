package br.com.fiap.smarttraffic.service;

import br.com.fiap.smarttraffic.model.Reading;
import br.com.fiap.smarttraffic.model.Sensor;
import br.com.fiap.smarttraffic.model.Vehicle;
import br.com.fiap.smarttraffic.repository.ReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadingService {

    @Autowired
    private ReadingRepository readingRepository;

    public Reading createReading(Reading reading) {
        return readingRepository.save(reading);
    }

    public Optional<Reading> getReadingById(Long id) {
        return readingRepository.findById(id);
    }

    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }

    public List<Reading> getReadingsBySensor(Optional<Sensor> sensor) {
        return readingRepository.findBySensor(sensor);
    }

    public List<Reading> getReadingsByVehicle(Vehicle vehicle) {
        return readingRepository.findByVehicle(vehicle);
    }

}