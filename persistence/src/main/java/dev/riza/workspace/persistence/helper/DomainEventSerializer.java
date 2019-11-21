package dev.riza.workspace.persistence.helper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.riza.workspace.domain.core.model.DomainEvent;
import dev.riza.workspace.persistence.DomainEventEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class DomainEventSerializer {
    private final ObjectMapper objectMapper;

    public DomainEventSerializer() {
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public DomainEventEntity serialize(DomainEvent event){
        try {
            return DomainEventEntity.builder()
                    .uuid(event.aggregateUuid())
                    .type(event.type())
                    .payload(objectMapper.writeValueAsString(event))
                    .when(event.when())
                    .version(event.version())
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public DomainEvent deserialize(DomainEventEntity baseEvent){
        try {
            return objectMapper.readValue(baseEvent.getPayload(), DomainEvent.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
