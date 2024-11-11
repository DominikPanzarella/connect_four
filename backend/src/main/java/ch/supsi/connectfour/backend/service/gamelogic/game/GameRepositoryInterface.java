package ch.supsi.connectfour.backend.service.gamelogic.game;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface GameRepositoryInterface
{

    boolean handleSaveGame(final String destination, final String extension,final List<MoveInterface> moves) throws IOException;
    List<MoveInterface> handleLoadGame(final String source, final String extension);

}
