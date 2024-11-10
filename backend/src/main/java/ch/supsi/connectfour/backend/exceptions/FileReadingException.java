package ch.supsi.connectfour.backend.exceptions;

import java.io.IOException;

public class FileReadingException extends RuntimeException
{
    public FileReadingException(){
        super("Invalid file extension!");
    }

    public FileReadingException(final String message){
        super(message);
    }


    public FileReadingException(String s, IOException e) {
        super(s,e);
    }
}
