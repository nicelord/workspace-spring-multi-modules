package dev.riza.workspace.service.adapter;


import dev.riza.workspace.domain.core.domain.organization.OrganizationServiceImpl;
import dev.riza.workspace.persistence.adapter.OrganizationEventStoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Configuration {

    @Autowired
    OrganizationEventStoreImpl organizationEventStore;

    @Bean
    public OrganizationServiceImpl organizationService() {
        return new OrganizationServiceImpl(organizationEventStore);
    }
}
