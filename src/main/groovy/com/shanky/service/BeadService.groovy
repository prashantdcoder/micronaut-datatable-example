package com.shanky.service

import com.shanky.domain.Bead
import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Bead)
class BeadService {

    @Transactional
    Map beadList(PaginationCO paginationCO) {
        List<Bead> beadList = filteredBeadList(paginationCO)
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
        } as List<Bead>
        return beadList ?: []
    }

    Map generateDataTable(List<Bead> beadList) {
        int beadCount = Bead.list().size()
        List<String> dataRow = []
        List<List> beadData = []

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
}
