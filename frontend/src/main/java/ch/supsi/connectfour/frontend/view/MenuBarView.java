package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.command.*;
import ch.supsi.connectfour.frontend.contracts.handler.*;
import ch.supsi.connectfour.frontend.contracts.receiver.*;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledViewFxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarView implements ControlledViewFxml
{
    @FXML
    Menu fileMenu;
    @FXML
    MenuItem newMenuItem;
    @FXML
    MenuItem openMenuItem;
    @FXML
    MenuItem saveMenuItem;
    @FXML
    MenuItem saveAsMenuItem;
    @FXML
    MenuItem quitMenuItem;

    @FXML
    Menu editMenu;
    @FXML
    Menu changeLanguageMenu;
    @FXML
    MenuItem enUSMenuItem;
    @FXML
    MenuItem itCHMenuItem;

    @FXML
    MenuItem frFRMenuItem;
    @FXML
    MenuItem deDEMenuItem;
    @FXML
    MenuItem changeSymbol;

    @FXML
    Menu helpMenu;
    @FXML
    MenuItem aboutMenuItem;
    @FXML
    MenuItem helpMenuItem;


    private static Parent parent;
    private static MenuBarView myself;

    protected MenuBarView()
    {
        //empty constructor
    }

    public static MenuBarView getInstance(){
        if(myself == null){
            myself = new MenuBarView();
            try{
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = MenuBarView.class.getResource("/view/menubar.fxml");
                if(fxmlurl != null){
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }

    @Override
    public void initialize() {
        //saveAsMenuItem.setDisable(true);
        //saveMenuItem.setDisable(true);

    }

    @Override
    public Parent getParent() {
        return parent;
    }

    public <T extends OpenFileCommand<? extends OpenFileReceiver<OpenFileHandler>>> void createOpenMenuItemBehaviour(T command){
        openMenuItem.setOnAction(action->command.execute());
    }

    public <T extends ExportFileCommand<? extends ExportFileReceiver<ExportFileHandler>>> void createExportFileBehaviour(T command){
        saveAsMenuItem.setOnAction(action->command.execute());
    }

    public <T extends NewGameCommand<? extends NewGameReceiver<NewGameHandler>>> void createNewGameBehaviour(T command){
        newMenuItem.setOnAction(action->command.execute());
    }

    public <T extends ExitCommand<? extends ExitReceiver<ExitHandler>>> void createExitBehaviour(T command){
        quitMenuItem.setOnAction(action->command.execute());
    }

    public <T extends AboutCommand<? extends AboutReceiver<AboutHandler>>> void createAboutBehavior(T command) {
        aboutMenuItem.setOnAction(action -> command.execute());
    }

    public <T extends ChangeLanguageCommand<? extends ChangeLanguageReceiver<ChangeLanguageHandler>>> void createEnUSMenuItemBehaviour(T command){
        enUSMenuItem.setOnAction(action->command.execute());
    }

    public <T extends ChangeLanguageCommand<? extends ChangeLanguageReceiver<ChangeLanguageHandler>>> void createItCHMenuItemBehaviour(T command){
        itCHMenuItem.setOnAction(action->command.execute());
    }

    public <T extends ChangeLanguageCommand<? extends ChangeLanguageReceiver<ChangeLanguageHandler>>> void createFrFRMenuItemBehaviour(T command){
        frFRMenuItem.setOnAction(action->command.execute());
    }

    public <T extends ChangeLanguageCommand<? extends ChangeLanguageReceiver<ChangeLanguageHandler>>> void createDeDEMenuItemBehaviour(T command){
        deDEMenuItem.setOnAction(action->command.execute());
    }


}
