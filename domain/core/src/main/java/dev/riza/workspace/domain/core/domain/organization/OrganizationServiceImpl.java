package dev.riza.workspace.domain.core.domain.organization;


import dev.riza.workspace.domain.core.domain.DomainEvent;
import dev.riza.workspace.domain.core.port.OrganizationEventBus;
import dev.riza.workspace.domain.core.port.OrganizationEventStore;
import dev.riza.workspace.domain.core.port.OrganizationService;

import java.util.List;
import java.util.UUID;

public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationEventStore organizationEventStore;
    private final OrganizationEventBus organizationEventBus;

    public OrganizationServiceImpl(OrganizationEventStore organizationEventStore, OrganizationEventBus organizationEventBus) {
        this.organizationEventStore = organizationEventStore;
        this.organizationEventBus = organizationEventBus;
    }

    @Override
    public void initialize(Organization organization) {
        storeEvents(organization.getChanges());
    }

    @Override
    public void changeName(Organization organization, String newName) {
        organization.changeName(newName);
        storeEvents(organization.getChanges());
    }

    @Override
    public void changeAddress(Organization organization, String newAddress) {
        organization.changeAddress(newAddress);
        storeEvents(organization.getChanges());
    }

    @Override
    public void storeEvents(List<DomainEvent> changes) {
        organizationEventStore.storeEvents(changes);
        organizationEventBus.publish(changes);
    }

    @Override
    public Organization recreateFromPastEvents(UUID organizationId) {
        List<DomainEvent> domainEvents = organizationEventStore.loadEvents(organizationId);
        return new Organization(organizationId, domainEvents);
    }
}
