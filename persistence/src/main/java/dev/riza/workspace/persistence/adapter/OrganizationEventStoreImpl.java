package dev.riza.workspace.persistence.adapter;


import dev.riza.workspace.domain.core.model.DomainEvent;
import dev.riza.workspace.domain.core.port.OrganizationEventStore;
import dev.riza.workspace.persistence.OrganizationEventStoreJPA;
import dev.riza.workspace.persistence.helper.DomainEventSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrganizationEventStoreImpl implements OrganizationEventStore {

    private final OrganizationEventStoreJPA organizationEventStoreJPA;
    private final DomainEventSerializer domainEventSerializer;

    @Autowired
    public OrganizationEventStoreImpl(OrganizationEventStoreJPA organizationEventStoreJPA, DomainEventSerializer domainEventSerializer) {
        this.organizationEventStoreJPA = organizationEventStoreJPA;
        this.domainEventSerializer = domainEventSerializer;
    }

    @Override
    public List<DomainEvent> loadEvents(UUID organizationId) {
        return organizationEventStoreJPA.findByUuid(organizationId)
                .stream()
                .map(domainEventSerializer::deserialize)
                .collect(Collectors.toList());
    }

    @Override
    public void storeEvents(List<DomainEvent> changes) {
        changes.stream().map(domainEventSerializer::serialize).forEach(organizationEventStoreJPA::save);
    }
}
