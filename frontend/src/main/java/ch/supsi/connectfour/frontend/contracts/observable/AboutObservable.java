package ch.supsi.connectfour.frontend.contracts.observable;


import ch.supsi.connectfour.frontend.contracts.observer.AboutObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AboutObservable extends Observable
{
    List<AboutObserver> observers = new ArrayList<>();

    default public void addAboutObserver(AboutObserver observer) {
        observers.add(observer);
    }

    default void removeAboutObserver(AboutObserver observer) {
        observers.remove(observer);
    }

    default void notifyAboutObservers(List<String> infos, Map<String, String> developers) {
        for (AboutObserver observer : observers) {
            observer.about(infos, developers);
        }
    }
}
