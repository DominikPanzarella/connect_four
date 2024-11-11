package ch.supsi.connectfour.frontend.command;


import ch.supsi.connectfour.frontend.contracts.handler.RedoHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.RedoReceiver;

public class RedoCommand<T extends RedoReceiver<RedoHandler>> extends AbstractCommand<T> {
    protected RedoCommand(T receiver) {
        super(receiver);
    }

    public static <T extends RedoReceiver<RedoHandler>> RedoCommand<T> create(T receiver) throws InstantiationException {
        if (receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new RedoCommand<>(receiver);
    }

    @Override
    public void execute() {
        receiver.redo();
    }
}
