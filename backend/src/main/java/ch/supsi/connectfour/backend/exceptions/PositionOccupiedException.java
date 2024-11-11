package ch.supsi.connectfour.backend.exceptions;

public class PositionOccupiedException extends IllegalArgumentException
{
    public PositionOccupiedException(){
        super("Position selected is already occupied");
    }
}
