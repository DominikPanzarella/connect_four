package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.contracts.observer.AboutObserver;
import ch.supsi.connectfour.frontend.contracts.observer.PlayerInfoObserver;
import ch.supsi.connectfour.frontend.contracts.observer.RePaintObserver;

import java.util.ArrayList;
import java.util.List;

public interface RePaintObservable extends Observable
{
    List<RePaintObserver> observers = new ArrayList<>();

    default public void addRepaintObserver(RePaintObserver observer) {
        observers.add(observer);
    }

    default void removeRepaintObserver(AboutObserver observer) {
        observers.remove(observer);
    }

    default void notifyRepaintObservers(List<MoveInterface> toUpdate) {
        for (RePaintObserver observer : observers) {
            observer.repaint(toUpdate);
        }
    }
}
