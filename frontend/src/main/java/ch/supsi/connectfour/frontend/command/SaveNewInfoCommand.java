package ch.supsi.connectfour.frontend.command;

import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.frontend.contracts.handler.AboutHandler;
import ch.supsi.connectfour.frontend.contracts.handler.SaveNewInfoHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.AboutReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.SaveNewInfoReceiver;

public class SaveNewInfoCommand <T extends SaveNewInfoReceiver<SaveNewInfoHandler>> extends AbstractCommand<T>
{

    private String newName;
    private MySymbolInterface newSymbol;
    private int position;


    protected SaveNewInfoCommand(T receiver) {
        super(receiver);
        this.position = receiver.getPosition();
    }

    public static <T extends SaveNewInfoReceiver<SaveNewInfoHandler>> SaveNewInfoCommand<T> create(T receiver) throws InstantiationException {
        if (receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new SaveNewInfoCommand<>(receiver);
    }

    public void setNewName(String newName){
        this.newName = newName;
    }

    public void setNewSymbol(MySymbolInterface newSymbol){
        this.newSymbol = newSymbol;
    }

    public int getPosition(){return position; }

    @Override
    public void execute() {
        receiver.saveNewInfo(position, newName, newSymbol);
    }
}
