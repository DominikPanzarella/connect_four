package ch.supsi.connectfour.frontend.command;

import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;
import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.MakeMoveReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.OpenFileReceiver;

public class MakeMoveCommand<T extends MakeMoveReceiver<MakeMoveHandler>> extends AbstractCommand<T>
{
    private final int column;
    protected MakeMoveCommand(T receiver, final int column) {
        super(receiver);
        this.column = column;
    }

    public static <T extends MakeMoveReceiver<MakeMoveHandler>> MakeMoveCommand<T> create(T receiver, final int column) throws InstantiationException {
        if(receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new MakeMoveCommand<>(receiver, column);
    }

    @Override
    public void execute() {
        receiver.makeMove(column);
    }
}
