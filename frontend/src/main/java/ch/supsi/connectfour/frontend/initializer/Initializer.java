package ch.supsi.connectfour.frontend.initializer;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.player.*;
import ch.supsi.connectfour.frontend.command.*;
import ch.supsi.connectfour.frontend.contracts.handler.*;
import ch.supsi.connectfour.frontend.contracts.receiver.*;
import ch.supsi.connectfour.frontend.contracts.receiver.ExitReceiver;
import ch.supsi.connectfour.frontend.controller.AboutController;
import ch.supsi.connectfour.frontend.controller.ColumnSelectorController;
import ch.supsi.connectfour.frontend.controller.MenuBarController;
import ch.supsi.connectfour.frontend.controller.PlayerInfoController;
import ch.supsi.connectfour.frontend.model.*;
import ch.supsi.connectfour.frontend.view.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Initializer {

    public static void init(Stage stage) throws IOException, InstantiationException {

        MySymbolInterface player1Symbol = new MySymbol('A', new MyColor(1.0,0,0));
        MySymbolInterface player2Symbol = new MySymbol('B', new MyColor(0,1.0,0));
        Player player1 = new HumanPlayer("Player1", player1Symbol);
        Player player2 = new HumanPlayer("Player2", player2Symbol);

        TranslationsController translationsController = TranslationsController.getInstance();
        ConnectFourModel model = ConnectFourModel.getInstance();
        AboutModel aboutModel = AboutModel.getInstance();
        PropertiesModel propertiesModel = PropertiesModel.getInstance();
        LanguageModel languageModel = LanguageModel.getInstance();

        model.addPlayer(player1);
        model.addPlayer(player2);
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

        //PLAYER INFO VIEW
        PlayerInfoView playerInfoView1 = new PlayerInfoView(0);
        playerInfoView1.initialize();

        PlayerInfoView playerInfoView2 = new PlayerInfoView(1);
        playerInfoView2.initialize();


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

        //EXIT VIEW
        ExitView exitView = ExitView.getInstance();
        exitView.initialize();

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
        AboutController aboutController = AboutController.getInstance(aboutModel,propertiesModel);
        PlayerInfoController playerInfoController1 = new PlayerInfoController(0,model,model);
        PlayerInfoController playerInfoController2 = new PlayerInfoController(1,model, model);

        MenuBarController menuBarController = MenuBarController.getInstance(model,model, languageModel);
        ColumnSelectorController columnSelectorController = ColumnSelectorController.getInstance(model);
         /*
           ###################################
                   Setup Receiver
           ###################################
         */
        AboutReceiver<AboutHandler> aboutReceiver = aboutController;
        PlayerInfoReceiver<PlayerInfoHandler> playerInfoReceiver1 = playerInfoController1;
        PlayerInfoReceiver<PlayerInfoHandler> playerInfoReceiver2 = playerInfoController2;
        OpenFileReceiver<OpenFileHandler> openFileReceiver = menuBarController;
        MakeMoveReceiver<MakeMoveHandler> makeMoveReceiver = columnSelectorController;
        ExportFileReceiver<ExportFileHandler> exportFileReceiver = menuBarController;
        ExitReceiver<ExitHandler> exitReceiver = menuBarController;
        OkReceiver<OKHandler> okReceiver = menuBarController;
        CancelReceiver<CancelHandler> cancelReceiver = menuBarController;
        ChangeLanguageReceiver<ChangeLanguageHandler> languageReceiver = menuBarController;
        SaveNewInfoReceiver<SaveNewInfoHandler> saveNewInfoReceiver1 = playerInfoController1;
        SaveNewInfoReceiver<SaveNewInfoHandler> saveNewInfoReceiver2 = playerInfoController2;
        /*
           ###################################
                   Setup Command
           ###################################
         */
        AboutCommand<AboutReceiver<AboutHandler>> aboutCommand = AboutCommand.create(aboutReceiver);

        PlayerInfoCommand<PlayerInfoReceiver<PlayerInfoHandler>> playerInfoCommand1 = PlayerInfoCommand.create(playerInfoReceiver1);
        PlayerInfoCommand<PlayerInfoReceiver<PlayerInfoHandler>> playerInfoCommand2 = PlayerInfoCommand.create(playerInfoReceiver2);
        SaveNewInfoCommand<SaveNewInfoReceiver<SaveNewInfoHandler>> saveNewInfoCommand1 = SaveNewInfoCommand.create(saveNewInfoReceiver1);
        SaveNewInfoCommand<SaveNewInfoReceiver<SaveNewInfoHandler>> saveNewInfoCommand2 = SaveNewInfoCommand.create(saveNewInfoReceiver2);

        OpenFileCommand<OpenFileReceiver<OpenFileHandler>> openFileCommand = OpenFileCommand.create(openFileReceiver);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn0Command = MakeMoveCommand.create(makeMoveReceiver,0);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn1Command = MakeMoveCommand.create(makeMoveReceiver,1);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn2Command = MakeMoveCommand.create(makeMoveReceiver,2);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn3Command = MakeMoveCommand.create(makeMoveReceiver,3);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn4Command = MakeMoveCommand.create(makeMoveReceiver,4);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn5Command = MakeMoveCommand.create(makeMoveReceiver,5);
        MakeMoveCommand<MakeMoveReceiver<MakeMoveHandler>> makeMoveColumn6Command = MakeMoveCommand.create(makeMoveReceiver,6);
        ExportFileCommand<ExportFileReceiver<ExportFileHandler>> exportFileCommand = ExportFileCommand.create(exportFileReceiver);
        ExitCommand<ExitReceiver<ExitHandler>> exitCommand = ExitCommand.create(exitReceiver);
        OkCommand<OkReceiver<OKHandler>> okCommand  = OkCommand.create(okReceiver);
        CancelCommand<CancelReceiver<CancelHandler>> cancelCommand = CancelCommand.create(cancelReceiver, exitView.getStage());
        ChangeLanguageCommand<ChangeLanguageReceiver<ChangeLanguageHandler>> languageEnUSCommand = ChangeLanguageCommand.create(languageReceiver, "en-US");        //Refactored
        ChangeLanguageCommand<ChangeLanguageReceiver<ChangeLanguageHandler>> languageItCHCommand = ChangeLanguageCommand.create(languageReceiver, "it-CH");        //Refactored
        ChangeLanguageCommand<ChangeLanguageReceiver<ChangeLanguageHandler>> languageFrFRCommand = ChangeLanguageCommand.create(languageReceiver, "fr-FR");        //Refactored
        ChangeLanguageCommand<ChangeLanguageReceiver<ChangeLanguageHandler>> languageDeDECommand = ChangeLanguageCommand.create(languageReceiver, "de-DE");        //Refactored
         /*
           ###################################
               Linking Component - Command
           ###################################
         */
        menuBarView.createAboutBehavior(aboutCommand);
        menuBarView.createOpenMenuItemBehaviour(openFileCommand);
        playerBarView.createEditCharacteristicsPlayer1Behavior(playerInfoCommand1);
        playerBarView.createEditCharacteristicsPlayer2Behavior(playerInfoCommand2);
        columnSelectorView.makeMoveColumn0(makeMoveColumn0Command);
        columnSelectorView.makeMoveColumn1(makeMoveColumn1Command);
        columnSelectorView.makeMoveColumn2(makeMoveColumn2Command);
        columnSelectorView.makeMoveColumn3(makeMoveColumn3Command);
        columnSelectorView.makeMoveColumn4(makeMoveColumn4Command);
        columnSelectorView.makeMoveColumn5(makeMoveColumn5Command);
        columnSelectorView.makeMoveColumn6(makeMoveColumn6Command);
        menuBarView.createExitBehaviour(exitCommand);
        exitView.createCancelBehaviour(cancelCommand);
        exitView.createOKBehaviour(okCommand);
        menuBarView.createExportFileBehaviour(exportFileCommand);
        menuBarView.createItCHMenuItemBehaviour(languageItCHCommand);
        menuBarView.createEnUSMenuItemBehaviour(languageEnUSCommand);
        menuBarView.createFrFRMenuItemBehaviour(languageFrFRCommand);
        menuBarView.createDeDEMenuItemBehaviour(languageDeDECommand);
        playerInfoView1.createSaveNewInfoBehavior(saveNewInfoCommand1);
        playerInfoView2.createSaveNewInfoBehavior(saveNewInfoCommand2);
         /*
          ###################################
              Linking Observer - Notifier
           ###################################

         */
        model.addMoveObserver(gameBoardView);
        model.addObserver(infoBarView);
        model.addColumnFullObserver(columnSelectorView);
        model.addGameHasAWinnerObserver(infoBarView);
        model.addGameHasAWinnerObserver(columnSelectorView);
        model.addGameDrawObserver(infoBarView);
        model.addClearViewObserver(gameBoardView);
        model.addExitObserver(exitView);
        aboutModel.addAboutObserver(aboutView);

        model.addPlayerInfoObserver(playerInfoView1);
        model.addPlayerInfoObserver(playerInfoView2);
        model.addSaveNewInfoObserver(playerBarView);
        model.addSaveNewInfoObserver(playerBarView);
        model.addRepaintObserver(gameBoardView);
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
