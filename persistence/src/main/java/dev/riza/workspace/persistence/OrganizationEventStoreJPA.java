package dev.riza.workspace.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrganizationEventStoreJPA extends JpaRepository<DomainEventEntity, Long> {
    List<DomainEventEntity> findByUuid(UUID uuid);

}
