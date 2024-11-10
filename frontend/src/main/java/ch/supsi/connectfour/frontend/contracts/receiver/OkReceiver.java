package ch.supsi.connectfour.frontend.contracts.receiver;


import ch.supsi.connectfour.frontend.contracts.handler.OKHandler;

public interface OkReceiver<T extends OKHandler> extends Receiver
{
    void ok();
}
