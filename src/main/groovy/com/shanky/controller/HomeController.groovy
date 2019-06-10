package com.shanky.controller

import com.shanky.service.BeadService
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View

import javax.inject.Inject

@Controller("/")
class HomeController {

    @Inject
    BeadService beadService

    @View("/index")
    @Get(value = "/")
    HttpResponse index() {
        return HttpResponse.ok()
    }

    @Get(value = "/bead/list")
    HttpResponse beadList(HttpRequest httpRequest) {
        return HttpResponse.ok(beadService.beadList(httpRequest))
    }
}
