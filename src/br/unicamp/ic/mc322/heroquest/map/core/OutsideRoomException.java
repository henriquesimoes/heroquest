package br.unicamp.ic.mc322.heroquest.map.core;

public class OutsideRoomException extends IllegalStateException {
    public OutsideRoomException() {
    }

    public OutsideRoomException(String s) {
        super(s);
    }

    public OutsideRoomException(String message, Throwable cause) {
        super(message, cause);
    }

    public OutsideRoomException(Throwable cause) {
        super(cause);
    }
}
