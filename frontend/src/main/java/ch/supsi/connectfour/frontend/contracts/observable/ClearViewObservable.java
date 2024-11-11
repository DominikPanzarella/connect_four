package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.frontend.contracts.observer.ClearViewObserver;
import ch.supsi.connectfour.frontend.contracts.observer.ColumnFullObserver;

import java.util.ArrayList;
import java.util.List;

public interface ClearViewObservable extends Observable
{
    List<ClearViewObserver> observers = new ArrayList<>();

    default void addClearViewObserver(ClearViewObserver observer) {
        observers.add(observer);
    }

    default void removeClearViewObserver(ClearViewObserver observer) {
        observers.remove(observer);
    }

    default void notifyClearViewObservers() {
        for (ClearViewObserver observer : observers) {
            observer.clearView();
        }
    }
}
