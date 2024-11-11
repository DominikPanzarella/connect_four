package ch.supsi.connectfour.frontend.contracts.receiver;


import ch.supsi.connectfour.frontend.contracts.handler.UndoHandler;

public interface UndoReceiver<T extends UndoHandler> extends Receiver
{
    void undo();
}
