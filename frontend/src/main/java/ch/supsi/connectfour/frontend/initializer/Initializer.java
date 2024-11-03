package ch.supsi.connectfour.frontend.initializer;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.command.OpenFileCommand;
import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.OpenFileReceiver;
import ch.supsi.connectfour.frontend.controller.MenuBarController;
import ch.supsi.connectfour.frontend.model.ConnectFourModel;
import ch.supsi.connectfour.frontend.view.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Initializer {

    public static void init(Stage stage) throws IOException, InstantiationException {

        TranslationsController translationsController = TranslationsController.getInstance();
        ConnectFourModel model = ConnectFourModel.getInstance();
        /*
            ###################################
                Loading all fxml files
            ###################################
        */

        // MENUBAR
        MenuBarView menuBarView = MenuBarView.getInstance();
        Parent menuBar = menuBarView.getParent();

        // INFOBAR
        FXMLLoader infoBarViewLoader = new FXMLLoader(Initializer.class.getResource("/view/infobar.fxml"));
        Parent infoBarView = infoBarViewLoader.load();
        InfoBarView infoBarViewController = infoBarViewLoader.getController();
        infoBarViewController.addToDisplay(translationsController.translate("label.welcome"));

        // ABOUT VIEW
        AboutView aboutView = AboutView.getInstance();
        aboutView.initialize();

        // GAMEBOARD VIEW
        GameBoardView gameBoardView = GameBoardView.getInstance();
        gameBoardView.initialize();
        Parent gameBoardViewParent = gameBoardView.getParent();

        // COLUMN SELECTOR VIEW
        ColumnSelectorView columnSelectorView = ColumnSelectorView.getInstance();
        columnSelectorView.initialize();
        Parent columnSelectorViewParent = columnSelectorView.getParent();

        // PLAYER SIDE BAR
        PlayerBarView playerBarView = PlayerBarView.getInstance();
        playerBarView.initialize();
        Parent playerBarViewParent = playerBarView.getParent();

        // Load main view and get controller
        FXMLLoader mainViewLoader = new FXMLLoader(Initializer.class.getResource("/view/mainview.fxml"));
        Parent mainView = mainViewLoader.load();
        MainView mainViewController = mainViewLoader.getController();

        // Integrate views into mainView.fxml's panes
        addToAnchorPane(mainViewController.getMenuBarPane(), menuBar);
        addToAnchorPane(mainViewController.getInfoBarPane(), infoBarView);
        addToAnchorPane(mainViewController.getColumnSelectorPane(), columnSelectorViewParent);
        addToAnchorPane(mainViewController.getConnectFour(), gameBoardViewParent); // Last added, should be on top
        addToAnchorPane(mainViewController.getPlayersPane(), playerBarViewParent);

        /*
           ###################################
                   Setup Controller
           ###################################
         */
        MenuBarController menuBarController = MenuBarController.getInstance(model);

         /*
           ###################################
                   Setup Receiver
           ###################################
         */
        OpenFileReceiver<OpenFileHandler> openFileReceiver = menuBarController;

        /*
           ###################################
                   Setup Command
           ###################################
         */

        OpenFileCommand<OpenFileReceiver<OpenFileHandler>> openFileCommand = OpenFileCommand.create(openFileReceiver);

         /*
           ###################################
               Linking Component - Command
           ###################################
         */
        menuBarView.createOpenMenuItemBehaviour(openFileCommand);

         /*
          ###################################
              Linking Observer - Notifier
           ###################################

         */

         /*
          ###################################
              Setup the stage
           ###################################

         */
        stage.setTitle("ConnectFour");
        stage.setScene(new Scene(mainView));
        stage.setResizable(false);
        // stage.getIcons().add(new Image(Objects.requireNonNull(Initializer.class.getResourceAsStream("/logo/2DEditorLogo.png"))));
        stage.show();
    }

    /**
     * Utility method to add a child to an AnchorPane and set all anchors to 0.
     */
    private static void addToAnchorPane(AnchorPane pane, Parent child) {
        pane.getChildren().add(child);
        AnchorPane.setTopAnchor(child, 0.0);
        AnchorPane.setBottomAnchor(child, 0.0);
        AnchorPane.setLeftAnchor(child, 0.0);
        AnchorPane.setRightAnchor(child, 0.0);
    }
}
