package ch.supsi.connectfour.backend.service.gamelogic.game;

import ch.supsi.connectfour.backend.controller.GameServiceInterface;
import ch.supsi.connectfour.backend.exceptions.GameNotRunningException;
import ch.supsi.connectfour.backend.exceptions.NotYourTurnException;
import ch.supsi.connectfour.backend.repository.GameRepository;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoard;
import ch.supsi.connectfour.backend.service.gamelogic.board.GameBoardInterface;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.backend.controller.SwitchTurnServiceInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.SwitchTurnService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GameService implements GameServiceInterface
{
    private static final int MAX_PLAYERS = 2;
    /*private GameBoardInterface board;
    private final SwitchTurnServiceInterface switchTurnService;
    private GameStatusType status;
    private final List<MoveInterface> moves;
    private final List<Player> playerList;*/

    private GameRepositoryInterface gameRepository;
    private static GameService myself;

    protected GameService(GameBoardInterface board){

        this.gameRepository = GameRepository.getInstance();
    }

    public static GameService getInstance(){

        if(myself==null)
        {
            myself = new GameService(GameBoard.getInstance());
        }
        return myself;
    }

    @Override
    public boolean saveGame(Path destination) {
        //TODO: to be implemented

        return true;
    }

    @Override
    public List<MoveInterface> loadGame(Path source) {
        //TODO: to be implemented
        return null;
    }

    /*
    public void play(MoveInterface move, boolean b) {
        System.out.println("Game::play()");

        //is the game in progress?
        if(status != GameStatusType.IN_PROGRESS)
            throw new GameNotRunningException();

        //is you turn?

        if(move.getPlayer() != switchTurnService.getCurrentPlayer())
            throw new NotYourTurnException();

        //save the move

        moves.add(move);
        board.setCell(move);

        //notify other listener
        //events.dispatch(GameEventType.PLAYER_MOVED, move);
        // check for a winning move
        if(isWinningMove(move)){
            status = GameStatusType.WINNER;
            //events.dispatch(GameEventType.GAME_ENDED, move.getPlayer());
        }       //check for a draw
        else if(board.isFull()){
            status = GameStatusType.DRAW;
            //events.dispatch(GameEventType.GAME_ENDED, null);
        }
        else{
            //switch turn
            switchTurnService.switchTurn(!b);
        }

    }

    public void start() {
        status = GameStatusType.IN_PROGRESS;
        //events.dispatch(GameEventType.GAME_STARTED, null);
        //playerSwitcher.getCurrentPlayer().onMyTurn();
    }

    public void startNewGame() {
        //events.dispatch(GameEventType.GAME_STARTED, null);
        switchTurnService.reset();
        board.resetBoard();
        moves.clear();
    }

    public void playInColumn(int column) {
        final int row = board.findFirstAvailableRow(column);
        System.out.println("GameController Backend: column ["+row+","+column+"]");
        Player currentPlayer = switchTurnService.getCurrentPlayer();
        currentPlayer.play(row, column);
        if(board.isColumnFull(column)){
            // game.getEvents().dispatch(GameEventType.COLUMN_FULL, column);
        }

    }

    public void addPlayer(Player player) {
        switchTurnService.addPlayer(player);
        player.setGame(this);
    }



     */

}
