package ch.supsi.connectfour.backend.controller;

import ch.supsi.connectfour.backend.service.gamelogic.game.GameService;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class GameController
{
    private GameServiceInterface gameService;
    private static GameController myself;

    protected GameController(GameServiceInterface serviceInterface)
    {
        this.gameService = serviceInterface;
    }

    public static GameController getInstance(){
        if(myself == null)
        {
            myself = new GameController(GameService.getInstance());
        }
        return myself;
    }

    public boolean saveGame(final String destination, final String extension, final List<MoveInterface> moves) throws IOException {
        return gameService.saveGame(destination,extension ,moves);
    }
    public List<MoveInterface> loadGame(final String source) throws IOException{
        return gameService.loadGame(source);
    }


}
