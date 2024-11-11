package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;
import ch.supsi.connectfour.frontend.contracts.observer.MoveObserver;

import java.util.ArrayList;
import java.util.List;

public interface MoveObservable extends Observable
{
    List<MoveObserver> observers = new ArrayList<>();

    default void addMoveObserver(MoveObserver observer) {
        observers.add(observer);
    }

    default void removeObserver(MoveObserver observer) {
        observers.remove(observer);
    }

    default void notifyMoveObservers(final Move move) {
        for (MoveObserver observer : observers) {
            observer.makeMove(move);
        }
    }
}
