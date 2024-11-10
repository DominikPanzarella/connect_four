package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.PlayerInfoHandler;

public interface PlayerInfoReceiver<T extends PlayerInfoHandler> extends Receiver
{
    void playerInfo(final int position);

    int getPosition();
}
