package dev.riza.workspace.service.adapter;


import dev.riza.workspace.domain.app.commands.CmdChangeName;
import dev.riza.workspace.domain.app.port.OrganizationOperation;
import dev.riza.workspace.domain.core.domain.organization.Organization;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpringOrganizationService {

    private final OrganizationOperation organizationOperation;

    public SpringOrganizationService(OrganizationOperation organizationOperation) {
        this.organizationOperation = organizationOperation;
    }

    public void changeName(CmdChangeName cmdChangeName) {
        organizationOperation.changeName(cmdChangeName);
    }

    public Organization load(UUID organizationId) {
        return organizationOperation.load(organizationId);
    }
}
