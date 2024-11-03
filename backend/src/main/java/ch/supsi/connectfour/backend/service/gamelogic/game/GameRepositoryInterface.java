package ch.supsi.connectfour.backend.service.gamelogic.game;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.nio.file.Path;
import java.util.List;

public interface GameRepositoryInterface
{

    boolean handleSaveGame(final Path destination);
    List<MoveInterface> handleLoadGame(final Path source);

}
