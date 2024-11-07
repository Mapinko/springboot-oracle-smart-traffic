package br.com.fiap.smarttraffic.repository;

import br.com.fiap.smarttraffic.model.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
}
