package ch.supsi.connectfour.frontend.command;


import ch.supsi.connectfour.frontend.contracts.handler.ExitHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.ExitReceiver;

public class ExitCommand<T extends ExitReceiver<ExitHandler>> extends AbstractCommand<T>
{

    protected ExitCommand(T receiver) {
        super(receiver);
    }

    public static <T extends ExitReceiver<ExitHandler>> ExitCommand<T> create(T receiver) throws InstantiationException {
        if (receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new ExitCommand<>(receiver);
    }

    @Override
    public void execute() {
        receiver.exit();
    }
}
