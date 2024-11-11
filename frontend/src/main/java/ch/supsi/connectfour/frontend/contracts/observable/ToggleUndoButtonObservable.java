package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.frontend.contracts.observer.ToggleUndoButtonObserver;

import java.util.ArrayList;
import java.util.List;

public interface ToggleUndoButtonObservable {
    List<ToggleUndoButtonObserver> observers = new ArrayList<>();

    default void addUndoButtonObserver(ToggleUndoButtonObserver observer) {
        observers.add(observer);
    }

    default void removeUndoButtonObserver(ToggleUndoButtonObserver observer) {
        observers.remove(observer);
    }

    default void notifyUndoButtonObservers(boolean state) {
        for (ToggleUndoButtonObserver observer : observers) {
            observer.toggleUndoButton(state);
        }
    }
}
