package ch.supsi.connectfour.backend.repository.reader;

import ch.supsi.connectfour.backend.exceptions.FileReadingException;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.HumanPlayer;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONReader implements Reader
{
    @Override
    public List<MoveInterface> read(String path) throws FileReadingException {
        List<MoveInterface> moves = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(path)) {

            JSONArray jsonArray = (JSONArray) jsonParser.parse(reader);


            for (Object obj : jsonArray) {
                JSONObject jsonObject = (JSONObject) obj;

                int row = ((Long) jsonObject.get("row")).intValue();
                int column = ((Long) jsonObject.get("column")).intValue();

                JSONObject playerObject = (JSONObject) jsonObject.get("player");
                String playerName = (String) playerObject.get("name");


                Player player = new HumanPlayer(playerName, null);
                MoveInterface move = new Move(row, column, player);
                moves.add(move);
            }
        } catch (IOException | ParseException e) {
            throw new FileReadingException("Error reading JSON file: " + e.getMessage());
        }

        return moves;
    }
}
