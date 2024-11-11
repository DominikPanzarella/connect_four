package ch.supsi.connectfour.backend.exceptions;

public class GameAlreadyRunningException extends IllegalArgumentException
{

    public GameAlreadyRunningException(){
        super("A Game is already running");
    }
}
