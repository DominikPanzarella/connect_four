package ch.supsi.connectfour.frontend.contracts.observer;

import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;

public interface SaveNewInfoObserver extends Observer
{
    void saveNewInfo(final String playerID,final String newName, final MySymbolInterface newSymbol);
}
