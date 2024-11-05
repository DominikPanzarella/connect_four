package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.observer.GameHasAWinnerObserver;
import ch.supsi.connectfour.frontend.presentable.Presentable;

import java.util.ArrayList;
import java.util.List;

public interface GameHasAWinnerObservable extends Observable
{
    List<GameHasAWinnerObserver> observers = new ArrayList<>();

    default void addGameHasAWinnerObserver(GameHasAWinnerObserver observer) {
        observers.add(observer);
    }

    default void removeGameHasAWinnerObserver(GameHasAWinnerObserver observer) {
        observers.remove(observer);
    }

    default void notifyGameHasAWinnerObservers(final Presentable winnerPlayer) {
        for (GameHasAWinnerObserver observer : observers) {
            observer.gameHasAWinner(winnerPlayer);
        }
    }
}
