package ch.supsi.connectfour.frontend.contracts.observer;

import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

public interface PlayerInfoObserver extends Observer
{
    void playerInfo(final int position,Player player);
}
