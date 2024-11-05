package ch.supsi.connectfour.backend.exceptions;

public class FileReadingException extends RuntimeException
{
    public FileReadingException(){
        super("Invalid file extension!");
    }

    public FileReadingException(final String message){
        super(message);
    }


}
