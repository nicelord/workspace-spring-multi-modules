package dev.riza.workspace.domain.app.commands;

import lombok.Getter;

import java.util.UUID;

@Getter
public class CmdChangeAddress {
    private UUID uuid;
    private String newAddress;

    public CmdChangeAddress(UUID uuid, String newAddress) {
        this.uuid = uuid;
        this.newAddress = newAddress;
    }
}
