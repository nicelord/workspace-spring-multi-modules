package dev.riza.workspace.service.adapter;

import dev.riza.workspace.domain.core.domain.DomainEvent;
import dev.riza.workspace.domain.core.port.OrganizationEventBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import java.util.List;

@Component
public class OrganizationEventBusImpl implements OrganizationEventBus {

    @Autowired
    OrganizationStream organizationStream;

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(domainEvent -> {
            organizationStream.outputChannel()
                    .send(MessageBuilder
                            .withPayload(domainEvent)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
        });
    }
}
