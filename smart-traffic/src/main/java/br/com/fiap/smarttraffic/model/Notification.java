package br.com.fiap.smarttraffic.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "tbl_notifications")
@Getter
@Setter
public class Notification {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "NOTIFICATIONS_SEQ"
    )
    @SequenceGenerator(
            name = "NOTIFICATIONS_SEQ",
            sequenceName = "NOTIFICATIONS_SEQ",
            allocationSize = 1
    )
    private Long id;

    @NotBlank
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification notification = (Notification) o;
        return Objects.equals(id, notification.id) && Objects.equals(message, notification.message) && Objects.equals(user, notification.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, user);
    }
}