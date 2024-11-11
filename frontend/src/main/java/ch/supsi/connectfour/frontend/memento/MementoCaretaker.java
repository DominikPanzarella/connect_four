package ch.supsi.connectfour.frontend.memento;

import java.util.LinkedList;

public class MementoCaretaker<T> {
    private LinkedList<Memento<T>> mementoStackState;
    private int current;

    public MementoCaretaker() {
        this.mementoStackState = new LinkedList<>();
        current = -1;
    }

    public void addState(Memento<T> newState) {
        // Clear future states if adding a new state after an undo
        while (mementoStackState.size() > current + 1) {
            mementoStackState.removeLast();
        }
        mementoStackState.add(newState);
        current++;
    }

    public Memento<T> undo() {
        if (canUndo()) {
            current--;
            return mementoStackState.get(current);
        }
        return null;
    }

    public boolean canUndo() {
        return current > 0;
    }

    public Memento<T> redo() {
        if (canRedo()) {
            current++;
            return mementoStackState.get(current);
        }
        return null;
    }

    public boolean canRedo() {
        return current < (mementoStackState.size() - 1);
    }

    public void printCurrentMemento() {
        System.out.println("Current State Index: " + current + ", Total States: " + mementoStackState.size());
    }
}

