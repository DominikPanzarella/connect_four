package ch.supsi.connectfour.backend.exceptions;

public class PositionNotValidException extends IllegalArgumentException
{

    public PositionNotValidException()
    {
        super("Position not valid!");
    }
}
