package ch.supsi.connectfour.frontend.contracts.observable;


import ch.supsi.connectfour.frontend.contracts.observer.ToggleRedoButtonObserver;

import java.util.ArrayList;
import java.util.List;

public interface ToggleRedoButtonObservable {
    List<ToggleRedoButtonObserver> observers = new ArrayList<>();

    default void addORedoButtonObserver(ToggleRedoButtonObserver observer) {
        observers.add(observer);
    }

    default void removeRedoButtonObserver(ToggleRedoButtonObserver observer) {
        observers.remove(observer);
    }

    default void notifyRedoButtonObservers(boolean state) {
        for (ToggleRedoButtonObserver observer : observers) {
            observer.toggleRedoButton(state);
        }
    }
}
