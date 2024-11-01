package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.controller.TranslationsController;
import ch.supsi.connectfour.frontend.contracts.viewContracts.ControlledViewFxml;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SymbolsMenu implements ControlledViewFxml
{
    @FXML
    ColorPicker colorPlayer1;
    @FXML
    ColorPicker colorPlayer2;
    @FXML
    ColorPicker symbolPlayer1;
    @FXML
    ColorPicker symbolPlayer2;
    @FXML
    ColorPicker labelPlayer1;
    @FXML
    ColorPicker labelPlayer2;
    private static Stage stage;
    private static Parent parent;
    private static SymbolsMenu myself;

    protected SymbolsMenu()
    {
        //empty constructor
    }

    public static SymbolsMenu getInstance() {
        if (myself == null) {
            myself = new SymbolsMenu();
            try {
                TranslationsController translationsController = TranslationsController.getInstance();
                ResourceBundle bundle = translationsController.getResourceBundle();
                URL fxmlurl = SymbolsMenu.class.getResource("/view/symbolmenu.fxml");
                if (fxmlurl != null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(fxmlurl, bundle);
                    fxmlLoader.setController(myself);
                    parent = fxmlLoader.load();
                    stage = new Stage();
                    stage.setScene(new Scene(parent));
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
