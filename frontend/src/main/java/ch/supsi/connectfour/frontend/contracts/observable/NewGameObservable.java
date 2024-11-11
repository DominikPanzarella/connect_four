package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.observer.AboutObserver;
import ch.supsi.connectfour.frontend.contracts.observer.NewGameObserver;
import ch.supsi.connectfour.frontend.contracts.observer.PlayerInfoObserver;

import java.util.ArrayList;
import java.util.List;

public interface NewGameObservable extends Observable
{
    List<NewGameObserver> observers = new ArrayList<>();

    default void addNewGameObserver(NewGameObserver observer) {
        observers.add(observer);
    }

    default void removeNewGameObserver(NewGameObserver observer) {
        observers.remove(observer);
    }

    default void notifyNewGameObservers() {
        for (NewGameObserver observer : observers) {
            observer.newGame();
        }
    }
}
