package dev.riza.workspace.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
public class DomainEventEntity {
    @Id
    @GeneratedValue
    private Long id;
    private UUID uuid;

    private String type;
    private LocalDateTime when;
    private int version;
    private String payload;

    public DomainEventEntity() {
    }
}
