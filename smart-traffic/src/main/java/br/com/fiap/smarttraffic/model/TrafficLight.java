package br.com.fiap.smarttraffic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tbl_traffic_lights")
@Getter
@Setter
public class TrafficLight {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "TRAFFIC_LIGHTS_SEQ"
    )
    @SequenceGenerator(
            name = "TRAFFIC_LIGHTS_SEQ",
            sequenceName = "TRAFFIC_LIGHTS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotBlank
    private String location;

    private String state; // Possible values: "red", "yellow", "green"

    private int cycleTime; // In seconds

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficLight trafficLight = (TrafficLight) o;
        return Objects.equals(id, trafficLight.id) && Objects.equals(location, trafficLight.location) && Objects.equals(state, trafficLight.state) && Objects.equals(cycleTime, trafficLight.cycleTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, state, cycleTime);
    }
}