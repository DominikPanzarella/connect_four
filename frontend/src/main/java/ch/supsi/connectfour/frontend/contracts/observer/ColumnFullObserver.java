package ch.supsi.connectfour.frontend.contracts.observer;

public interface ColumnFullObserver extends Observer
{
    void disableColumn(final int column);
}
