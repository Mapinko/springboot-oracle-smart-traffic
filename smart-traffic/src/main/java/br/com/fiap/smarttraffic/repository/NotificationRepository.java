package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
