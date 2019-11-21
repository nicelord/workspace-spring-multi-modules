package dev.riza.workspace.configuration;


import dev.riza.workspace.domain.core.model.organization.OrganizationServiceImpl;
import dev.riza.workspace.domain.core.port.OrganizationEventBus;
import dev.riza.workspace.domain.core.port.OrganizationService;
import dev.riza.workspace.persistence.adapter.OrganizationEventStoreImpl;
import dev.riza.workspace.projection.OrganizationProjectionService;
import dev.riza.workspace.service.OrganizationStream;
import dev.riza.workspace.service.adapter.OrganizationProjectionRepositoryImpl;
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

    @Autowired
    OrganizationProjectionRepositoryImpl organizationProjectionRepository;


    @Bean
    public OrganizationService organizationService() {
        return new OrganizationServiceImpl(organizationEventStore, organizationEventBus);
    }

    @Bean
    public OrganizationProjectionService organizationProjectionService() {
        return new OrganizationProjectionService(organizationProjectionRepository);
    }
}
