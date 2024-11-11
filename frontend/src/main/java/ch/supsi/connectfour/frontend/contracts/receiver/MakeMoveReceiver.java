package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;


public interface MakeMoveReceiver<T extends MakeMoveHandler> extends Receiver
{
    void makeMove(final int column);
}
