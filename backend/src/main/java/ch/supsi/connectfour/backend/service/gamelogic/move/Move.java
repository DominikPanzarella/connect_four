package ch.supsi.connectfour.backend.service.gamelogic.move;

import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

public class Move implements MoveInterface
{
    private final int row;
    private final int column;

    private Player player;

    public Move(int row, int column, Player player) {
        this.row = row;
        this.column = column;
        this.player = player;
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public int getRow()
    {
        return row;
    }

    public int getColumn(){
        return column;
    }

    @Override
    public String toString() {
        String playerName = player != null ? ""+player.hashCode() : "Unknown";
        return "Move: [" + playerName + " moved to row " + row + ", column " + column + "]";
    }


}
