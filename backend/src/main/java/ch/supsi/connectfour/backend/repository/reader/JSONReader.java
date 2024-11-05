package ch.supsi.connectfour.backend.repository.reader;

import ch.supsi.connectfour.backend.exceptions.FileReadingException;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.util.List;

public class JSONReader implements Reader
{
    @Override
    public List<MoveInterface> read(String path) throws FileReadingException {
        return null;
    }
}
