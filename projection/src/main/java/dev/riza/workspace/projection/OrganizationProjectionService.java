package dev.riza.workspace.projection;

public class OrganizationProjectionService {

    private final OrganizationProjectionRepository organizationProjectionRepository;

    public OrganizationProjectionService(OrganizationProjectionRepository organizationProjectionRepository) {
        this.organizationProjectionRepository = organizationProjectionRepository;
    }

    public void save(String data) {
        organizationProjectionRepository.save(data);
    }

}
