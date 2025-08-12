package org.wikikafka.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wikikafka.consumer.entities.WikimediaData;

public interface WikimediaDataRepository extends JpaRepository<WikimediaData, Long> {
}
