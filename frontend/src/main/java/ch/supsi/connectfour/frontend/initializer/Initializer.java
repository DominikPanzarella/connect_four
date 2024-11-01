package ch.supsi.connectfour.frontend.initializer;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.model.PropertiesModel;
import ch.supsi.connectfour.frontend.view.AboutView;
import ch.supsi.connectfour.frontend.view.GameBoardView;
import ch.supsi.connectfour.frontend.view.InfoBarView;
import ch.supsi.connectfour.frontend.view.MenuBarView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Initializer
{

    public static void init(Stage stage) throws IOException {

        TranslationsController translationsController = TranslationsController.getInstance();
       /*
            ###################################
                Loading all fxml files
            ###################################
        */

        // MENUBAR
        Parent menuBar;
        MenuBarView menuBarView = MenuBarView.getInstance();
        menuBar = menuBarView.getParent();

        // INFOBAR
        FXMLLoader infoBarViewLoader = new FXMLLoader(Initializer.class.getResource("/view/infoBarView.fxml"));
        Parent infoBarView = infoBarViewLoader.load();
        InfoBarView infoBarViewView = infoBarViewLoader.getController();
        infoBarViewView.addToDisplay(translationsController.translate("label.welcome"));

        // ABOUT VIEW
        AboutView aboutView = AboutView.getInstance();
        PropertiesModel propertiesModel = PropertiesModel.getInstance();
        aboutView.initialize();

        // GAMEBOARD VIEW
        GameBoardView gameBoardView = GameBoardView.getInstance();
        aboutView.initialize();

        // menuBar.fxml inside mainView.fxml




    }

}
