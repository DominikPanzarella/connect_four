package ch.supsi.connectfour.backend.exceptions;

public class SymbolMenuNotFoundException extends IllegalArgumentException
{
    public SymbolMenuNotFoundException(){
        super("symbolmenu.fxml not found");
    }
}
