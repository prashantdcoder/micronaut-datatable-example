package com.shanky.service

import com.shanky.domain.Bead
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import io.micronaut.http.HttpRequest

@Service(Bead)
class BeadService {

    @Transactional
    Map beadList(HttpRequest httpRequest) {
        List<Bead> beadList = filteredBeadList(generatePaginationCO(httpRequest))
        return generateDataTable(beadList)
    }


    List<Bead> filteredBeadList(PaginationCO paginationCO) {
        List<Bead> beadList = Bead.createCriteria().list(offset: paginationCO.start, max: paginationCO.length + paginationCO.start) {
            or {
                if (paginationCO.search) {
                    ilike('name', "%${paginationCO.search.trim()}%")
                    ilike('color', "%${paginationCO.search.trim()}%")
                }
            }
            switch (paginationCO.columnIndex) {
                case 0: order('name', paginationCO.direction)
                    break
                case 1: order('color', paginationCO.direction)
                    break
                case 2: order('width', paginationCO.direction)
                    break
                case 3: order('threadWidth', paginationCO.direction)
                    break
            }
        } as List<Bead>
        return beadList ?: []
    }

    Map generateDataTable(List<Bead> beadList) {
        int beadCount = Bead.list().size()
        List<String> dataRow = []
        List<List> beadData = []
        if (beadList) {
            beadList.each { bead ->
                dataRow << bead.name
                dataRow << bead.color
                dataRow << bead.width.toString()
                dataRow << bead.threadWidth.toString()
                beadData << dataRow
                dataRow = []
            }
            return [data: beadData, recordsTotal: beadCount, recordsFiltered: beadCount]
        }
        return [data: [[null, null, null, null]], recordsTotal: beadCount, recordsFiltered: 0]
    }

    PaginationCO generatePaginationCO(HttpRequest httpRequest) {
        String[] direction = httpRequest.getParameters().asMap().get("order[0][dir]")
        String[] search = httpRequest.getParameters().asMap().get("search[value]")
        String[] columnIndex = httpRequest.getParameters().asMap().get("order[0][column]")
        int start = httpRequest.getParameters().asMap().get("start").join("").toInteger()
        int length = httpRequest.getParameters().asMap().get("length").join("").toInteger()
        PaginationCO paginationCO = new PaginationCO(start: start, length: length, search: search.join(""), columnIndex: columnIndex.join("").toLong(), direction: direction.join(""))
        return paginationCO
    }
}
