package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.OpenFileReceiver;


public class MenuBarController implements OpenFileReceiver<OpenFileHandler>
{
    private OpenFileHandler openFileModel;

    private static MenuBarController myself;

    protected MenuBarController(OpenFileHandler openFileModel) {
        this.openFileModel = openFileModel;

    }

    public static MenuBarController getInstance(OpenFileHandler openFileModel) {
        if (myself == null) {
            myself = new MenuBarController(openFileModel);
        }
        return myself;
    }

    @Override
    public void openFile() {
        openFileModel.openFile();
    }

}
