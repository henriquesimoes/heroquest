package br.unicamp.ic.mc322.heroquest.map.loader;

public class InvalidResourcesFolderLocationException extends IllegalStateException {
    public InvalidResourcesFolderLocationException() {
    }

    public InvalidResourcesFolderLocationException(String s) {
        super(s);
    }

    public InvalidResourcesFolderLocationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidResourcesFolderLocationException(Throwable cause) {
        super(cause);
    }
}
