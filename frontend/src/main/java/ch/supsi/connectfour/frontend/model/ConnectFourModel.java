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
import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.handler.*;
import ch.supsi.connectfour.frontend.contracts.observable.*;
import ch.supsi.connectfour.frontend.memento.Memento;
import ch.supsi.connectfour.frontend.memento.MementoCaretaker;
import ch.supsi.connectfour.frontend.presentable.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ConnectFourModel implements MakeMoveHandler, OKHandler, CancelHandler, ExitHandler ,OpenFileHandler, ExportFileHandler, MoveObservable, ColumnFullObservable, FeedbackObservable, GameHasAWinnerObservable, GameDrawObservable, ClearViewObservable, ExitObservable,PlayerInfoHandler, PlayerInfoObservable, SaveNewInfoHandler,SaveNewInfoObservable, RePaintObservable, NewGameHandler, UndoHandler, RedoHandler,ToggleUndoButtonObservable, ToggleRedoButtonObservable, UndoObservable, RedoObservable, NewGameObservable, FreeColumnObservable
{
    private static final int MAX_PLAYERS = 2;
    private GameBoardInterface board;
    private SwitchTurnController switchTurnController;
    private GameController gameController;
    private TranslationsController translationsController;
    private PreferencesController preferencesController;
    private GameStatusType status;
    private List<MoveInterface> moves;
    private MementoCaretaker<List<MoveInterface>> movesMementoCaretaker;

    private static ConnectFourModel myself;

    private boolean isChanged;

    protected ConnectFourModel(){
        this.board = GameBoard.getInstance();
        this.switchTurnController = SwitchTurnController.getInstance();
        this.translationsController = TranslationsController.getInstance();
        this.preferencesController = PreferencesController.getInstance();
        this.gameController = GameController.getInstance();
        this.status = GameStatusType.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.movesMementoCaretaker = new MementoCaretaker<>();
        movesMementoCaretaker.addState(new Memento<>(new ArrayList<>()));
        this.isChanged = false;
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
            loadFile(file.getPath());
            notifyFeedbackObservers(new OpenFilePresentable());
        }
    }

    @Override
    public void exportFile() {
        if(moves.isEmpty())
        {
            notifyFeedbackObservers(new EmptyListPresentable());
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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        notifyFeedbackObservers(new FileSavedPresentable());


    }

    private void loadFile(String path){
        try{
            this.moves.clear();
            notifyClearViewObservers();
            switchTurnController.reset();
            board.resetBoard();
            status = GameStatusType.IN_PROGRESS;

            enableSelectorButtons();

            List<MoveInterface> loadedMoves = gameController.loadGame(path);

            this.movesMementoCaretaker = new MementoCaretaker<>();
            movesMementoCaretaker.addState(new Memento<>(new ArrayList<>()));
            for (MoveInterface move : loadedMoves) {
                makeMove(move.getColumn());
            }

            movesMementoCaretaker.addState(new Memento<>(new ArrayList<>(moves)));

            enableSelectorButtons();

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
        isChanged = true;
        movesMementoCaretaker.addState(new Memento<>(new ArrayList<>(moves)));
        notifyMoveObservers(move);
        notifyFeedbackObservers(new PlayerMovePresentable(move));
        notifyUndoButtonObservers(false);
        notifyRedoButtonObservers(false);
        if(board.isColumnFull(column))
            notifyColumnFullObservers(column);

        // check for a winning move
        if(isWinningMove(move)){
            status = GameStatusType.WINNER;
            notifyGameHasAWinnerObservers(new PlayerWinPresentable(currentPlayer));
            notifyRedoButtonObservers(true);
            notifyUndoButtonObservers(true);

        }
        else if(board.isFull()){
            status = GameStatusType.DRAW;
            notifyGameDrawObservers(new GameDrawPresentable());
            notifyRedoButtonObservers(true);
            notifyUndoButtonObservers(true);

        }
        else //switch turn
            switchTurnController.switchTurn();


    }

    @Override
    public void exit() {
        if(this.isChanged){
            notifyExitObservers();
        }
        else{
            System.exit(0);
        }
    }

    @Override
    public void cancel(Stage toClose) {
        toClose.close();
    }

    @Override
    public void ok() {
        System.exit(0);
    }

    @Override
    public void playerInfo(final int position) {
        Player toWatch = switchTurnController.getPlayerAtIndex(position);
        notifyPlayerInfosObservers(position, toWatch);
    }

    @Override
    public void saveNewInfo(final int position,String newName, MySymbolInterface newSymbol) {
        Player toUpdate = switchTurnController.getPlayerAtIndex(position);
        String oldName = toUpdate.getName();

        //controllare che
        if(switchTurnController.nameAlreadyUsed(oldName,newName)){
            notifyFeedbackObservers(new NameAlreadyUsedPresentable());
            return;
        }
        if(switchTurnController.symbolAlreadyUsed(oldName, newSymbol)){
            notifyFeedbackObservers(new SymbolAlreadyUsedPresentable());
            return;
        }


        //Update the board view
        List<MoveInterface> playerMoves = moves.stream()
                .filter(move -> move.getPlayer().getName().equals(oldName))
                .collect(Collectors.toList());


        toUpdate.changeName(newName);
        toUpdate.changeSymbol(newSymbol);

        notifyRepaintObservers(playerMoves);
        notifySaveNewInfosObservers(position, newName, newSymbol);

    }

    @Override
    public void newGame() {
        switchTurnController.reset();
        board.resetBoard();
        this.status = GameStatusType.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.isChanged = false;
        this.movesMementoCaretaker = new MementoCaretaker<>();
        movesMementoCaretaker.addState(new Memento<>(new ArrayList<>()));
        notifyClearViewObservers();
        notifyNewGameObservers();
        notifyFeedbackObservers(new NewGamePresentable());
    }

    @Override
    public void undo() {
        Memento<List<MoveInterface>> previousState = movesMementoCaretaker.undo();
        if (previousState != null) {
            this.moves = new ArrayList<>(previousState.getState());
            board.setCells(moves);
            enableSelectorButtons();
            notifyClearViewObservers();
            notifyUndoObservers(moves);
            notifyRedoButtonObservers(!movesMementoCaretaker.canRedo()); // Enable redo if possible
            notifyUndoButtonObservers(!movesMementoCaretaker.canUndo()); // Disable undo if no more undos
        } else {
            notifyUndoButtonObservers(true); // Disable undo if at the beginning
        }
    }

    @Override
    public void redo() {
        Memento<List<MoveInterface>> nextState = movesMementoCaretaker.redo();
        if (nextState != null) {
            this.moves = new ArrayList<>(nextState.getState());
            board.setCells(moves);

            disableSelectorButtons();

            notifyClearViewObservers();
            notifyRedoObservers(moves);
            notifyUndoButtonObservers(!movesMementoCaretaker.canUndo()); // Enable undo if possible
            notifyRedoButtonObservers(!movesMementoCaretaker.canRedo()); // Disable redo if no more redos
        } else {
            notifyRedoButtonObservers(true); // Disable redo if at the end
        }
    }

    private void enableSelectorButtons(){
        for(int i=0; i<board.getWidth(); i++)
            if(!board.isColumnFull(i))
                notifyFreeColumnObservers(i);
    }

    private void disableSelectorButtons(){
        for(int i=0; i<board.getWidth(); i++)
            if(board.isColumnFull(i))
                notifyColumnFullObservers(i);
    }

}
