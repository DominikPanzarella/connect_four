package ch.supsi.connectfour.frontend.contracts.receiver;


import ch.supsi.connectfour.frontend.contracts.handler.AboutHandler;

public interface AboutReceiver<T extends AboutHandler> extends Receiver{

    void about();
}
