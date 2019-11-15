package dev.riza.workspace.domain.core.domain.organization;


import dev.riza.workspace.domain.core.domain.DomainEvent;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EvtInitialized implements DomainEvent {
    public static final String type = "Initialized";
    private UUID aggregateId;
    private int version;
    private LocalDateTime when;

    public EvtInitialized(UUID aggregateId, LocalDateTime when, int version) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.when = when;
    }

    public EvtInitialized() {
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
