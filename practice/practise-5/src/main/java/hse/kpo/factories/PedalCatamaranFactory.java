package hse.kpo.factories;


import hse.kpo.domains.Car;
import hse.kpo.domains.Catamaran;
import hse.kpo.domains.PedalEngine;
import hse.kpo.interfaces.InterfaceCarFactory;
import hse.kpo.interfaces.InterfaceCatamaranFactory;
import hse.kpo.params.PedalEngineParams;

/**
 * Factory for pedal cars.
 */
public class PedalCatamaranFactory implements InterfaceCatamaranFactory<PedalEngineParams> {
    @Override
    public Catamaran createCatamaran(PedalEngineParams catamaranParams, int catamaranNumber) {
        var engine = new PedalEngine(catamaranParams.pedalSize()); // создаем двигатель на основе переданных параметров

        // возвращаем собранный автомобиль с установленным двигателем и определенным номером
        return new Catamaran(catamaranNumber, engine);
    }
}
