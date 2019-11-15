package dev.riza.workspace.domain.app.port;


import dev.riza.workspace.domain.app.commands.CmdChangeName;
import dev.riza.workspace.domain.core.domain.organization.Organization;

import java.util.UUID;

/**
 * INPUT PORT
 */
public interface OrganizationOperation {
    void changeName(CmdChangeName cmdChangeName);
    Organization load(UUID aggregateId);
}
