package ch.supsi.connectfour.backend.service.gamelogic.board;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

import java.util.List;

public interface GameBoardInterface
{
    void setCell(final int row, final int col, final Player player);
    void setCell(final MoveInterface move);
    Player getPlayerAtCell(final int row, final int col);
    Player[][] getBoard();
    boolean isFull();
    boolean isColumnFull(final int column);
    int findFirstAvailableRow(final int column);
    void resetBoard();
    void unsetCell(final MoveInterface move);
    void setCells(List<MoveInterface> moves);
    int getWidth();
    int getHeight();


}
