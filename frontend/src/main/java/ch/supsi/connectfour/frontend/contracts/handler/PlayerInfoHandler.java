package ch.supsi.connectfour.frontend.contracts.handler;

import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

public interface PlayerInfoHandler extends Handler
{
    void playerInfo(final int position);
}
