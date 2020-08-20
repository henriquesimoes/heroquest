package br.unicamp.ic.mc322.heroquest.map.core;

public class OccupiedUnitException extends IllegalStateException {
    public OccupiedUnitException() {
    }

    public OccupiedUnitException(String s) {
        super(s);
    }

    public OccupiedUnitException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedUnitException(Throwable cause) {
        super(cause);
    }
}
