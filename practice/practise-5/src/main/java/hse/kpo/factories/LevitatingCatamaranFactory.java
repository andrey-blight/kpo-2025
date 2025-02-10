package hse.kpo.factories;

import hse.kpo.domains.Car;
import hse.kpo.domains.Catamaran;
import hse.kpo.domains.LevitatingEngine;
import hse.kpo.interfaces.InterfaceCarFactory;
import hse.kpo.interfaces.InterfaceCatamaranFactory;
import hse.kpo.params.EmptyEngineParams;

/**
 * Factory for levitating cars.
 */
public class LevitatingCatamaranFactory implements InterfaceCatamaranFactory<EmptyEngineParams> {
    @Override
    public Catamaran createCatamaran(EmptyEngineParams catamaranParams, int catamaranNumber) {
        var engine = new LevitatingEngine(); // Создаем двигатель без каких-либо параметров

        return new Catamaran(catamaranNumber, engine); // создаем автомобиль с ручным приводом
    }
}
