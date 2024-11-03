package ch.supsi.connectfour.backend.exceptions;

public class FailedToLoadGameException extends Exception {
    public FailedToLoadGameException(String message) {
        super(message);
    }
}
