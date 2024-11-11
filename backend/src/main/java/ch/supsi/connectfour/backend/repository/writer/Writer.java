package ch.supsi.connectfour.backend.repository.writer;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.io.IOException;
import java.util.List;

public interface Writer
{
    boolean write(final String path, String extension, final List<MoveInterface> moves) throws IOException;
}
