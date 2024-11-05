package ch.supsi.connectfour.backend.service.gamelogic.player;


public class MySymbol implements MySymbolInterface
{
    private Character character;
    private MyColorInterface myColorInterface;

    public MySymbol(char symbol, MyColorInterface myColorInterface)
    {
        this.character = symbol;
        this.myColorInterface = myColorInterface;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character symbol) {
        this.character = symbol;
    }

    public MyColorInterface getColor() {
        return myColorInterface;
    }

    public void setColor(MyColorInterface myColorInterface) {
        this.myColorInterface = myColorInterface;
    }
}
