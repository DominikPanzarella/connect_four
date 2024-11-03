package ch.supsi.connectfour.backend.exceptions;

public class FailedToSaveGameException extends Exception {
    public FailedToSaveGameException(String message) {
        super(message);
    }
}
