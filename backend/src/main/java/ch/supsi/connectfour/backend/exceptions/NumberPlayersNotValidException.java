package ch.supsi.connectfour.backend.exceptions;

public class NumberPlayersNotValidException extends IllegalArgumentException
{

    public NumberPlayersNotValidException()
    {
        super("Number of players is not valid!");
    }
}
