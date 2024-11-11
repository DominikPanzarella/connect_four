package ch.supsi.connectfour.frontend.command;

import ch.supsi.connectfour.frontend.contracts.handler.NewGameHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.NewGameReceiver;

public class NewGameCommand<T extends NewGameReceiver<NewGameHandler>> extends AbstractCommand<T>{
    protected NewGameCommand(T receiver) {
        super(receiver);
    }

    public static <T extends NewGameReceiver<NewGameHandler>> NewGameCommand<T> create(T receiver) throws InstantiationException {
        if(receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new NewGameCommand<>(receiver);
    }

    @Override
    public void execute() {
        receiver.newGame();
    }
}
