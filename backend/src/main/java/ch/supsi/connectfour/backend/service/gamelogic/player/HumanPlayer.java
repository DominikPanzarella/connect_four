package ch.supsi.connectfour.backend.service.gamelogic.player;

public class HumanPlayer extends Player implements OnMyTurnInterface
{

    public HumanPlayer(final String name,MySymbolInterface symbol)
    {
        super(name,symbol);
    }
    @Override
    public void onMyTurn() {
    }
}
