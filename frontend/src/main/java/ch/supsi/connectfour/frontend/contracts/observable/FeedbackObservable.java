package ch.supsi.connectfour.frontend.contracts.observable;


import ch.supsi.connectfour.frontend.contracts.observer.FeedbackObserver;
import ch.supsi.connectfour.frontend.presentable.Presentable;

import java.util.ArrayList;
import java.util.List;

public interface FeedbackObservable extends Observable{
    List<FeedbackObserver> observers = new ArrayList<>();

    default public void addObserver(FeedbackObserver observer) {
        observers.add(observer);
    }

    default void removeObserver(FeedbackObserver observer) {
        observers.remove(observer);
    }

    default void notifyFeedbackObservers(Presentable feedback) {
        for (FeedbackObserver observer : observers) {
            observer.updateFeedback(feedback);
        }
    }
}
