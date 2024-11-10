package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.contracts.handler.*;
import ch.supsi.connectfour.frontend.contracts.receiver.*;
import ch.supsi.connectfour.frontend.model.ConnectFourModel;
import javafx.stage.Stage;


public class MenuBarController implements
        OpenFileReceiver<OpenFileHandler>,
        ExportFileReceiver<ExportFileHandler>,
        ExitReceiver<ExitHandler>,
        CancelReceiver<CancelHandler>,
        OkReceiver<OKHandler>,
        ChangeLanguageReceiver<ChangeLanguageHandler>
{
    private OpenFileHandler openFileModel;
    private ExportFileHandler exportModel;
    private ExitHandler exitModel;
    private OKHandler okModel;
    private CancelHandler cancelModel;
    private ChangeLanguageHandler changeLanguageModel;

    private static MenuBarController myself;

    protected MenuBarController(OpenFileHandler openFileModel, ExportFileHandler exportModel,ChangeLanguageHandler changeLanguageModel) {
        this.openFileModel = openFileModel;
        this.exportModel = exportModel;
        this.exitModel = ConnectFourModel.getInstance();
        this.cancelModel = ConnectFourModel.getInstance();
        this.okModel = ConnectFourModel.getInstance();
        this.changeLanguageModel = changeLanguageModel;

    }

    public static MenuBarController getInstance(OpenFileHandler openFileModel,ExportFileHandler exportModel,ChangeLanguageHandler changeLanguageModel) {
        if (myself == null) {
            myself = new MenuBarController(openFileModel,exportModel,changeLanguageModel);
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

    @Override
    public void exit() {
        exitModel.exit();
    }

    @Override
    public void cancel(Stage toClose) {
        cancelModel.cancel(toClose);
    }


    @Override
    public void ok() {
        okModel.ok();
    }

    @Override
    public void changeLanguage(String languageTag) {
        changeLanguageModel.changeLanguage(languageTag);
    }
}
