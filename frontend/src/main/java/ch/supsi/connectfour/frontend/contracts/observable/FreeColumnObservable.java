package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.frontend.contracts.observer.FreeColumnObserver;

import java.util.ArrayList;
import java.util.List;

public interface FreeColumnObservable extends Observable
{
    List<FreeColumnObserver> observers = new ArrayList<>();

    default void addFreeColumnObserver(FreeColumnObserver observer) {
        observers.add(observer);
    }

    default void removeFreeColumnObserver(FreeColumnObserver observer) {
        observers.remove(observer);
    }

    default void notifyFreeColumnObservers(final int toFree) {
        for (FreeColumnObserver observer : observers) {
            observer.freeColumn(toFree);
        }
    }
}
