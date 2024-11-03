package ch.supsi.connectfour.backend.exceptions;

public class PathEmptyException extends IllegalArgumentException
{
    public PathEmptyException(){
        super("Path file is empty!");
    }
}
