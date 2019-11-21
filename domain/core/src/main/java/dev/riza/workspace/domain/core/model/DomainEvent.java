package dev.riza.workspace.domain.core.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import dev.riza.workspace.domain.core.model.organization.EvtAddressChanged;
import dev.riza.workspace.domain.core.model.organization.EvtInitialized;
import dev.riza.workspace.domain.core.model.organization.EvtNameChanged;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(name = EvtInitialized.type, value = EvtInitialized.class),
        @JsonSubTypes.Type(name = EvtNameChanged.type, value = EvtNameChanged.class),
        @JsonSubTypes.Type(name = EvtAddressChanged.type, value = EvtAddressChanged.class)
})

public interface DomainEvent {


    String type();

    LocalDateTime when();

    UUID aggregateUuid();

    int version();
}
