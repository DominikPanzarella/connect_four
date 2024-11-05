package ch.supsi.connectfour.frontend.initializer;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.player.HumanPlayer;
import ch.supsi.connectfour.backend.service.gamelogic.player.MyColor;
import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbol;
import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.frontend.command.MakeMoveCommand;
import ch.supsi.connectfour.frontend.command.OpenFileCommand;
import ch.supsi.connectfour.frontend.contracts.handler.MakeMoveHandler;
import ch.supsi.connectfour.frontend.contracts.handler.OpenFileHandler;
import ch.supsi.connectfour.frontend.contracts.receiver.MakeMoveReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.OpenFileReceiver;
import ch.supsi.connectfour.frontend.controller.ColumnSelectorController;
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


        MySymbolInterface player1Symbol = new MySymbol('A', new MyColor(1.0,0,0));
        MySymbolInterface player2Symbol = new MySymbol('B', new MyColor(0,1.0,0));

        model.addPlayer(new HumanPlayer("Player1", player1Symbol));
        model.addPlayer(new HumanPlayer("Player2", player2Symbol));
        /*
            ###################################
                Loading all fxml files
            ###################################
        */

        // MENUBAR
        MenuBarView menuBarView = MenuBarView.getInstance();
        Parent menuBar = menuBarView.getParent();

        // INFOBAR
        InfoBarView infoBarView = InfoBarView.getInstance();
        Parent infoBarViewParent = infoBarView.getParent();

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
        addToAnchorPane(mainViewController.getInfoBarPane(), infoBarViewParent);
        addToAnchorPane(mainViewController.getColumnSelectorPane(), columnSelectorViewParent);
        addToAnchorPane(mainViewController.getConnectFour(), gameBoardViewParent); // Last added, should be on top
        addToAnchorPane(mainViewController.getPlayersPane(), playerBarViewParent);

        /*
           ###################################
                   Setup Controller
           ###################################
         */
        MenuBarController menuBarController = MenuBarController.getInstance(model);
        ColumnSelectorController columnSelectorController = ColumnSelectorController.getInstance(model);
         /*
           ###################################
                   Setup Receiver
           ###################################
         */
        OpenFileReceiver<OpenFileHandler> openFileReceiver = menuBarController;
        MakeMoveReceiver<MakeMoveHandler> makeMoveReceiver = columnSelectorController;

        /*
           ###################################
                   Setup Command
           ###################################
         */

        OpenFileCommand<OpenFileReceiver<OpenFileHandler>> openFileCommand = OpenFileCommand.create(openFileReceiver);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn0Command = MakeMoveCommand.create(makeMoveReceiver,0);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn1Command = MakeMoveCommand.create(makeMoveReceiver,1);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn2Command = MakeMoveCommand.create(makeMoveReceiver,2);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn3Command = MakeMoveCommand.create(makeMoveReceiver,3);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn4Command = MakeMoveCommand.create(makeMoveReceiver,4);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn5Command = MakeMoveCommand.create(makeMoveReceiver,5);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn6Command = MakeMoveCommand.create(makeMoveReceiver,6);

         /*
           ###################################
               Linking Component - Command
           ###################################
         */
        menuBarView.createOpenMenuItemBehaviour(openFileCommand);
        columnSelectorView.makeMoveColumn0(makeMoveColumn0Command);
        columnSelectorView.makeMoveColumn1(makeMoveColumn1Command);
        columnSelectorView.makeMoveColumn2(makeMoveColumn2Command);
        columnSelectorView.makeMoveColumn3(makeMoveColumn3Command);
        columnSelectorView.makeMoveColumn4(makeMoveColumn4Command);
        columnSelectorView.makeMoveColumn5(makeMoveColumn5Command);
        columnSelectorView.makeMoveColumn6(makeMoveColumn6Command);


         /*
          ###################################
              Linking Observer - Notifier
           ###################################

         */

        model.addMoveObserver(gameBoardView);
        model.addObserver(infoBarView);
        model.addColumnFullObserver(columnSelectorView);

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
