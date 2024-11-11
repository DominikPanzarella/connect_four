package ch.supsi.connectfour.frontend.command;

import ch.supsi.connectfour.frontend.contracts.handler.AboutHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.AboutReceiver;

public class AboutCommand<T extends AboutReceiver<AboutHandler>> extends AbstractCommand<T>
{

    protected AboutCommand(T aboutReceiver){
        super(aboutReceiver);
    }

    public static <T extends AboutReceiver<AboutHandler>> AboutCommand<T> create(T receiver) throws InstantiationException {
        if (receiver == null)
            throw new InstantiationException("Command receiver cannot be null!");
        return new AboutCommand<>(receiver);
    }

    @Override
    public void execute() {
        receiver.about();
    }
}
