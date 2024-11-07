package br.com.fiap.smarttraffic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Entity
@Table(name = "tbl_vahicle")
@Getter
@Setter
public class Vehicle {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "VEHICLES_SEQ"
    )
    @SequenceGenerator(
            name = "VEHICLES_SEQ",
            sequenceName = "VEHICLES_SEQ",
            allocationSize = 50
    )
    private Long id;

    @NotBlank
    @Column(name = "license_plate")
    private String licensePlate;
    private String type;

    @NotBlank
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) && Objects.equals(licensePlate, vehicle.licensePlate) && Objects.equals(type, vehicle.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, licensePlate, type);
    }
}
