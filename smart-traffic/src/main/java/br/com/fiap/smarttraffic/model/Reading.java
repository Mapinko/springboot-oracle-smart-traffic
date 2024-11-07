package br.com.fiap.smarttraffic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tbl_readings")
@Getter
@Setter
public class Reading {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "READINGS_SEQ"
    )
    @SequenceGenerator(
            name = "READINGS_SEQ",
            sequenceName = "READINGS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "sensor_id")
    private Sensor sensor;

    private double speed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reading reading = (Reading) o;
        return Objects.equals(id, reading.id) && Objects.equals(timestamp, reading.timestamp) && Objects.equals(vehicle, reading.vehicle) && Objects.equals(sensor, reading.sensor) && Double.compare(speed, reading.speed) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, vehicle, sensor, speed);
    }
}