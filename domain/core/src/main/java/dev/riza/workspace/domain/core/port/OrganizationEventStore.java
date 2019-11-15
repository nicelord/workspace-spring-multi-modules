package dev.riza.workspace.domain.core.port;


import dev.riza.workspace.domain.core.domain.DomainEvent;

import java.util.List;
import java.util.UUID;

/**
 * OUTPUT PORT TO EVENT STORE
 */

public interface OrganizationEventStore {
    List<DomainEvent> loadEvents(UUID organizationId);

    void storeEvents(List<DomainEvent> changes);
}
