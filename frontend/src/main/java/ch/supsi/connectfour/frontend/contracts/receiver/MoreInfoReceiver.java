package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.frontend.contracts.handler.MoreInfoHandler;

public interface MoreInfoReceiver<T extends MoreInfoHandler> extends Receiver{
    void moreInfo();
}
