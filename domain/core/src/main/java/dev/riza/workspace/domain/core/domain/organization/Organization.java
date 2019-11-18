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
    private int baseVersion;
    private String address;


    public Organization(UUID aggregateId, String name) {
        this.aggregateId = aggregateId;
        this.name = name;
        appendChange(new EvtInitialized(aggregateId, LocalDateTime.now(), getNextVersion()));
    }

    public Organization(UUID aggregateId, List<DomainEvent> domainEvents) {
        this.aggregateId = aggregateId;
        domainEvents.forEach(domainEvent -> {
            System.out.println(domainEvent.type() + " : " + domainEvent.version());
            appendChange(domainEvent);
            this.baseVersion = domainEvent.version();
        });
        this.changes.clear();
    }


    /**
     * CMDS
     */

    void changeName(String name) {
        if (name.equals(this.name)) {
            throw new IllegalStateException("same name");
        }
        appendChange(new EvtNameChanged(aggregateId, LocalDateTime.now(), getNextVersion(), name));
    }

    void changeAddress(String newAddress) {
        appendChange(new EvtAddressChanged(aggregateId, LocalDateTime.now(), getNextVersion(), newAddress));
    }



    /**
     * EVENTS
     */

    private Organization nameChanged(EvtNameChanged event) {
        this.name = event.getName();
        return this;
    }

    private Organization addressChanged(EvtAddressChanged event) {
        this.address = event.getNewAddress();
        return this;
    }



    private void appendChange(DomainEvent domainEvent) {
        if (domainEvent.version() != getNextVersion()) {
            throw new IllegalStateException(String.format("New event version '%d' does not match expected next version '%d' on domain event '%s'",
                    domainEvent.version(), getNextVersion(), domainEvent.type()));
        }

        changes.add(domainEvent);
        applyChange(domainEvent);
    }

    private Organization applyChange(DomainEvent domainEvent) {
        return API.Match(domainEvent).of(
                Case($(Predicates.instanceOf(EvtInitialized.class)), this),
                Case($(Predicates.instanceOf(EvtNameChanged.class)), this::nameChanged),
                Case($(Predicates.instanceOf(EvtAddressChanged.class)), this::addressChanged)
        );
    }


    public List<DomainEvent> getChanges() {
        return Collections.unmodifiableList(changes);
    }

    protected int getNextVersion() {
        return baseVersion + 1;
    }
}
