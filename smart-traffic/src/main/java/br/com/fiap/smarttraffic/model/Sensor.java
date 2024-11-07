package br.com.fiap.smarttraffic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "table_sensors")
@Getter
@Setter
public class Sensor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SENSORS_SEQ"
    )
    @SequenceGenerator(
            name = "SENSORS_SEQ",
            sequenceName = "SENSORS_SEQ",

            allocationSize = 1
    )
    private Long id;

    @NotBlank
    private String location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;
        return Objects.equals(id, sensor.id) && Objects.equals(location,
                sensor.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location);
    }
}
