package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.ExitHandler;

public interface ExitReceiver<T extends ExitHandler>  extends Receiver
{
    void exit();
}
