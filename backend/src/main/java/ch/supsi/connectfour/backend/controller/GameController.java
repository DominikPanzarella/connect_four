package ch.supsi.connectfour.backend.controller;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.nio.file.Path;
import java.util.List;

public class GameController
{
    private GameServiceInterface gameService;
    private static GameController myself;

    protected GameController(){

    }

    public static GameController getInstance(){
        if(myself == null)
        {
            myself = new GameController();
        }
        return myself;
    }

    boolean saveGame(final Path destination){
        return gameService.saveGame(destination);
    }
    List<MoveInterface> loadGame(final Path source){
        return gameService.loadGame(source);
    }


}
