package ch.supsi.connectfour.frontend.contracts.observer;

import ch.supsi.connectfour.frontend.presentable.Presentable;

public interface GameHasAWinnerObserver extends Observer
{
    void gameHasAWinner(Presentable winnerPlayer);
}
