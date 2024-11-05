package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.contracts.handler.ExportFileHandler;
import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.ExportFileReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.OpenFileReceiver;


public class MenuBarController implements
        OpenFileReceiver<OpenFileHandler>,
        ExportFileReceiver<ExportFileHandler>
{
    private OpenFileHandler openFileModel;
    private ExportFileHandler exportModel;

    private static MenuBarController myself;

    protected MenuBarController(OpenFileHandler openFileModel, ExportFileHandler exportModel) {
        this.openFileModel = openFileModel;
        this.exportModel = exportModel;

    }

    public static MenuBarController getInstance(OpenFileHandler openFileModel,ExportFileHandler exportModel) {
        if (myself == null) {
            myself = new MenuBarController(openFileModel,exportModel);
        }
        return myself;
    }

    @Override
    public void openFile() {
        openFileModel.openFile();
    }

    @Override
    public void exportFile() {
        exportModel.exportFile();
    }
}
