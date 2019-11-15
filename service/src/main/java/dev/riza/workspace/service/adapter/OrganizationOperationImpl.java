package dev.riza.workspace.service.adapter;


import dev.riza.workspace.domain.app.commands.CmdChangeName;
import dev.riza.workspace.domain.app.port.OrganizationOperation;
import dev.riza.workspace.domain.core.domain.organization.Organization;
import dev.riza.workspace.domain.core.port.OrganizationService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrganizationOperationImpl implements OrganizationOperation {

    private final OrganizationService organizationService;

    public OrganizationOperationImpl(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    public void changeName(CmdChangeName cmdChangeName) {
        organizationService.changeName(load(cmdChangeName.getAggregateId()), cmdChangeName.getNewName());
    }

    @Override
    public Organization load(UUID aggregateId) {
        return organizationService.recreateFromPastEvents(aggregateId);
    }
}