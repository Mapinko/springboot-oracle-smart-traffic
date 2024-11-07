package br.com.fiap.smarttraffic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tbl_routes")
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ROUTES_SEQ"
    )
    @SequenceGenerator(
            name = "ROUTES_SEQ",
            sequenceName = "ROUTES_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    private double estimatedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(id, route.id) && Objects.equals(origin, route.origin) && Objects.equals(destination, route.destination) && Double.compare(route.estimatedTime, estimatedTime) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, destination, estimatedTime);
    }
}