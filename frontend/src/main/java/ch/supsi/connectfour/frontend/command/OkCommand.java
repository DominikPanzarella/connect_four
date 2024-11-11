package ch.supsi.connectfour.frontend.command;


import ch.supsi.connectfour.frontend.contracts.handler.OKHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.OkReceiver;

public class OkCommand<T extends OkReceiver<OKHandler>> extends AbstractCommand<T>
{

    protected OkCommand(T receiver) {
        super(receiver);
    }

    public static <T extends OkReceiver<OKHandler>> OkCommand<T> create(T receiver) throws InstantiationException {
        if(receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return  new OkCommand<>(receiver);
    }


    @Override
    public void execute() {
        receiver.ok();
    }
}
