package dev.riza.workspace.domain.core.model.organization;


import dev.riza.workspace.domain.core.model.DomainEvent;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class EvtNameChanged implements DomainEvent {
    public static final String type = "NameChanged";
    private UUID aggregateId;
    private int version;
    private LocalDateTime when;
    private String name;

    public EvtNameChanged(UUID aggregateId, LocalDateTime when, int version, String name) {
        this.aggregateId = aggregateId;
        this.version = version;
        this.when = when;
        this.name = name;
    }

    public EvtNameChanged() {
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
