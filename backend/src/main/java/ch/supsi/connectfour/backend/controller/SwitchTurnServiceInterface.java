package ch.supsi.connectfour.backend.controller;

import ch.supsi.connectfour.backend.exceptions.GameFullException;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

public interface SwitchTurnServiceInterface
{
    void addPlayer(final Player player) throws GameFullException;
    void reset();
    Player getCurrentPlayer();
    Player getPlayerAtIndex(final int index);
    int getCurrentPlayers();
    int getCurrentPlayerIndex();
    int getMaxPlayers();
    void switchTurn();
}
