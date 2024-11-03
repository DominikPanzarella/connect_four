package ch.supsi.connectfour.backend.exceptions;

public class GameNotRunningException extends IllegalArgumentException
{
    public GameNotRunningException(){
        super("Current Game is not running!");
    }
}
