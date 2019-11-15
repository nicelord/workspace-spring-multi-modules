package dev.riza.workspace.domain.core.domain.organization;




import dev.riza.workspace.domain.core.domain.DomainEvent;
import dev.riza.workspace.domain.core.port.OrganizationEventStore;
import dev.riza.workspace.domain.core.port.OrganizationService;

import java.util.List;
import java.util.UUID;

public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationEventStore organizationEventStore;

    public OrganizationServiceImpl(OrganizationEventStore organizationEventStore) {
        this.organizationEventStore = organizationEventStore;
    }

    @Override
    public void changeName(Organization organization, String newName) {
        organization.changeName(newName);
        storeEvents(organization.getChanges());
    }

    @Override
    public void storeEvents(List<DomainEvent> changes) {
        organizationEventStore.storeEvents(changes);
    }

    @Override
    public Organization recreateFromPastEvents(UUID organizationId) {
        List<DomainEvent> domainEvents = organizationEventStore.loadEvents(organizationId);
        return new Organization(organizationId, domainEvents);
    }
}
