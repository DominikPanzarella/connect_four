package ch.supsi.connectfour.frontend.contracts.observer;

import ch.supsi.connectfour.frontend.presentable.Presentable;

public interface FeedbackObserver {
    void updateFeedback(Presentable feedback);
}
