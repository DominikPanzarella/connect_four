package ch.supsi.connectfour.backend.repository.reader;

import ch.supsi.connectfour.backend.exceptions.FileReadingException;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.util.List;

public interface Reader
{
    List<MoveInterface> read(final String path) throws FileReadingException;

}
