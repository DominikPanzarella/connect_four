package ch.supsi.connectfour.frontend.contracts.handler;

import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;

public interface SaveNewInfoHandler
{
    void saveNewInfo(final int position,final String newName, final MySymbolInterface newSymbol);
}
