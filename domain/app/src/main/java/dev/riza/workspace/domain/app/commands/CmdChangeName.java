package dev.riza.workspace.domain.app.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CmdChangeName {
    private final UUID aggregateId;
    private final String newName;

    public CmdChangeName(UUID aggregateId, String newName) {
        this.aggregateId = aggregateId;
        this.newName = newName;
    }
}
