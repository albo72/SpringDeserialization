package com.albo.deserialization.eventhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class StopEventHandler implements ApplicationListener<ContextStoppedEvent> {
    private static final Logger log = LoggerFactory.getLogger(StopEventHandler.class);

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        log.debug("Context stopped!");
    }
}
