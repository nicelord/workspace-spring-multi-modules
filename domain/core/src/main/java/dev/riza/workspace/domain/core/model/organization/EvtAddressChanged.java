package dev.riza.workspace.domain.core.model.organization;

import dev.riza.workspace.domain.core.model.DomainEvent;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EvtAddressChanged implements DomainEvent {
    public static final String type = "AddressChanged";
    private UUID aggregateId;
    private int version;
    private LocalDateTime when;
    private String newAddress;

    public EvtAddressChanged(UUID aggregateId, LocalDateTime when, int version, String newAddress) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.when = when;
        this.newAddress = newAddress;
    }

    public EvtAddressChanged() {
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public LocalDateTime when() {
        return when;
    }

    @Override
    public UUID aggregateUuid() {
        return aggregateId;
    }

    @Override
    public int version() {
        return version;
    }
}
