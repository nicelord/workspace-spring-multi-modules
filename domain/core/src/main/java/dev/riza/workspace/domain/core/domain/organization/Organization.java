package dev.riza.workspace.domain.core.domain.organization;


import dev.riza.workspace.domain.core.domain.DomainEvent;
import io.vavr.API;
import io.vavr.Predicates;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;

@Getter
@ToString
public class Organization {

    private UUID aggregateId;
    private String name;
    private List<DomainEvent> changes = new ArrayList<>();

    public Organization(UUID aggregateId, String name) {
        this.aggregateId = aggregateId;
        this.name = name;
        appendChange(new EvtInitialized(aggregateId, LocalDateTime.now(), 0));

    }

    public Organization(UUID aggregateId, List<DomainEvent> domainEvents) {
        this.aggregateId = aggregateId;
        domainEvents.forEach(this::appendChange);
        this.changes.clear();
    }


    /**
     * CMDS
     */

    void changeName(String name) {
        if (name.equals(this.name)) {
            throw new IllegalStateException("same name");
        }
        appendChange(new EvtNameChanged(aggregateId, LocalDateTime.now(), 0, name));
    }


    /**
     * EVENTS
     */

    private Organization nameChanged(EvtNameChanged event) {
        this.name = event.getName();
        return this;
    }


    private void appendChange(DomainEvent domainEvent) {
        /** TODO check version */

        changes.add(domainEvent);
        applyChange(domainEvent);
    }

    private Organization applyChange(DomainEvent domainEvent) {
        return API.Match(domainEvent).of(
                Case($(Predicates.instanceOf(EvtInitialized.class)), this),
                Case($(Predicates.instanceOf(EvtNameChanged.class)), this::nameChanged)
        );
    }


    public List<DomainEvent> getChanges() {
        return Collections.unmodifiableList(changes);
    }
}
