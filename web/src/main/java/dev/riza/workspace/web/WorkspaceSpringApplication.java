package dev.riza.workspace.web;


import dev.riza.workspace.domain.app.commands.CmdChangeName;
import dev.riza.workspace.domain.app.commands.CmdInitOrganization;
import dev.riza.workspace.domain.core.model.organization.Organization;
import dev.riza.workspace.service.SpringOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

//        Organization organization = new Organization(UUID.randomUUID(),"TEST");
//        springOrganizationService.initialize(new CmdInitOrganization(organization));
//        springOrganizationService.changeName(new CmdChangeName(organization.getAggregateId(),"SAD"));
//        springOrganizationService.changeName(new CmdChangeName(organization.getAggregateId(),"ABC"));
//        springOrganizationService.changeName(new CmdChangeName(organization.getAggregateId(),"ASDASD"));
//        springOrganizationService.changeAddress(new CmdChangeAddress(organization.getAggregateId(),"alamat baru disni"));
//        System.out.println(springOrganizationService.load(organization.getAggregateId()).getBaseVersion());


    }


}

@RestController("/organization")
class Controller {
    @Autowired
    SpringOrganizationService springOrganizationService;


    @GetMapping("/organization/create")
    public String create(){
        Organization organization = new Organization(UUID.randomUUID(),"TEST");
        springOrganizationService.initialize(new CmdInitOrganization(organization));
        return organization.getAggregateId().toString();
    }

    @GetMapping("/organization/change/{uuid}/{newName}")
    public void change(@PathVariable UUID uuid, @PathVariable String newName){
        springOrganizationService.changeName(new CmdChangeName(uuid,newName));
    }


}
