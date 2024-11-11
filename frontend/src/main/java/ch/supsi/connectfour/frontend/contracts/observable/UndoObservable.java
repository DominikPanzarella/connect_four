package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.frontend.contracts.observer.UndoObserver;

import java.util.ArrayList;
import java.util.List;

public interface UndoObservable extends Observable{
    List<UndoObserver> observers = new ArrayList<>();

    default void addUndoObserver(UndoObserver observer) {
        observers.add(observer);
    }

    default void removeUndoObserver(UndoObserver observer) {
        observers.remove(observer);
    }

    default void notifyUndoObservers(List<MoveInterface> data) {
        for (UndoObserver observer : observers) {
            observer.undo(data);
        }
    }
}
