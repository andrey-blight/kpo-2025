package hse.kpo.interfaces;

import hse.kpo.domains.Catamaran;

/**
 * Interface for catamaran.
 */
public interface InterfaceCatamaranFactory<T> {
    Catamaran createCatamaran(T catamaranParams, int catamaranNumber);
}
