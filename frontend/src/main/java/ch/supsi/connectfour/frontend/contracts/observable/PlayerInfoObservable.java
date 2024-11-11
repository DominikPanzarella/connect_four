package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.observer.AboutObserver;
import ch.supsi.connectfour.frontend.contracts.observer.PlayerInfoObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PlayerInfoObservable extends Observable
{
    List<PlayerInfoObserver> observers = new ArrayList<>();

    default public void addPlayerInfoObserver(PlayerInfoObserver observer) {
        observers.add(observer);
    }

    default void removeAboutObserver(AboutObserver observer) {
        observers.remove(observer);
    }

    default void notifyPlayerInfosObservers(final int position,Player player) {
        for (PlayerInfoObserver observer : observers) {
            observer.playerInfo(position,player);
        }
    }
}
