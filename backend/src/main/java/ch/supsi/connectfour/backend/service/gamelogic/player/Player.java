package ch.supsi.connectfour.backend.service.gamelogic.player;

import ch.supsi.connectfour.backend.controller.GameServiceInterface;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;

public abstract class Player implements OnMyTurnInterface
{
    private GameServiceInterface gameService;
    private String name;

    public Player() {
        name = "unknown";
    }

    public Player(final String name){
        this.name = name;
    }

    public void changeName(final String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setGame(GameServiceInterface gameService) {
        this.gameService = gameService;
    }

    public GameServiceInterface getGame() {
        return gameService;
    }

    public void play(final int row,final int col){
        final Move move = new Move(row,col,this);
        //gameService.play(move,true);

    }

    @Override
    public String toString() {
        return "Player{game=" + gameService + '}';
    }
}
