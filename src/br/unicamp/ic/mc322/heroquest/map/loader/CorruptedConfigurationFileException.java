package br.unicamp.ic.mc322.heroquest.map.loader;

public class CorruptedConfigurationFileException extends IllegalStateException {
    public CorruptedConfigurationFileException() {
    }

    public CorruptedConfigurationFileException(String s) {
        super(s);
    }

    public CorruptedConfigurationFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CorruptedConfigurationFileException(Throwable cause) {
        super(cause);
    }
}
