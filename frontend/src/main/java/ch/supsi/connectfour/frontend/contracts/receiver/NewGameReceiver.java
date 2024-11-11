package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.NewGameHandler;

public interface NewGameReceiver<T extends NewGameHandler> extends Receiver
{
    void newGame();
}
