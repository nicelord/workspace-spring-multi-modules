package dev.riza.workspace.domain.core.port;


import dev.riza.workspace.domain.core.domain.DomainEvent;
import dev.riza.workspace.domain.core.domain.organization.Organization;

import java.util.List;
import java.util.UUID;

/**
 * INPUT PORT TO DOMAIN BUSINESS
 */

public interface OrganizationService {

    void initialize(Organization organization);

    void changeName(Organization organization, String newName);

    void changeAddress(Organization organization, String newAddress);

    void storeEvents(List<DomainEvent> changes);

    Organization recreateFromPastEvents(UUID organizationId) ;
}
