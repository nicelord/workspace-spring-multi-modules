package dev.riza.workspace.service.adapter;


import dev.riza.workspace.domain.core.domain.organization.OrganizationServiceImpl;
import dev.riza.workspace.domain.core.port.OrganizationEventBus;
import dev.riza.workspace.persistence.adapter.OrganizationEventStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@org.springframework.context.annotation.Configuration
@EnableBinding(OrganizationStream.class)
@Component
public class Configuration {

    @Autowired
    OrganizationEventStoreImpl organizationEventStore;

    @Autowired
    OrganizationEventBus organizationEventBus;


    @Bean
    public OrganizationServiceImpl organizationService() {
        return new OrganizationServiceImpl(organizationEventStore, organizationEventBus);
    }
}
