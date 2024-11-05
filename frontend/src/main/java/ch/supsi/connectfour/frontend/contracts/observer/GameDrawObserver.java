package ch.supsi.connectfour.frontend.contracts.observer;

import ch.supsi.connectfour.frontend.presentable.Presentable;

public interface GameDrawObserver extends Observer
{
    void gameDraw(Presentable toPrint);
}
