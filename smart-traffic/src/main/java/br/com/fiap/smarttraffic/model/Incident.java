package br.com.fiap.smarttraffic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tbl_incidents")
@Getter
@Setter
public class Incident {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "INCIDENTS_SEQ"
    )
    @SequenceGenerator(
            name = "INCIDENTS_SEQ",
            sequenceName = "INCIDENTS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotBlank
    private String description;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle involvedVehicle;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return Objects.equals(id, incident.id) && Objects.equals(description, incident.description) && Objects.equals(startTime, incident.startTime) && Objects.equals(endTime, incident.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, startTime, endTime);
    }

}