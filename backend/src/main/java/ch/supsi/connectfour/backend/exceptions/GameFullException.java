package ch.supsi.connectfour.backend.exceptions;

public class GameFullException extends IllegalArgumentException
{
    public GameFullException(){
        super("Game is already full!");
    }
}
