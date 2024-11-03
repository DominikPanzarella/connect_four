package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledViewFxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerBarView implements ControlledViewFxml
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
    Button undoButton;
    @FXML
    Button redoButton;

    private static PlayerBarView myself;
    private static Parent parent;

    protected PlayerBarView(){

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
}
