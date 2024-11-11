package ch.supsi.connectfour.backend.service.gamelogic.player;

import ch.supsi.connectfour.backend.controller.GameServiceInterface;
import ch.supsi.connectfour.backend.service.gamelogic.move.Move;

public abstract class Player implements OnMyTurnInterface
{
    private GameServiceInterface gameService;
    private String name;
    private MySymbolInterface symbol;

    public Player() {
        name = "unknown";
    }

    public Player(final String name, MySymbolInterface symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public void changeName(final String name){
        this.name = name;
    }

    public void changeSymbol(MySymbolInterface newSymbol){
        this.symbol = newSymbol;
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


    public Character getPlayerCharacter(){
        return symbol.getCharacter();
    }

    public MyColorInterface getPlayerColors(){
        return symbol.getColor();
    }



    @Override
    public String toString() {
        return "Player{game=" + gameService + '}';
    }
}
