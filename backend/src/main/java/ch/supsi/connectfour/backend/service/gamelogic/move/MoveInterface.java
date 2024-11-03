package ch.supsi.connectfour.backend.service.gamelogic.move;

import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

public interface MoveInterface
{
    void setPlayer(Player player);

    Player getPlayer();

    int getRow();

    int getColumn();

}
