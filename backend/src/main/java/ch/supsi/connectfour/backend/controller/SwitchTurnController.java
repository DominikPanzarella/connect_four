package ch.supsi.connectfour.backend.controller;

import ch.supsi.connectfour.backend.exceptions.GameFullException;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.backend.service.gamelogic.player.SwitchTurnService;

public class SwitchTurnController
{
    private static final int MAX_PLAYERS = 2;
    private SwitchTurnServiceInterface switchTurnService;
    private static SwitchTurnController myself;

    protected SwitchTurnController(){
        switchTurnService = SwitchTurnService.getInstance(MAX_PLAYERS);
    }

    public static SwitchTurnController getInstance(){
        if(myself==null)
        {
            myself = new SwitchTurnController();
        }
        return myself;
    }

    public void addPlayer(final Player player) throws GameFullException {
        switchTurnService.addPlayer(player);
    }
    public void reset(){
        switchTurnService.reset();
    }
    public Player getCurrentPlayer(){
        return switchTurnService.getCurrentPlayer();
    }
    public Player getPlayerAtIndex(final int index){
        return switchTurnService.getPlayerAtIndex(index);
    }

    public int getCurrentPlayers(){
        return switchTurnService.getCurrentPlayers();
    }
    public int getCurrentPlayerIndex(){
        return switchTurnService.getCurrentPlayerIndex();
    }
    public int getMaxPlayers(){
        return switchTurnService.getMaxPlayers();
    }

    public void switchTurn()
    {
        switchTurnService.switchTurn();
    }
}
