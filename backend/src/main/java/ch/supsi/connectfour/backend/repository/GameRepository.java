package ch.supsi.connectfour.backend.repository;

import ch.supsi.connectfour.backend.exceptions.FileReadingException;
import ch.supsi.connectfour.backend.repository.reader.JSONReader;
import ch.supsi.connectfour.backend.repository.reader.Reader;
import ch.supsi.connectfour.backend.repository.writer.JSONWriter;
import ch.supsi.connectfour.backend.repository.writer.Writer;
import ch.supsi.connectfour.backend.service.gamelogic.game.GameRepositoryInterface;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameRepository implements GameRepositoryInterface
{

    private static final Set<String> SUPPORTED_EXTENSIONS = new HashSet<>(Set.of("JSON"));
    private static GameRepository myself;
    private final Writer writer;
    private final Reader reader;

    protected GameRepository(){
        writer = new JSONWriter();
        reader = new JSONReader();
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
    public boolean handleSaveGame(String destination, String extension, final List<MoveInterface> moves) throws IOException {
        System.out.println("SaveGame: " +destination + " -- " + extension);
        if(!SUPPORTED_EXTENSIONS.contains(extension.toUpperCase()))
            throw new FileReadingException("Invalid file extension");
        return writer.write(destination,extension,moves);
    }

    @Override
    public List<MoveInterface> handleLoadGame(String source, String extension) {
        System.out.println("LoadGame: " + source + " -- " + extension);
        return reader.read(source);
    }
}
