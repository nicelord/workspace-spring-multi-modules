package dev.riza.workspace.domain.app.port;


import dev.riza.workspace.domain.app.commands.CmdChangeAddress;
import dev.riza.workspace.domain.app.commands.CmdChangeName;
import dev.riza.workspace.domain.app.commands.CmdInitOrganization;
import dev.riza.workspace.domain.core.model.organization.Organization;

import java.util.UUID;

/**
 * INPUT PORT
 */
public interface OrganizationOperation {
    void initialize(CmdInitOrganization cmdInitOrganization);

    void changeName(CmdChangeName cmdChangeName);

    void changeAddress(CmdChangeAddress cmdChangeAddress);

    Organization load(UUID aggregateId);
}
