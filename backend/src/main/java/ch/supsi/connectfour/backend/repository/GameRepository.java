package ch.supsi.connectfour.backend.repository;

import ch.supsi.connectfour.backend.service.gamelogic.game.GameRepositoryInterface;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.nio.file.Path;
import java.util.List;

public class GameRepository implements GameRepositoryInterface
{
    private static GameRepository myself;

    protected GameRepository(){
        //reader and writer must be set
    }

    public static GameRepository getInstance()
    {
        if(myself==null)
        {
            myself = new GameRepository();
        }
        return myself;
    }

    @Override
    public boolean handleSaveGame(Path destination) {
        return true;
    }

    @Override
    public List<MoveInterface> handleLoadGame(Path source) {
        return null;
    }
}
