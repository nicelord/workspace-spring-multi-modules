package dev.riza.workspace.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrganizationStream {
    String OUTPUT = "organization-out";
    String INPUT = "organization-in";

    @Output(OUTPUT)
    MessageChannel outputChannel();

    @Input(INPUT)
    SubscribableChannel inputChannel();

}
