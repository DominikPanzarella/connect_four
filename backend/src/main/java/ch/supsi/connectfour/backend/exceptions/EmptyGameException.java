package ch.supsi.connectfour.backend.exceptions;

public class EmptyGameException extends IllegalArgumentException
{
    public EmptyGameException(){
        super("No players added yet!");
    }
}
