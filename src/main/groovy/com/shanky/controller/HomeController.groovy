package com.shanky.controller

import com.shanky.domain.Bead
import com.shanky.service.BeadService
import com.shanky.service.PaginationCO
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
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

    @Get(value = "/bead/list", produces = MediaType.APPLICATION_JSON)
    Map beadList(HttpRequest httpRequest) {
        return beadService.beadList(httpRequest)
    }

    @Get(value = "/diamond")
    @View("/diamond")
    HttpResponse diamonds() {
        return HttpResponse.ok()
    }

    @Get(value = "/fetchDiamonds", produces = MediaType.APPLICATION_JSON)
    List<Bead> fetchDiamond(Integer start, Integer length) {
        PaginationCO paginationCO = new PaginationCO(start: start, length: length)
        return beadService.filteredBeadList(paginationCO)
    }


}
