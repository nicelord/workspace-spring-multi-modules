package dev.riza.workspace.web;


import dev.riza.workspace.domain.app.commands.CmdChangeName;
import dev.riza.workspace.domain.core.TestLibrary;
import dev.riza.workspace.domain.core.domain.organization.Organization;
import dev.riza.workspace.service.adapter.SpringOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.UUID;

@SpringBootApplication
@ComponentScan(basePackages = "dev.riza.workspace.*")
@EnableJpaRepositories(basePackages = "dev.riza.workspace.persistence")
@EntityScan(basePackages = "dev.riza.workspace.persistence")
public class WorkspaceSpringApplication {

    @Autowired
    SpringOrganizationService springOrganizationService;


    public static void main(String[] args) {
        SpringApplication.run(WorkspaceSpringApplication.class);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws InterruptedException {

        Organization organization = new Organization(UUID.randomUUID(),"TEST");
        springOrganizationService.changeName(new CmdChangeName(organization.getAggregateId(),"SAD"));

    }


}
