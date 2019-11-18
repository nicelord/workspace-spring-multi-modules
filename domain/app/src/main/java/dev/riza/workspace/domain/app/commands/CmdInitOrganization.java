package dev.riza.workspace.domain.app.commands;

import dev.riza.workspace.domain.core.domain.organization.Organization;
import lombok.Getter;

public class CmdInitOrganization {

    @Getter
    private final Organization organization;

    public CmdInitOrganization(Organization organization) {
        this.organization = organization;
    }
}
