package dev.riza.workspace.service.adapter;


import dev.riza.workspace.domain.app.commands.CmdChangeAddress;
import dev.riza.workspace.domain.app.commands.CmdChangeName;
import dev.riza.workspace.domain.app.commands.CmdInitOrganization;
import dev.riza.workspace.domain.app.port.OrganizationOperation;
import dev.riza.workspace.domain.core.domain.organization.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SpringOrganizationService {

    @Autowired
    OrganizationStream organizationStream;

    private final OrganizationOperation organizationOperation;

    public SpringOrganizationService(OrganizationOperation organizationOperation) {
        this.organizationOperation = organizationOperation;
    }

    public void initialize(CmdInitOrganization cmdInitOrganization) {
        organizationOperation.initialize(cmdInitOrganization);
    }

    public void changeName(CmdChangeName cmdChangeName) {
        organizationOperation.changeName(cmdChangeName);
    }

    public void changeAddress(CmdChangeAddress cmdChangeAddress) {
        organizationOperation.changeAddress(cmdChangeAddress);
    }

    public Organization load(UUID organizationId) {
        return organizationOperation.load(organizationId);
    }


    
    @StreamListener(OrganizationStream.INPUT)
    public void process(String request) {
        System.out.println(request);
    }
}
