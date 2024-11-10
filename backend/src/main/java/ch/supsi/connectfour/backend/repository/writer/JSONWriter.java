package ch.supsi.connectfour.backend.repository.writer;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

        JSONArray jsonArray = new JSONArray();

        for (MoveInterface move : moves) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("row", move.getRow());
            jsonObject.put("column", move.getColumn());

            JSONObject playerObject = new JSONObject();
            playerObject.put("name", move.getPlayer().getName());

            jsonObject.put("player", playerObject);

            jsonArray.add(jsonObject);
        }

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(jsonArray.toJSONString());
            return true;
        } catch (IOException e) {
            System.err.println("Failed to write JSON file: " + e.getMessage());
            return false;
        }
    }
}
