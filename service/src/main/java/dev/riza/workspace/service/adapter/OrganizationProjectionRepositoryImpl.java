package dev.riza.workspace.service.adapter;

import dev.riza.workspace.projection.OrganizationProjectionRepository;
import org.springframework.stereotype.Component;

@Component
public class OrganizationProjectionRepositoryImpl implements OrganizationProjectionRepository {
    @Override
    public void save(String data) {
        System.out.println("projection updated : " + data);
    }
}
