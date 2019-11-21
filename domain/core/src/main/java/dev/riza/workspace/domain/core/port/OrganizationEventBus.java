package dev.riza.workspace.domain.core.port;

import dev.riza.workspace.domain.core.model.DomainEvent;

import java.util.List;

public interface OrganizationEventBus {
    void publish(List<DomainEvent> events);
}
