package ch.supsi.connectfour.frontend.contracts.receiver;

import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.frontend.contracts.handler.SaveNewInfoHandler;

public interface SaveNewInfoReceiver<T extends SaveNewInfoHandler> extends Receiver
{
    void saveNewInfo(final int position,final String newName, final MySymbolInterface newSymbol);
    int getPosition();
}
