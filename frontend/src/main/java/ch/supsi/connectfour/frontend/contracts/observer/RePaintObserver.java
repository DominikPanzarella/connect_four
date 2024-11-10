package ch.supsi.connectfour.frontend.contracts.observer;

import ch.supsi.connectfour.backend.service.gamelogic.move.MoveInterface;

import java.util.List;

public interface RePaintObserver extends Observer
{
    void repaint(List<MoveInterface> moves);
}
