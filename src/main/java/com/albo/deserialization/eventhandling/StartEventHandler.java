package com.albo.deserialization.eventhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartEventHandler implements ApplicationListener<ContextStartedEvent> {
    private static final Logger log = LoggerFactory.getLogger(StartEventHandler.class);

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        log.debug("Context started!");
    }
}
