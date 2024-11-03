package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.controller.PreferencesController;
import ch.supsi.connectfour.backend.controller.SwitchTurnController;
import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.exceptions.GameFullException;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoard;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoardInterface;
import ch.supsi.connectfour.backend.service.gamelogic.game.GameStatusType;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;
import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConnectFourModel implements MakeMoveHandler, OpenFileHandler
{
    private static final int MAX_PLAYERS = 2;
    private GameBoardInterface board;
    private SwitchTurnController switchTurnController;
    private TranslationsController translationsController;
    private PreferencesController preferencesController;
    private GameStatusType status;
    private final List<MoveInterface> moves;

    private static ConnectFourModel myself;

    protected ConnectFourModel(){
        this.board = GameBoard.getInstance();
        this.switchTurnController = SwitchTurnController.getInstance();
        this.translationsController = TranslationsController.getInstance();
        this.preferencesController = PreferencesController.getInstance();
        this.status = GameStatusType.NOT_STARTED;
        this.moves = new ArrayList<>();
    }

    public static ConnectFourModel getInstance()
    {
        if(myself==null)
        {

            myself = new ConnectFourModel();
        }
        return myself;
    }

    public void openFile(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(translationsController.translate("label.openTitle"));
        fileChooser.setInitialDirectory(preferencesController.getUserPreferencesDirectoryPath().toFile());

        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            loadImage(file.getPath());
        }
    }

    private void loadImage(String path){
        System.out.println("loadImage()");
    }

    public void addPlayer(Player player)
    {
        try{
            switchTurnController.addPlayer(player);
        }
        catch (GameFullException e)
        {
            //TODO: handle exception
        }

    }


    @Override
    public void makeMove() {
        board.findFirstAvailableRow(0);
    }

    private boolean isWinningMove(MoveInterface move)
    {
        Player player = move.getPlayer();
        int row = move.getRow();
        int col = move.getColumn();
        Player[][] cells = board.getBoard();
        int boardHeight = cells.length;
        int boardWidth = cells[0].length;

        // Define directions for checking winning patterns
        int[][] directions = {{1, 0}, {0, 1}, {1, 1}, {-1, 1}};

        for (int[] dir : directions) {
            int count = 1; // Count of consecutive pieces in a direction
            // Explore both directions from the current move
            for (int mult : new int[]{-1, 1}) {
                for (int i = 1; i <= 3; i++) {
                    int r = row + dir[0] * i * mult;
                    int c = col + dir[1] * i * mult;
                    if (r < 0 || r >= boardHeight || c < 0 || c >= boardWidth || cells[r][c] != player) {
                        break;
                    }
                    count++;
                }
            }
            if (count >= 4) {
                return true; // Found 4 consecutive pieces in this direction
            }
        }
        return false;
    }
}
