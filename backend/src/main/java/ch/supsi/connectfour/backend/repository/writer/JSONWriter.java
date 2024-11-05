package ch.supsi.connectfour.backend.repository.writer;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONWriter implements Writer
{
    @Override
    public boolean write(String path, String extension, final List<MoveInterface> moves) throws IOException {
            if (!"json".equalsIgnoreCase(extension)) {
                throw new IllegalArgumentException("Unsupported file extension: " + extension);
            }

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer = new FileWriter(path + "." + extension)) {
                gson.toJson(moves, writer);
                return true;
            } catch (IOException e) {
                System.err.println("Failed to write JSON file: " + e.getMessage());
                return false;
            }
    }
}
