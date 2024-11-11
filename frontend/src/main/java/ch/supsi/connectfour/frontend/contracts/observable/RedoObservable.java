package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.frontend.contracts.observer.RedoObserver;

import java.util.ArrayList;
import java.util.List;

public interface RedoObservable extends Observable
{
    List<RedoObserver> observers = new ArrayList<>();

    default void addRedoObserver(RedoObserver observer) {
        observers.add(observer);
    }

    default void removeRedoObserver(RedoObserver observer) {
        observers.remove(observer);
    }

    default void notifyRedoObservers(List<MoveInterface> data) {
        for (RedoObserver observer : observers) {
            observer.redo(data);
        }
    }
}
