package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.Catamaran;
import hse.kpo.domains.HandEngine;
import hse.kpo.interfaces.InterfaceCarFactory;
import hse.kpo.interfaces.InterfaceCatamaranFactory;
import hse.kpo.params.EmptyEngineParams;


/**
 * Factory for hand cars.
 */
public class HandCatamaranFactory implements InterfaceCatamaranFactory<EmptyEngineParams> {
    @Override
    public Catamaran createCatamaran(EmptyEngineParams catamaranParams, int catamaranNumber) {
        var engine = new HandEngine(); // Создаем двигатель без каких-либо параметров

        return new Catamaran(catamaranNumber, engine); // создаем автомобиль с ручным приводом
    }
}
