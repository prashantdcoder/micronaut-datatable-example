package com.shanky.service

import com.shanky.domain.Bead
import grails.gorm.transactions.Transactional
import io.micronaut.context.annotation.Prototype

import java.text.DecimalFormat

@Prototype
class BootstrapService {

    private static DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    @Transactional
    void initBead() {
        if (!Bead.count()) {
            List<Bead> beadList = []
            beadList << new Bead(color: 'Black', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Kohinoor')
            beadList << new Bead(color: 'Black', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Akbar Shah')
            beadList << new Bead(color: 'Black', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Alexandra')
            beadList << new Bead(color: 'Black', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Briolette')
            beadList << new Bead(color: 'Black', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Cross of Asia')

            beadList << new Bead(color: 'Yellow', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Vargas')
            beadList << new Bead(color: 'Yellow', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Winston')
            beadList << new Bead(color: 'Yellow', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Uncle Sam')
            beadList << new Bead(color: 'Yellow', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Sweeet Josapine')
            beadList << new Bead(color: 'Yellow', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Star of South')

            beadList << new Bead(color: 'Pink', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Hope')
            beadList << new Bead(color: 'Pink', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Heart of eternity')
            beadList << new Bead(color: 'Pink', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Golden Eye')
            beadList << new Bead(color: 'Pink', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Eureka')
            beadList << new Bead(color: 'Pink', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'DeepDene')

            beadList << new Bead(color: 'Green', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Dresden')
            beadList << new Bead(color: 'Green', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Ocean Dream')
            beadList << new Bead(color: 'Green', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Holland')
            beadList << new Bead(color: 'Green', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Skull Star')
            beadList << new Bead(color: 'Green', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Graff')

            beadList << new Bead(color: 'Red', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Allnatt')
            beadList << new Bead(color: 'Red', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Moussaieff')
            beadList << new Bead(color: 'Red', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Wittelsbach')
            beadList << new Bead(color: 'Red', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'Steinmetz')
            beadList << new Bead(color: 'Red', width: generateRandomWidth(100, 500), threadWidth: generateRandomWidth(10, 100), name: 'De Beers Centenary')

            beadList*.save()
        }
    }

    double generateRandomWidth(int minRange, int maxRange) {
        Random random = new Random()
        double randomValue = minRange + (maxRange - minRange) * random.nextDouble()
        return DECIMAL_FORMAT.format(randomValue).toDouble()
    }
}
