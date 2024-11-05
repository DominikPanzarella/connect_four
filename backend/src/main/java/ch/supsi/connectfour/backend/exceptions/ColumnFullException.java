package ch.supsi.connectfour.backend.exceptions;

public class ColumnFullException extends RuntimeException
{
    public ColumnFullException(){
        super("Column full!");
    }
}
