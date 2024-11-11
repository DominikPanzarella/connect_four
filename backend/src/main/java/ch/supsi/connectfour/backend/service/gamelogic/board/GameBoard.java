package ch.supsi.connectfour.backend.service.gamelogic.board;

import ch.supsi.connectfour.backend.exceptions.PositionNotValidException;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;

import java.util.List;

public class GameBoard implements GameBoardInterface
{
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 7;
    private Player[][] board = new Player[NUM_ROWS][NUM_COLS];
    private static GameBoard mySelf;

    protected GameBoard(){

    }

    public static GameBoard getInstance()
    {
        if(mySelf==null)
        {
            mySelf = new GameBoard();
        }
        return mySelf;
    }

    @Override
    public void setCell(int row, int col, Player player) {
        checkPosition(row,col);
        board[row][col] = player;
    }

    @Override
    public void setCell(final MoveInterface move) {
        final int row       = move.getRow();
        final int col       = move.getColumn();
        final Player player = move.getPlayer();

        checkPosition(row,col);
        board[row][col] = player;
    }

    @Override
    public Player getPlayerAtCell(int row, int col) {
        checkPosition(row,col);
        return board[row][col];
    }

    @Override
    public Player[][] getBoard() {
        return board;
    }

    @Override
    public boolean isFull() {
        for(int row=0; row<NUM_ROWS; row++)
            for(int col=0; col<NUM_COLS; col++)
                if(board[row][col]==null)
                    return false;
        return true;
    }

    @Override
    public boolean isColumnFull(final int column){
        for(int row=NUM_ROWS-1; row>=0; --row){
            if(board[row][column] == null){
                return false; // Found an empty cell, column is not full
            }
        }
        return true; // All cells in the column are occupied, column is full
    }

    @Override
    public int findFirstAvailableRow(final int column){
        for (int row = board.length - 1; row >= 0; row--) {
            if (board[row][column] == null) {
                return row;
            }
        }
        // Column is full
        return -1;
    }

    @Override
    public void resetBoard() {
        board = new Player[NUM_ROWS][NUM_COLS];
    }

    @Override
    public void unsetCell(MoveInterface move) {
        final int row       = move.getRow();
        final int col       = move.getColumn();
        board[row][col] = null ;

    }

    @Override
    public void setCells(List<MoveInterface> moves) {
        resetBoard();
        for(MoveInterface move : moves)
        {
            final int row       = move.getRow();
            final int col       = move.getColumn();
            final Player player = move.getPlayer();

            board[row][col] = player;
        }
    }

    @Override
    public int getWidth() {
        return NUM_COLS;
    }

    @Override
    public int getHeight() {
        return NUM_ROWS ;
    }

    //Helper methode
    private static void checkPosition(int row, int col)
    {
        if(row<0 || row>NUM_ROWS-1 || col<0 || col>NUM_COLS-1)
            throw new PositionNotValidException();
    }


}
