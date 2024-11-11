package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.frontend.contracts.observer.ColumnFullObserver;
import ch.supsi.connectfour.frontend.contracts.observer.MoveObserver;

import java.util.ArrayList;
import java.util.List;

public interface ColumnFullObservable extends Observable
{
    List<ColumnFullObserver> observers = new ArrayList<>();

    default void addColumnFullObserver(ColumnFullObserver observer) {
        observers.add(observer);
    }

    default void removeColumnFullObserver(ColumnFullObserver observer) {
        observers.remove(observer);
    }

    default void notifyColumnFullObservers(final int column) {
        for (ColumnFullObserver observer : observers) {
            observer.disableColumn(column);
        }
    }

}
