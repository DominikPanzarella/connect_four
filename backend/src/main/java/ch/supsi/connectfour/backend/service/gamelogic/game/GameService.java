package ch.supsi.connectfour.backend.service.gamelogic.game;

import ch.supsi.connectfour.backend.controller.GameServiceInterface;
import ch.supsi.connectfour.backend.exceptions.FileReadingException;
import ch.supsi.connectfour.backend.exceptions.GameNotRunningException;
import ch.supsi.connectfour.backend.exceptions.NotYourTurnException;
import ch.supsi.connectfour.backend.repository.GameRepository;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoard;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoardInterface;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.backend.controller.SwitchTurnServiceInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.SwitchTurnService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameService implements GameServiceInterface
{

    private GameRepositoryInterface gameRepository;
    private static GameService myself;

    protected GameService(GameRepositoryInterface repositoryInterface){

        this.gameRepository = repositoryInterface;
    }

    public static GameService getInstance(){

        if(myself==null)
        {
            myself = new GameService(GameRepository.getInstance());
        }
        return myself;
    }

    @Override
    public boolean saveGame(String destination,final String extension,final List<MoveInterface> moves) throws IOException {
        return gameRepository.handleSaveGame(destination, extension,moves);
    }

    @Override
    public List<MoveInterface> loadGame(String source) {
        int pointPosition = source.lastIndexOf(".");
        if(pointPosition == (-1))
            throw new FileReadingException("File extension not valid!");
        String extension = source.substring(pointPosition+1);
        return gameRepository.handleLoadGame(source,extension);
    }
}
