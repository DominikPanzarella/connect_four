package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.frontend.contracts.observer.GameDrawObserver;
import ch.supsi.connectfour.frontend.contracts.observer.MoveObserver;
import ch.supsi.connectfour.frontend.presentable.Presentable;

import java.util.ArrayList;
import java.util.List;

public interface GameDrawObservable extends Observable
{
    List<GameDrawObserver> observers = new ArrayList<>();

    default void addGameDrawObserver(GameDrawObserver observer) {
        observers.add(observer);
    }

    default void removeGameDrawObserver(GameDrawObserver observer) {
        observers.remove(observer);
    }

    default void notifyGameDrawObservers(Presentable toPrint) {
        for (GameDrawObserver observer : observers) {
            observer.gameDraw(toPrint);
        }
    }

}
