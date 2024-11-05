package ch.supsi.connectfour.frontend.contracts.observer;

import ch.supsi.connectfour.backend.service.gamelogic.move.Move;

public interface MoveObserver extends Observer
{
    void makeMove(final Move move);
}
