package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.controller.GameController;
import ch.supsi.connectfour.backend.controller.PreferencesController;
import ch.supsi.connectfour.backend.controller.SwitchTurnController;
import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.exceptions.GameFullException;
import ch.supsi.connectfour.backend.exceptions.GameNotRunningException;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoard;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoardInterface;
import ch.supsi.connectfour.backend.service.gamelogic.game.GameStatusType;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;
import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;
import ch.supsi.connectfour.frontend.contracts.handler.ExportFileHandler;
import ch.supsi.connectfour.frontend.contracts.observable.*;
import ch.supsi.connectfour.frontend.presentable.GameDrawPresentable;
import ch.supsi.connectfour.frontend.presentable.PlayerMovePresentable;
import ch.supsi.connectfour.frontend.presentable.PlayerWinPresentable;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConnectFourModel implements MakeMoveHandler, OpenFileHandler, ExportFileHandler, MoveObservable, ColumnFullObservable, FeedbackObservable, GameHasAWinnerObservable, GameDrawObservable
{
    private static final int MAX_PLAYERS = 2;
    private GameBoardInterface board;
    private SwitchTurnController switchTurnController;
    private GameController gameController;
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
        this.gameController = GameController.getInstance();
        this.status = GameStatusType.IN_PROGRESS;
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

    @Override
    public void openFile(){
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(translationsController.translate("label.openTitle"));
        fileChooser.setInitialDirectory(preferencesController.getUserPreferencesDirectoryPath().toFile());

        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            loadImage(file.getPath());
            //TODO: notify feedback
        }
    }

    @Override
    public void exportFile() {
        if(moves.isEmpty())
        {
            //TODO: notify that the list is empty
            return;
        }

        final FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

        fileChooser.setTitle(translationsController.translate("label.exportTitle"));
        fileChooser.setInitialDirectory(preferencesController.getUserPreferencesDirectoryPath().toFile());

        File file = fileChooser.showSaveDialog(null);
        String[] fileName = file.toURI().getPath().split(File.separator);
        String[] fileNameAndExtension = fileName[fileName.length - 1].split("\\.");

        try{
            gameController.saveGame(file.toURI().getPath(), fileNameAndExtension[1], moves );
            //notifyFeedbackObservers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void loadImage(String path){
        try{
            List<MoveInterface> loadedMoves = gameController.loadGame(path);
            //TODO: adjust the moves with the corresponding player (name and colors can be changed)
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPlayer(Player player)
    {
        try{
            switchTurnController.addPlayer(player);
        }
        catch (GameFullException e)
        {
        }
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

    @Override
    public void makeMove(int column) {

        if(status != GameStatusType.IN_PROGRESS)
            throw new GameNotRunningException();


        int firstAvailableRow = board.findFirstAvailableRow(column);

        Player currentPlayer = switchTurnController.getCurrentPlayer();
        System.out.println(currentPlayer.getName());
        Move move = new Move(firstAvailableRow, column, currentPlayer);

        moves.add(move);
        board.setCell(move);
        notifyMoveObservers(move);

        notifyFeedbackObservers(new PlayerMovePresentable(move));

        if(board.isColumnFull(column))
            notifyColumnFullObservers(column);

        // check for a winning move
        if(isWinningMove(move)){
            status = GameStatusType.WINNER;
            notifyGameHasAWinnerObservers(new PlayerWinPresentable(currentPlayer));
        }
        else if(board.isFull()){
            status = GameStatusType.DRAW;
            notifyGameDrawObservers(new GameDrawPresentable());

        }
        else //switch turn
            switchTurnController.switchTurn();


    }

}
