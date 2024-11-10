package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.backend.service.gamelogic.player.MySymbolInterface;
import ch.supsi.connectfour.backend.service.gamelogic.player.Player;
import ch.supsi.connectfour.frontend.command.ChangeLanguageCommand;
import ch.supsi.connectfour.frontend.command.PlayerInfoCommand;
import ch.supsi.connectfour.frontend.contracts.handler.ChangeLanguageHandler;
import ch.supsi.connectfour.frontend.contracts.handler.PlayerInfoHandler;
import ch.supsi.connectfour.frontend.contracts.observer.SaveNewInfoObserver;
import ch.supsi.connectfour.frontend.contracts.receiver.ChangeLanguageReceiver;
import ch.supsi.connectfour.frontend.contracts.receiver.PlayerInfoReceiver;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledViewFxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerBarView implements ControlledViewFxml, SaveNewInfoObserver
{
    @FXML
    Button readCharacteristicsPlayer1Button;
    @FXML
    Button editCharacteristicsPlayer1Button;

    @FXML
    Button readCharacteristicsPlayer2Button;
    @FXML
    Button editCharacteristicsPlayer2Button;

    @FXML
    Label player1Name;
    @FXML
    Label player1Symbol;
    @FXML
    Label player2Name;
    @FXML
    Label player2Symbol;

    @FXML
    Button undoButton;
    @FXML
    Button redoButton;

    private static PlayerBarView myself;
    private static Parent parent;

    protected PlayerBarView(){
    }

    public void createEditCharacteristicsPlayer1Behavior(PlayerInfoCommand<PlayerInfoReceiver<PlayerInfoHandler>> command) {
        editCharacteristicsPlayer1Button.setOnAction(action -> {
            command.execute();
        });
    }

    public void createEditCharacteristicsPlayer2Behavior(PlayerInfoCommand<PlayerInfoReceiver<PlayerInfoHandler>> command) {
        editCharacteristicsPlayer2Button.setOnAction(action -> {
            command.execute();
        });
    }
    public static PlayerBarView getInstance()
    {
        if(myself==null)
        {
            try {
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = AboutView.class.getResource("/view/playerbar.fxml");
                if (fxmlurl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    myself = new PlayerBarView();
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return myself;
    }

    @Override
    public void initialize() {

    }

    @Override
    public Parent getParent() {
        return parent;
    }



    @Override
    public void saveNewInfo(String playerID, String newName, MySymbolInterface newSymbol) {
        switch (playerID){
            case "player1":
                updatePlayer1Infos(newName, newSymbol);
                break;

            case "player2":
                updatePlayer2Infos(newName, newSymbol);
                break;
        }
    }

    void updatePlayer1Infos(final String newName, final MySymbolInterface newSymbol){
        player1Name.setText("");
        player1Symbol.setText("");

        player1Name.setText(newName);
        player1Symbol.setText(newSymbol.getCharacter().toString());
    }

    void updatePlayer2Infos(final String newName, final MySymbolInterface newSymbol){
        player2Name.setText("");
        player2Symbol.setText("");

        player2Name.setText(newName);
        player2Symbol.setText(newSymbol.getCharacter().toString());
    }
}
