package ch.supsi.connectfour.backend.service.gamelogic.player;

import ch.supsi.connectfour.backend.controller.SwitchTurnServiceInterface;
import ch.supsi.connectfour.backend.exceptions.EmptyGameException;
import ch.supsi.connectfour.backend.exceptions.GameFullException;
import ch.supsi.connectfour.backend.exceptions.NumberPlayersNotValidException;

import java.util.ArrayList;
import java.util.List;

public class SwitchTurnService implements SwitchTurnServiceInterface {
    private final List<Player> players;
    private final int maxPlayers;
    private int currentPlayerIndex;
    private static SwitchTurnService sw = null;

    public SwitchTurnService(final int maxPlayers)
    {
        if(maxPlayers < 1)      throw new NumberPlayersNotValidException();
        players = new ArrayList<>();
        this.maxPlayers = maxPlayers;
        currentPlayerIndex = 0;
    }

    public static SwitchTurnService getInstance(int maxPlayers){
        if(sw == null)          sw = new SwitchTurnService(maxPlayers);
        return sw;
    }

    @Override
    public void addPlayer(final Player player) throws GameFullException{
        if(players.size() >= maxPlayers)            throw new GameFullException();
        players.add(player);
    }

    @Override
    public int getMaxPlayers(){
        return maxPlayers;
    }

    @Override
    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }

    @Override
    public int getCurrentPlayers(){
        return players.size();
    }

    @Override
    public Player getPlayerAtIndex(final int index)
    {
        return players.get(index);
    }

    @Override
    public Player getCurrentPlayer()
    {
        return getPlayerAtIndex(this.currentPlayerIndex);
    }

    @Override
    public void reset()
    {
        this.currentPlayerIndex = 0;
    }

    @Override
    public void switchTurn(boolean onPlayerTurn)
    {
        if(players.isEmpty())           throw new EmptyGameException();

        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

        if(onPlayerTurn){
            players.get(currentPlayerIndex).onMyTurn();
        }

    }

}