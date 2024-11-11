package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.contracts.handler.RedoHandler;
import ch.supsi.connectfour.frontend.contracts.handler.UndoHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.RedoReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.UndoReceiver;

public class PlayerBarController implements UndoReceiver<UndoHandler>, RedoReceiver<RedoHandler>
{
    private UndoHandler undoHandler;
    private RedoHandler redoHandler;
    private static PlayerBarController myself;

    protected PlayerBarController(UndoHandler undoHandler, RedoHandler redoHandler) {

        this.undoHandler = undoHandler;
        this.redoHandler = redoHandler;
    }

    public static PlayerBarController getInstance( UndoHandler undoHandler, RedoHandler redoHandler) {
        if (myself == null) {
            myself = new PlayerBarController(undoHandler,redoHandler);
        }
        return myself;
    }

    @Override
    public void redo() {
        redoHandler.redo();
    }

    @Override
    public void undo() {
        undoHandler.undo();
    }
}
