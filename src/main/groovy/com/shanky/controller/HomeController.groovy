package com.shanky.controller

import com.shanky.service.BeadService
import com.shanky.service.PaginationCO
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
    HttpResponse beadList(int start, int length, HttpRequest httpRequest) {
        String[] search = httpRequest.getParameters().values()[29]
        PaginationCO paginationCO = new PaginationCO(start: start, length: length, search: search.join(""))
        return HttpResponse.ok(beadService.beadList(paginationCO))
    }
}
