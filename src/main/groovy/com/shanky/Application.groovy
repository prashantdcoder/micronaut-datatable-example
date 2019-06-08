package com.shanky

import com.shanky.service.BootstrapService
import groovy.transform.CompileStatic
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.event.annotation.EventListener

import javax.inject.Inject

@CompileStatic
class Application {


    @Inject
    BootstrapService bootstrapService

    @EventListener
    void startUp(ServiceStartedEvent event) {
        bootstrapService.initBead()
    }

    static void main(String[] args) {
        Micronaut.run(Application)
    }
}