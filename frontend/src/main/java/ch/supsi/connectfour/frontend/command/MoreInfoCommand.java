package ch.supsi.connectfour.frontend.command;


import ch.supsi.connectfour.frontend.contracts.handler.MoreInfoHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.MoreInfoReceiver;

public class MoreInfoCommand<T extends MoreInfoReceiver<MoreInfoHandler>> extends AbstractCommand<T>{
    protected MoreInfoCommand(T receiver) {
        super(receiver);
    }

    public static <T extends MoreInfoReceiver<MoreInfoHandler>> MoreInfoCommand<T> create(T receiver) throws InstantiationException {
        if (receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new MoreInfoCommand<>(receiver);
    }

    @Override
    public void execute() {
        receiver.moreInfo();
    }
}
