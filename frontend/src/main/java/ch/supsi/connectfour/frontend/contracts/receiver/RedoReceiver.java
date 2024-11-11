package ch.supsi.connectfour.frontend.contracts.receiver;


import ch.supsi.connectfour.frontend.contracts.handler.RedoHandler;

public interface RedoReceiver<T extends RedoHandler> extends Receiver
{
    void redo();
}
