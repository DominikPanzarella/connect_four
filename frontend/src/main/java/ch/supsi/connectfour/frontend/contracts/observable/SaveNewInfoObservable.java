package ch.supsi.connectfour.frontend.contracts.observable;

import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.frontend.contracts.observer.SaveNewInfoObserver;

import java.util.ArrayList;
import java.util.List;

public interface SaveNewInfoObservable extends Observable
{
    List<SaveNewInfoObserver> observers = new ArrayList<>();

    default void addSaveNewInfoObserver(SaveNewInfoObserver observer) {
        observers.add(observer);
    }

    default void removeSaveNewInfoObserver(SaveNewInfoObserver observer) {
        observers.remove(observer);
    }

    default void notifySaveNewInfosObservers(final int position,final String newName, final MySymbolInterface newSymbol) {
        for (SaveNewInfoObserver observer : observers) {
            observer.saveNewInfo(position,newName, newSymbol);
        }
    }
}
