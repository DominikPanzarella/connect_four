package ch.supsi.connectfour.backend.exceptions;

public class NotYourTurnException extends IllegalArgumentException
{
    public NotYourTurnException(){
        super("It is not your turn!");
    }
}
